package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ListView listView;
    public static ArrayList<StudentInfo> students;
    private static MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        students = extras.getParcelableArrayList("StudentsList");

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter(students, getApplicationContext());
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, MainActivity.class);
                Bundle b = new Bundle();

                b.putParcelableArrayList("StudentsList", students);
                i.putExtras(b);

                startActivity(i);
                finish();
            }
        });
    }

}
