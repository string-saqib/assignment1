package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    ListView listView;

    private static MyAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_list, container, false);
        listView = inflateView.findViewById(R.id.list_view);
        adapter = new MyAdapter(getArguments().<StudentInfo>getParcelableArrayList("StudentsList"), getContext());
        listView.setAdapter(adapter);
        return inflateView;
    }
}

