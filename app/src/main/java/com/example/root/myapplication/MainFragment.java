package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.zip.Inflater;



public class MainFragment extends Fragment {

    private EditText nameView;
    private EditText ageView;
    private RadioGroup buttonView;
    private RadioButton checkedButton;
    Spinner mySpinner;

    public static String name;
    public static String age;
    public static String gender;
    public static String city;
    public static ArrayList<StudentInfo> studentsList;
    public static Bundle b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_main, container, false);
        if (getArguments() != null)
            studentsList = getArguments().getParcelableArrayList("StudentsList");
        else
            studentsList = new ArrayList<>();

        nameView = inflatedView.findViewById(R.id.input_name);
        ageView = inflatedView.findViewById(R.id.input_age);
        buttonView = inflatedView.findViewById(R.id.gender_button);

        mySpinner = inflatedView.findViewById(R.id.spinner_city_list);
        final ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.city_list, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(dataAdapter);

        b = new Bundle();

        Button addButton = inflatedView.findViewById(R.id.add_more_button);
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (credentialsVerified()) {
                    int checkedButtonId = buttonView.getCheckedRadioButtonId();
                    checkedButton = getView().findViewById(checkedButtonId);

                    gender = checkedButton.getText().toString();
                    name = nameView.getText().toString();
                    age = ageView.getText().toString();
                    city = mySpinner.getSelectedItem().toString();

                    StudentInfo student = new StudentInfo(name, Integer.parseInt(age), gender, city);
                    studentsList.add(student);
                    b.putParcelableArrayList("StudentsList", studentsList);
                    nameView.getText().clear();
                    ageView.getText().clear();
                    buttonView.check(R.id.female);
                    mySpinner.setSelection(1);
                }
            }
        });

        Button finishButton = inflatedView.findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (credentialsVerified()) {
                    int checkedButtonId = buttonView.getCheckedRadioButtonId();
                    checkedButton = getView().findViewById(checkedButtonId);

                    city = mySpinner.getSelectedItem().toString();
                    gender = checkedButton.getText().toString();
                    name = nameView.getText().toString();
                    age = ageView.getText().toString();

                    StudentInfo student = new StudentInfo(name, Integer.parseInt(age), gender, city);
                    studentsList.add(student);
                    b.putParcelableArrayList("StudentsList", studentsList);
                    Intent i = new Intent(getActivity(), DetailsActivity.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
        return inflatedView;
    }

    public boolean credentialsVerified(){

        nameView.setError(null);
        ageView.setError(null);

        boolean verified = true;
        View focusView = null;

        String name = nameView.getText().toString();
        String age = ageView.getText().toString();

        if(TextUtils.isEmpty(name)){
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            verified = false;
        }else if(!isNameValid(name)){
            nameView.setError(getString(R.string.error_invalid_name));
            focusView = nameView;
            verified = false;
        }
        if(TextUtils.isEmpty(age)){
            ageView.setError(getString(R.string.error_field_required));
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

    
