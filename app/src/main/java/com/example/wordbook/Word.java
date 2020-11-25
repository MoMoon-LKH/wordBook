package com.example.wordbook;

public class Word {
    private String engWord;
    private String krWord;

    Word(String engWord, String krWord){
        this.engWord = engWord;
        this.krWord = krWord;
    }

    public String getEngWord() {
        return engWord;
    }

    public String getKrWord() {
        return krWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }

    public void setKrWord(String krWord) {
        this.krWord = krWord;
    }
}
