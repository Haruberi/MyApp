package com.example.myapplication;

public class TheWord {
    private String theWord;
    private String wordSentence;
    private String sentenceTranslation;

    private String option1;
    private String option2;
    private String option3;
    private int answerNr;

    public TheWord(){}

    public TheWord(String theWord, String sentenceTranslation, String option1, String option2, String option3, int answerNr) {
        this.theWord = theWord;
        this.sentenceTranslation = sentenceTranslation;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
    }
    //theWord

    public String getTheWord() {
        return theWord;
    }
    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    //sentence translation

    public String getSentenceTranslation() {
        return sentenceTranslation;
    }

    public void setSentenceTranslation(String sentenceTranslation) {
        this.sentenceTranslation = sentenceTranslation;
    }
    //Quiz option1

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }
    //Quiz option 2

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    //quiz option 3

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
    //answer nr ...

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
