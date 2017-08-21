package com.example.root.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import java.util.ArrayList;

import static com.example.root.myapplication.LoginFragment.MyPREFERENCES;


public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    onButtonClickListener buttonClickListener;
    private TextView mtextView;

    interface onButtonClickListener{
        void onButtonClick(View view);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        try{
            buttonClickListener = (onButtonClickListener) (Activity)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onButtonClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflateView = inflater.inflate(R.layout.fragment_list,container,false);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String username = sharedPref.getString("UserName", "").toUpperCase();
        String email = sharedPref.getString("E-mail", "");

        SpannableString spannable = new SpannableString(username + "\n" + email);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, username.length(), 0);
        spannable.setSpan(new StyleSpan(Typeface.ITALIC), username.length(), spannable.length(), 0);
        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.brown)), 0, username.length(), 0);
        spannable.setSpan(new RelativeSizeSpan(2f), 0, username.length(), 0);

        mtextView = inflateView.findViewById(R.id.login_details);
        mtextView.setText(spannable);

        mRecyclerView = inflateView.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getArguments().<StudentInfo>getParcelableArrayList("StudentsList"));
        mRecyclerView.setAdapter(mAdapter);

        Button backButton = inflateView.findViewById(R.id.back_button);
        Button logoutButton = inflateView.findViewById(R.id.logout_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClickListener.onButtonClick(view);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClickListener.onButtonClick(view);
            }
        });

        return inflateView;
    }
}


