package com.example.doubt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Askdoubt extends AppCompatActivity {

    RecyclerView recyclerView;
    Subjectadapter adapter;
    List<Subject> list;
    DatabaseReference dbs;
    EditText ed;
    ImageView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askdoubt);
        ed=findViewById(R.id.dedit);
        b=findViewById(R.id.dimg);
        list= new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter=new Subjectadapter(this,list);
        recyclerView.setAdapter(adapter);
        dbs= FirebaseDatabase.getInstance().getReference("subject");
        dbs.addListenerForSingleValueEvent(valueEventListener);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sn=ed.getText().toString().trim();
                dbs= FirebaseDatabase.getInstance().getReference("subject");
                Query query=FirebaseDatabase.getInstance().getReference("subject")
                        .orderByChild("sn")
                        .equalTo(sn);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

    };



    final ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();
            if (snapshot.exists()) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Subject subject = snapshot1.getValue(Subject.class);
                    list.add(subject);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    };
}