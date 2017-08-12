package com.example.root.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText ageView;
    private RadioGroup buttonView;
    private RadioButton checkedButton;

    public static String name;
    public static String age;
    public static String gender;
    public static ArrayList<StudentInfo> studentsList;
    public static Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras() != null)
            studentsList = getIntent().getExtras().getParcelableArrayList("StudentsList");
        else
            studentsList = new ArrayList<>();

        nameView = (EditText) findViewById(R.id.input_name);
        ageView = (EditText) findViewById(R.id.input_age);
        buttonView = (RadioGroup) findViewById(R.id.gender_button);

        b = new Bundle();

        Button addButton = (Button) findViewById(R.id.add_more_button);
        addButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                if(credentialsVerified()){
                    int checkedButtonId = buttonView.getCheckedRadioButtonId();
                    checkedButton =(RadioButton) findViewById(checkedButtonId);

                    gender = checkedButton.getText().toString();
                    name = nameView.getText().toString();
                    age = ageView.getText().toString();

                    StudentInfo student = new StudentInfo(name,Integer.parseInt(age),gender);
                    studentsList.add(student);
                    b.putParcelableArrayList("StudentsList",studentsList);
                    nameView.getText().clear();
                    ageView.getText().clear();
                    buttonView.check(R.id.female);
                }
            }
        });

        Button finishButton = (Button) findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(credentialsVerified()) {
                    int checkedButtonId = buttonView.getCheckedRadioButtonId();
                    checkedButton =(RadioButton) findViewById(checkedButtonId);

                    gender = checkedButton.getText().toString();
                    name = nameView.getText().toString();
                    age = ageView.getText().toString();

                    StudentInfo student = new StudentInfo(name,Integer.parseInt(age),gender);
                    studentsList.add(student);
                    b.putParcelableArrayList("StudentsList",studentsList);
                    Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
    }

    public void onPause(){
        super.onPause();
        finish();
    }

    public boolean credentialsVerified(){

        nameView.setError(null);
        ageView.setError(null);

        boolean verified = true;
        View focusView = null;

        String name = nameView.getText().toString();
        String age = ageView.getText().toString();

        if(TextUtils.isEmpty(name)){
            nameView.setError(getString(R.string.error_empty_name));
            focusView = nameView;
            verified = false;
        }else if(!isNameValid(name)){
            nameView.setError(getString(R.string.error_invalid_name));
            focusView = nameView;
            verified = false;
        }
        if(TextUtils.isEmpty(age)){
            ageView.setError(getString(R.string.error_empty_age));
            focusView = ageView;
            verified = false;
        }else if(!isAgeValid(age)){
            ageView.setError(getString(R.string.error_invalid_age));
            focusView = ageView;
            verified = false;
        }

        if(!verified){
            focusView.requestFocus();
        }

        return verified;
    }

    public boolean isNameValid(String name){
        return name.matches("[a-zA-Z]*");
    }

    public boolean isAgeValid(String age){
        return age.matches("[1-9][0-9]*");
    }
}
