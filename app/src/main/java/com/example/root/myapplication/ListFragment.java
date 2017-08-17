package com.example.root.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;


public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    onButtonClickListener buttonClickListener;

    interface onButtonClickListener{
        void onButtonClick();
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
        mRecyclerView = inflateView.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getArguments().<StudentInfo>getParcelableArrayList("StudentsList"));
        mRecyclerView.setAdapter(mAdapter);

        Button button = inflateView.findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick();
            }
        });

        return inflateView;
    }
}

