package com.example.doubt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editunitm,ph,unamem;
    Button btn,next;
    Spinner unit;
    DatabaseReference ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
;        setContentView(R.layout.activity_main);

        ds= FirebaseDatabase.getInstance().getReference("subject");
        editunitm = (EditText) findViewById(R.id.edit);
        btn = (Button) findViewById(R.id.addknown);
        unamem =(EditText)findViewById(R.id.editname);
        unit = (Spinner) findViewById(R.id.spin);
        ph=(EditText) findViewById(R.id.yedit);
        next=(Button) findViewById(R.id.addunknown);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsub();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Askdoubt.class);
                startActivity(i);
            }
        });

    }
        private void addsub(){
            String subname=editunitm.getText().toString().trim();
            String username=unamem.getText().toString().trim();
            String units=unit.getSelectedItem().toString();
            String phone=ph.getText().toString().trim();

            if(!TextUtils.isEmpty(subname)){
                String id=ds.push().getKey();

                Subject sub=new Subject(id,username,phone,subname,units);
                ds.child(id).setValue(sub);

                Toast.makeText(this, "Done!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "You should enter the subject",Toast.LENGTH_LONG).show();
            }

    }

}