package com.example.wordbook;

import android.provider.BaseColumns;

public class WordContract {
    private WordContract(){}

    public static class WordEntry implements BaseColumns {
        public static final String TABLE1 = "toe1";
        public static final String TABLE2 = "toe2";
        public static final String TABLE_MY = "my";
        public static final String id = _ID;
        public static final String COL_ENG = "eng";
        public static final String COL_KR = "kr";

        }
}

