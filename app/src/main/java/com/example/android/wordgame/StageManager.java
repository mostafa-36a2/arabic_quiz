package com.example.android.wordgame;

import com.example.android.wordgame.models.Stage;

import java.util.List;

public class StageManager {
    private QuestionManager questionManager;
    private List<Stage> stages;
    private int activeStageIndex ;


    public boolean isStageLocked(int index){
        //TODO : stage locking or not
        return false;
    }

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
}
