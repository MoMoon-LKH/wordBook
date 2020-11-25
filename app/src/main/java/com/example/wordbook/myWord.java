package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class myWord extends AppCompatActivity {
    WordRecyclerAdapter adapter;
    RecyclerView myRecyclerView;
    SQLiteDatabase db;
    WordDBHelper wordDBHelper;
    Cursor cursor;

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
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new WordRecyclerAdapter();
        myRecyclerView.setAdapter(adapter);

        wordDBHelper = new WordDBHelper(this,WordDBHelper.DB_NAME,null,WordDBHelper.DB_VERSION);
        db = wordDBHelper.getReadableDatabase();
        getDbData();
        adapter.notifyDataSetChanged();
    }

    void getDbData(){
        cursor = wordDBHelper.recordReadCursorMy(db);

        while(cursor.moveToNext()){
            String eng = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_ENG));
            String kr = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_KR));
            adapter.itemAdd(new Word(eng,kr));
        }
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        db.close();
        wordDBHelper.close();
        super.onDestroy();
    }
}