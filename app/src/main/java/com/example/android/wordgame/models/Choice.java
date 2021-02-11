package com.example.android.wordgame.models;

public class Choice {
    private String choice;
    private String status;

    public Choice(String choice, boolean isCorrect) {
        this.choice = choice;
        this.status =(isCorrect?"1":"0");
    }

    public String getChoice() {
        return choice;
    }

    public boolean isCorrect() {
        return status.equals("1");
    }
}
