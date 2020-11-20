package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class myWord extends AppCompatActivity {
    wordRecyclerAdapter adapter;
    RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);

        init();
    }


    void init() {
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL), 1);
        adapter = new wordRecyclerAdapter();
        myRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}