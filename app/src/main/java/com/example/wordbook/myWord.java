package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class myWord extends AppCompatActivity {
    WordRecyclerAdapter adapter;
    RecyclerView myRecyclerView;
    SQLiteDatabase db;
    WordDBHelper wordDBHelper;
    Cursor cursor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);

        init();
    }


    void init() {
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new WordRecyclerAdapter();
        myRecyclerView.setAdapter(adapter);

        wordDBHelper = new WordDBHelper(this,WordDBHelper.DB_NAME,null,WordDBHelper.DB_VERSION);
        db = wordDBHelper.getReadableDatabase();
        getDbData();
        adapter.notifyDataSetChanged();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
            case R.id.menuDelete :

                return true;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        db.close();
        wordDBHelper.close();
        super.onDestroy();
    }
}