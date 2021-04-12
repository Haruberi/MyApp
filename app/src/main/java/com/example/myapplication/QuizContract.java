package com.example.myapplication;

import android.provider.BaseColumns;

public final class QuizContract implements BaseColumns {

    private QuizContract(){}

    public static class WordTable {

        public static final String TABLE_NAME="quiz_words";
        public static final String _ID="ID";
        public static final String COLUMN_WORD="word";

        public static final String COLUMN_SENTENCE="word_sentence";
        public static final String COLUMN_SENTENCE_TRANSLATION="sentence_translation";

        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_ANSWER_NR="answer_nr";


    }
}
