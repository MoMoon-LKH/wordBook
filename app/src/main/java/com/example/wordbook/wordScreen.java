package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class wordScreen extends AppCompatActivity {
    TextView toolbarTxt, numTxt, screenEngTxt, screenKrTxt;
    Button btnBack, btnMy, btnNext;
    WordDBHelper wordDBHelper;
    SQLiteDatabase db;
    int pos;
    int num = 0;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);

        init();


    }

    void init() {
        toolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        numTxt = (TextView) findViewById(R.id.numTxt);
        screenEngTxt = (TextView) findViewById(R.id.screenEngTxt);
        screenKrTxt = (TextView) findViewById(R.id.screenKrTxt);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnMy = (Button) findViewById(R.id.btnMy);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnBack.setVisibility(View.INVISIBLE);

        wordDBHelper = new WordDBHelper(this, WordDBHelper.DB_NAME, null, WordDBHelper.DB_VERSION);
        db = wordDBHelper.getWritableDatabase();

        Intent intent = getIntent();

        String txt = intent.getExtras().getString("txt");
        pos = intent.getExtras().getInt("pos");
        toolbarTxt.setText(txt);
        setCursor();

        printNextWord();

    }

    private void setCursor(){
        switch (pos) {
            case 0:
                cursor = wordDBHelper.recordReadCursor1(db, pos);
                break;
            case 1:
                cursor = wordDBHelper.recordReadCursor2(db, pos);
                break;
        }
    }

    private void printNextWord(){
        numTxt.setText(++num +" / " + cursor.getCount());
        if(cursor.moveToNext()) {
            String eng = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_ENG));
            String kr = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_KR));
            screenEngTxt.setText(eng);
            screenKrTxt.setText(kr);
        }


    }

    void btnInvisibleChecked(){
        if(num == cursor.getCount())
            btnNext.setVisibility(View.INVISIBLE);

        if(num == 1)
            btnBack.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        db.close();
        wordDBHelper.close();
        super.onDestroy();
    }
}
