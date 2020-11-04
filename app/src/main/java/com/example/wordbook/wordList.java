package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

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
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        getData();

    }

    private void getData(){
        List<String> listBook = Arrays.asList("단어장1","단어장2","단어장3","단어장4","단어장5","단어장6");

        for(int i = 0; i < listBook.size(); i++){
            listData data = new listData();
            data.setWordBook(listBook.get(i));

            adapter.itemAdd(data);
        }
        adapter.notifyDataSetChanged();
    }
}