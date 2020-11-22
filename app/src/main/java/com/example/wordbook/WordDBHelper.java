package com.example.wordbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class WordDBHelper extends SQLiteOpenHelper {
    public final static String DB_NAME = "wordDB.db";
    public final static int DB_VERSION = 1;


    public WordDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + WordContract.WordEntry.TABLE1 + "(" +
                WordContract.WordEntry.id + " integer primary key autoincrement not null, " +
                WordContract.WordEntry.COL_ENG + " TEXT , " +
                WordContract.WordEntry.COL_KR + " TEXT);");


        db.execSQL("create table if not exists " + WordContract.WordEntry.TABLE2 + "(" +
                WordContract.WordEntry.id + " integer primary key autoincrement not null, " +
                WordContract.WordEntry.COL_ENG + " TEXT, " +
                WordContract.WordEntry.COL_KR + " TEXT);");


        db.execSQL("create table if not exists " + WordContract.WordEntry.TABLE3 + "(" +
                WordContract.WordEntry.id + " integer primary key autoincrement not null, " +
                WordContract.WordEntry.COL_ENG + " TEXT, " +
                WordContract.WordEntry.COL_KR + " TEXT);");

        dbValues(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + WordContract.WordEntry.TABLE1);
        db.execSQL("drop table if exists " + WordContract.WordEntry.TABLE2);
        db.execSQL("drop table if exists " + WordContract.WordEntry.TABLE3);
        onCreate(db);
    }

    void dbValues(SQLiteDatabase db){
        dbInsert(db, WordContract.WordEntry.TABLE1,"resume","n. 이력서");
        dbInsert(db, WordContract.WordEntry.TABLE1,"opening","n. 공석, 결원; 개장, 개시");
        dbInsert(db, WordContract.WordEntry.TABLE1,"applicant","n. 지원자, 신청자");
        dbInsert(db, WordContract.WordEntry.TABLE1,"requirement","n. 필요조건, 요건");
        dbInsert(db, WordContract.WordEntry.TABLE1,"meet","v. (필요·요구 등을) 만족시키다");
        dbInsert(db, WordContract.WordEntry.TABLE1,"qualified","adj. 자격있는 적격의");
        dbInsert(db, WordContract.WordEntry.TABLE1,"candidate","n. 후보자, 지원자");
        dbInsert(db, WordContract.WordEntry.TABLE1,"confidence","n. 확신, 자신; 신임");
        dbInsert(db, WordContract.WordEntry.TABLE1,"highly","adv. 매우, 대단히");
        dbInsert(db, WordContract.WordEntry.TABLE1,"professional","adj. 전문적인, 직업의\nn. 전문가");

        dbInsert(db, WordContract.WordEntry.TABLE2,"interview","n. 면접\nv. 면접을 보다");
        dbInsert(db, WordContract.WordEntry.TABLE2,"hire","v. 고용하다");
        dbInsert(db, WordContract.WordEntry.TABLE2,"training","n. 교육, 훈련");
        dbInsert(db, WordContract.WordEntry.TABLE2,"position","n. 일자리, 직책\nv. 두다");
        dbInsert(db, WordContract.WordEntry.TABLE2,"reference","n. 추천서; 참고");
        dbInsert(db, WordContract.WordEntry.TABLE2,"achievement","n. 성취, 달성");
        dbInsert(db, WordContract.WordEntry.TABLE2,"impressed","adj. 인상 깊게 생각하는\n     감명을 받은");
        dbInsert(db, WordContract.WordEntry.TABLE2,"excellent","adj. 휼륭한, 탁월한");
        dbInsert(db, WordContract.WordEntry.TABLE2,"eligible","adj. 자격이 있는, 적격의");
        dbInsert(db, WordContract.WordEntry.TABLE2,"identify","v. 알아보다");



    }

    void dbInsert(SQLiteDatabase db ,String table, String eng, String kr){
        Log.d("db","insert: " + table +" ,"+ eng +" ,"+ kr );
        ContentValues contentValues = new ContentValues();
        contentValues.put(WordContract.WordEntry.COL_ENG,eng);
        contentValues.put(WordContract.WordEntry.COL_KR,kr);

        db.insert(table,null,contentValues);
    }
}
