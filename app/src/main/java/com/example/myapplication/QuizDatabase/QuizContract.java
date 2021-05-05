package com.example.myapplication.QuizDatabase;

import android.provider.BaseColumns;

public final class QuizContract implements BaseColumns {

    private QuizContract() {
    }

    //JpnTable
    public static class JpnWordTable{
        public static final String _ID = "_id";
        public static final String TABLE_NAME="jpn_word_table";
        public static final String COLUMN_WORD="jpn_word";
        public static final String COLUMN_SENTENCE="jpn_sentence";
        public static final String COLUMN_SENTENCE_TRANSLATION="jpn_sentence_translation";

        public static final String COLUMN_OPTION_1="option_1";
        public static final String COLUMN_OPTION_2="option_2";
        public static final String COLUMN_OPTION_3="option_3";
        public static final String COLUMN_ANSWER_NR="answer_nr";
        //chi
        public static final String TABLE_NAME_CHI="chi_word_table";
        public static final String CHI_WORD="chi_word";
        public static final String CHI_SENTENCE="chi_sentence";
        public static final String CHI_SENTENCE_TRANSLATION="chi_sentence_translation";

        public static final String CHI_OPTION_1="chi_option_1";
        public static final String CHI_OPTION_2="chi_option_2";
        public static final String CHI_OPTION_3="chi_option_3";
        public static final String CHI_ANSWER_NR="chi_answer_nr";

    }

    //ChiTable

    public static class ChiWordTable {
            public static final String _ID = "_id";
            public static final String TABLE_NAME = "chi_word_table";
            public static String COLUMN_WORD = "chi_word";
            public static String COLUMN_SENTENCE = "chi_sentence";
            public static String COLUMN_SENTENCE_TRANSLATION = "chi_sentence_translation";

            public static String COLUMN_OPTION1 = "option1";
            public static String COLUMN_OPTION2 = "option2";
            public static String COLUMN_OPTION3 = "option3";
            public static String COLUMN_ANSWER_NR = "answer_nr";
        }
}


    //    //JpnTable
    //    public static class JpnWordTable {
    //
    //        public static final String TABLE_NAME = "JpnQuizWords";
    //        public static final String _ID = "ID";
    //        public static final String COLUMN_WORD = "word";
    //
    //        public static final String COLUMN_SENTENCE = "sentence";
    //        public static final String COLUMN_SENTENCE_TRANSLATION = "translation";
    //
    //        public static final String COLUMN_OPTION1 = "option1";
    //        public static final String COLUMN_OPTION2 = "option2";
    //        public static final String COLUMN_OPTION3 = "option3";
    //        public static final String COLUMN_ANSWER_NR = "answer_nr";
    //    }
    //}
