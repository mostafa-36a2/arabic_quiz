package com.example.android.wordgame.models;

public class Choice {
    private String choice;
    private boolean isCorrect;

    public Choice(String choice, boolean isCorrect) {
        this.choice = choice;
        this.isCorrect = isCorrect;
    }

    public String getChoice() {
        return choice;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
