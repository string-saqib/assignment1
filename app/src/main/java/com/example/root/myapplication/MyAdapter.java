package com.example.root.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<StudentInfo> studentList;

    public MyAdapter(ArrayList<StudentInfo> listinfo){
        studentList = listinfo;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtAgeAndSex;
        TextView txtCity;
        View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.detail1);
            txtAgeAndSex = v.findViewById(R.id.detail2);
            txtCity = v.findViewById(R.id.detail3);
        }
    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(studentList.get(position).getName());
        holder.txtAgeAndSex.setText(studentList.get(position).getAge() + ", " + studentList.get(position).getGender());
        holder.txtCity.setText(studentList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
