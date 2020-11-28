package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;


import java.util.Arrays;
import java.util.List;

public class wordList extends AppCompatActivity {
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        recyclerView = (RecyclerView) findViewById(R.id.wordListView);
        toolbar = (Toolbar) findViewById(R.id.listToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        getData();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }

        return false;
    }

    private void getData(){
        List<String> listBook = Arrays.asList("토익 단어 (1)","토익 단어 (2)");

        for(int i = 0; i < listBook.size(); i++){
            listData data = new listData();
            data.setWordBook(listBook.get(i));
            adapter.itemAdd(data);
        }
        adapter.notifyDataSetChanged();
    }
}