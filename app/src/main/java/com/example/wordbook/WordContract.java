/*
package com.example.wordbook;

import android.provider.BaseColumns;

public class WordContract {
    private WordContract(){}

    public static class WordEntry implements BaseColumns {
        public static final String[] TABLE = {"toeic1","toeic2","my"};
        public static final String COL_ENG = "eng";
        public static final String COL_KR = "kr";
        public String SQL_CREATE_TABLE(int i){
            String sql = "create table if not exists " + TABLE[i] +
                    "(" + _ID + "integer primary key autoincrement," +
                    COL_ENG + "text not null," +
                    COL_KR  + "text not null);";
            return sql;
        }
    }
}
*/
