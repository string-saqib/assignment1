package com.example.root.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    public static ArrayList<StudentInfo> students;
    public static Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        students = getIntent().getExtras().getParcelableArrayList("StudentsList");

        bundle = new Bundle();
        bundle.putParcelableArrayList("StudentsList",students);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,fragment).commit();

        Button button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });

    }

}
