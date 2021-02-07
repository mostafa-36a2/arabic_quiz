package com.example.android.wordgame.models;

import androidx.annotation.NonNull;

import com.alnamaa.arabic_quiz.MyLogger;

import java.util.List;

public class Question implements Cloneable{

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

            if(choice.getChoice().equals(answer)) {
                MyLogger.printAndStore(choice.getChoice()+" equals : "+answer+"  "+choice.isCorrect());
                return choice.isCorrect();
            }

        }
        return false;
    }



    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
