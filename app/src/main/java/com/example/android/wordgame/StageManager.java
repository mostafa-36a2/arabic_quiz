package com.example.android.wordgame;

import android.icu.text.MessagePattern;
import android.os.AsyncTask;

import com.example.android.wordgame.models.Stage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StageManager {
    private QuestionManager questionManager;
    private Stage stage;


    public StageManager(Stage stage){
        this.stage = stage;
    }

    public Stage getStages() {
        return stage;
    }

    public boolean isStageLocked(int index){
        //TODO : stage locking or not
        return false;
    }


    public void initialQuestionManager(String json){
        QuestionManager.build(json);
    }

    public void buildQuiz(){
        //TODO : build quiz

    }

    public int getStageTotalPoints(){
        //TODO : total points of stage
        return 0;
    }

    public int getStageEarnedPoints(){
        //TODO : stage earned points
        return  0;
    }

    public void updateStageEarnedPoints(int points){
        //TODO : updateStageEarnedPoints
    }


    public QuestionManager getQuestionManager() {
        return questionManager;
    }




}
