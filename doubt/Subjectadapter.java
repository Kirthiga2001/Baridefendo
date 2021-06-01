package com.example.doubt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Subjectadapter extends RecyclerView.Adapter<Subjectadapter.subjectviewholder> implements Filterable {

    private Context con;
    private List<Subject> units;
    private List<Subject> allunits;

    public Subjectadapter(Context con, List<Subject> units) {
        this.con = con;
        this.units = units;
        this.allunits=new ArrayList<>(units);
    }

    @Override
    public subjectviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con);
        View view=inflater.inflate(R.layout.list,null);
        return new subjectviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subjectviewholder holder, int position) {
        Subject sub=units.get(position);
        holder.tus.setText(sub.getUn());
        holder.tph.setText(sub.getPh());
        holder.tsub.setText(sub.getSn());
        holder.tunit.setText(sub.getU());
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Subject> filteredlist=new ArrayList<>();
            if (constraint.toString().isEmpty()){
                filteredlist.addAll(allunits);
            }
            else {
                for(Subject unit :allunits){
                    if(unit.toString().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredlist.add(unit);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            units.clear();
            units.addAll((Collection<? extends Subject>) results.values);
            notifyDataSetChanged();

        }
    };
    class subjectviewholder extends RecyclerView.ViewHolder{

        TextView tus,tph,tsub,tunit;

        public subjectviewholder(@NonNull View itemView) {
            super(itemView);
            tus= itemView.findViewById(R.id.textid);
            tph= itemView.findViewById(R.id.textna);
            tsub= itemView.findViewById(R.id.textsn);
            tunit= itemView.findViewById(R.id.textu);
        }
    }
}
