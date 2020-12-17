package com.example.android.wordgame.models;

import java.util.List;

public class Question {

    private int id;
    private String question;
    private List<Choice>choices;
    private int difficulty;
    private Language language;



    public String getQuestion(){return this.question;}

    public int getId() {
        return id;
    }

    public List<Choice> getChoices() {
        return choices;
    }


    public int getDifficulty() {
        return difficulty;
    }

    public Language getLanguage() {
        return language;
    }

    public boolean answer(String answer){
        for (Choice choice: choices) {

            if(choice.getChoice().equals(answer))
                return choice.isCorrect();

        }
        return false;
    }
}
