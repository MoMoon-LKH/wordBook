package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class wordScreen extends AppCompatActivity {
    TextView toolbarTxt, numTxt, screenEngTxt, screenKrTxt;
    Button btnBack, btnMy, btnNext;
    LinearLayout screenLayout;
    RecyclerView screenRecycler;
    WordRecyclerAdapter adapter;
    WordDBHelper wordDBHelper;
    SQLiteDatabase db;
    int pos;
    int num = 0;
    Cursor cursor;
    Toolbar toolbar;
    Menu menu;

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
        screenRecycler = (RecyclerView) findViewById(R.id.screenRecycler);
        screenLayout = (LinearLayout) findViewById(R.id.screenLayout);
        toolbar = (Toolbar) findViewById(R.id.wordToolbar);


        btnBack.setVisibility(View.INVISIBLE);
        screenRecycler.setVisibility(View.INVISIBLE);

        menu = toolbar.getMenu();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        wordDBHelper = new WordDBHelper(this, WordDBHelper.DB_NAME, null, WordDBHelper.DB_VERSION);
        db = wordDBHelper.getWritableDatabase(); //my table에 단어 저장
        Intent intent = getIntent();

        String txt = intent.getExtras().getString("txt");
        pos = intent.getExtras().getInt("pos");
        toolbarTxt.setText(txt);
        setCursor();
        printNextWord();


        screenEngTxt.setTextSize(55);
        screenKrTxt.setTextSize(32);

        recyclerInit();

    }

    void recyclerInit(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        screenRecycler.setLayoutManager(linearLayoutManager);
        screenRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new WordRecyclerAdapter();
        screenRecycler.setAdapter(adapter);

        getDbData();
        adapter.notifyDataSetChanged();

    }

    void getDbData(){

        do{
            String eng = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_ENG));
            String kr = cursor.getString(cursor.getColumnIndexOrThrow(WordContract.WordEntry.COL_KR));
            adapter.itemAdd(new Word(eng,kr));
        }while(cursor.moveToNext());
        cursor.moveToFirst();
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
                String eng = screenEngTxt.getText().toString();
                String kr = screenKrTxt.getText().toString();
                if(dbWordChecked(eng)){
                    toastDelay(true);
                }else{
                    wordDBHelper.dbInsert(db, WordContract.WordEntry.TABLE_MY, eng, kr);
                    toastDelay(false);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.wordallmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
            case R.id.wordAll :
                menuBtnAll();
                return true;
            case R.id.wordReturnMenu:
                menuBtnReturn();
        }
        return false;
    }

    void menuBtnAll(){
        screenLayout.setVisibility(View.INVISIBLE);
        screenRecycler.setVisibility(View.VISIBLE);
        menu.clear();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.wordbackmenu,menu);



    }

    void menuBtnReturn(){
        screenRecycler.setVisibility(View.INVISIBLE);
        screenLayout.setVisibility(View.VISIBLE);
        menu.clear();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.wordallmenu,menu);

        /*cursor.moveToFirst();
        for(int i = 0; i < num; i++)
            cursor.moveToNext();*/

    }

    boolean dbWordChecked(String eng) { // My 테이블에 이미 있는 단어라면 true / 없다면 false
        Cursor myCursor = wordDBHelper.recordReadCursorMy(db);
        while (myCursor.moveToNext()) {
            Log.d("check","cursor: "+myCursor.getString(myCursor.getColumnIndex(WordContract.WordEntry.COL_ENG)));
            if (myCursor.getString(myCursor.getColumnIndex(WordContract.WordEntry.COL_ENG)).equals(eng)) {
                myCursor.close();
                return true;
            }
        }
        myCursor.close();
        return false;
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

    void toastDelay(boolean check){
        Handler handler = new Handler(Looper.myLooper());

        if(check) {
            final Toast toast = Toast.makeText(wordScreen.this, "이미 저장되어있는 단어입니다.", Toast.LENGTH_SHORT);
            toast.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 900);
        } else {
            final Toast toast = Toast.makeText(wordScreen.this, "나의 단어장에 저장되었습니다", Toast.LENGTH_SHORT);
            toast.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 900);
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
