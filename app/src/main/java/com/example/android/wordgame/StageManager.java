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
    private List<Stage> stages;
    private int activeStageIndex ;

    public List<Stage> getStages() {
        return stages;
    }

    public boolean isStageLocked(int index){
        //TODO : stage locking or not
        return false;
    }

    public void setActiveStageIndex(int index){  activeStageIndex = index; }

    public void initialQuestionManager(String json){
        QuestionManager.build(json);
    }

    public void buildQuiz(int numOfQuestions){
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

    private Stage getActiveStage(){
        return stages.get(activeStageIndex);
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    public static StageManager build(String json){
        Gson gson = new Gson();
        Stage[] stages1 = gson.fromJson(json,Stage[].class);

        StageManager stageManager =new StageManager(stages1);

        return stageManager;
    }

    private StageManager(Stage[] listOfStages){
        stages = Arrays.asList(listOfStages);
        activeStageIndex = -1;
        questionManager = null;
    }



}
