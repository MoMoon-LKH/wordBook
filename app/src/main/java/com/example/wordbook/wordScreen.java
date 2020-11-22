package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class wordScreen extends AppCompatActivity {
    TextView toolbarTxt, numTxt,screenEngTxt,screenKrTxt;
    Button btnBack,btnMy,btnNext;
    WordDBHelper wordDBHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);

        init();

        db.close();
        wordDBHelper.close();
    }

    void init(){
        toolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        numTxt = (TextView) findViewById(R.id.numTxt);
        screenEngTxt = (TextView) findViewById(R.id.screenEngTxt);
        screenKrTxt = (TextView) findViewById(R.id.screenKrTxt);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnMy = (Button) findViewById(R.id.btnMy);
        btnNext = (Button) findViewById(R.id.btnNext);
        wordDBHelper = new WordDBHelper(this,WordDBHelper.DB_NAME,null,WordDBHelper.DB_VERSION);
        db = wordDBHelper.getWritableDatabase();

        Intent intent = getIntent();

        String txt = intent.getExtras().getString("txt");
        int pos = intent.getExtras().getInt("pos");
        toolbarTxt.setText(txt);


    }
}