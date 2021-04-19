package com.example.myapplication.QuizDatabase;

import android.provider.BaseColumns;

public final class QuizContract implements BaseColumns {

    private QuizContract() {
    }

    //ChiTable
    public static class ChiWordTable {
        public static final String TABLE_NAME = "ChiQuizWords";

        public static final String _ID = "ID";
        public static String COLUMN_WORD = "word";
        public static String COLUMN_SENTENCE = "sentence";
        public static String COLUMN_SENTENCE_TRANSLATION = "translation";

        public static String COLUMN_OPTION1 = "option1";
        public static String COLUMN_OPTION2 = "option2";
        public static String COLUMN_OPTION3 = "option3";
        public static String COLUMN_ANSWER_NR = "answer_nr";
    }


    //JpnTable
    public static class JpnWordTable {

        public static final String TABLE_NAME = "JpnQuizWords";
        public static final String _ID = "ID";
        public static final String COLUMN_WORD = "word";

        public static final String COLUMN_SENTENCE = "sentence";
        public static final String COLUMN_SENTENCE_TRANSLATION = "translation";

        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
