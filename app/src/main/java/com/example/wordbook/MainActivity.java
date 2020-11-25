package com.example.wordbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView wordImg, myImg;
    WordDBHelper wordDBHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordImg = (ImageView) findViewById(R.id.wordImg);
        myImg = (ImageView) findViewById(R.id.myImg);
        wordDBHelper = new WordDBHelper(this, WordDBHelper.DB_NAME, null, WordDBHelper.DB_VERSION);
        db = wordDBHelper.getReadableDatabase();
        btnListener();

    }

    void btnListener(){
        wordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.wordImg) {
                    Intent intent = new Intent(getApplicationContext(),wordList.class);
                    startActivity(intent);
                }
            }
        });

        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.myImg) {
                    if(MyTableCount() <= 0)
                        alertDlg();
                    else {
                        Intent intent = new Intent(getApplicationContext(), myWord.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    int MyTableCount(){
        Cursor cursor = db.rawQuery("select * FROM " + WordContract.WordEntry.TABLE_MY,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    void alertDlg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("나의 단어장").setMessage("저장된 단어가 없습니다.");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        db.close();
        wordDBHelper.close();
        super.onDestroy();
    }

}