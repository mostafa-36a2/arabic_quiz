package com.example.android.wordgame;

import com.example.android.wordgame.models.Stage;

public class StageManager {
    private QuestionManager manager;
    private Stage stage;


    public boolean isStageLocked(){
        //TODO : stage locking or not
        return false;
    }
    public int getStageID(){
        return stage.getStageID();
    }

    public void startQuiz(int numOfQuestions){
        //TODO : start quiz
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
}
