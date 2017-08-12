package com.example.root.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<StudentInfo> {

    ArrayList<StudentInfo> arrayList;
    Context mContext;

    public MyAdapter(ArrayList<StudentInfo> listinfo, Context context){
        super(context,R.layout.simple_list_item,listinfo);
        arrayList = listinfo;
        mContext = context;
    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtAgeAndSex;
    }

    public View getView(int position, View convertView, ViewGroup container){

        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.simple_list_item,container,false);
        viewHolder.txtName =  convertView.findViewById(R.id.detail1);
        viewHolder.txtAgeAndSex =  convertView.findViewById(R.id.detail2);

            viewHolder.txtName.setText(arrayList.get(position).getName());
            viewHolder.txtAgeAndSex.setText(arrayList.get(position).getAge() + ", " + arrayList.get(position).getGender());

        return convertView;
    }
}
