package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        btnListener();

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

        screenEngTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,55);
        screenKrTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

    }

    void btnListener(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printNextWord();
                btnInvisibleChecked();
                btnBack.setVisibility(View.VISIBLE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printBackWord();
                btnInvisibleChecked();
                btnNext.setVisibility(View.VISIBLE);

            }
        });

        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordDBHelper.dbInsert(db, WordContract.WordEntry.TABLE_MY,
                        screenEngTxt.getText().toString(),screenKrTxt.getText().toString());
                Toast.makeText(wordScreen.this, "나의 단어장에 저장되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCursor(){
        switch (pos) {
            case 0:
                cursor = wordDBHelper.recordReadCursor1(db);
                break;
            case 1:
                cursor = wordDBHelper.recordReadCursor2(db);
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

    private void printBackWord(){
        numTxt.setText(--num + " / " + cursor.getCount());
        if(cursor.moveToPrevious()){
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
