package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;


import java.util.Arrays;
import java.util.List;

public class wordList extends AppCompatActivity {
    RecyclerAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

       

        recyclerView = (RecyclerView) findViewById(R.id.wordListView);


        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        getData();

    }




    private void getData(){
        List<String> listBook = Arrays.asList("토익 단어(1)","토익 단어(2)");

        for(int i = 0; i < listBook.size(); i++){
            listData data = new listData();
            data.setWordBook(listBook.get(i));
            adapter.itemAdd(data);
        }
        adapter.notifyDataSetChanged();
    }
}