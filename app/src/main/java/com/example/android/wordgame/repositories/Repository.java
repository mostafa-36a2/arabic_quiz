package com.example.android.wordgame.repositories;

import com.example.android.wordgame.Dummy;
import com.example.android.wordgame.models.Question;
import com.example.android.wordgame.models.QuizLevel;

import java.util.List;

public class Repository {

    public List<Question> getQuestion(){
        return Dummy.dummyQuestion();
    }


    public List<QuizLevel> getQuizLevel(){
        return Dummy.dummyQuizLevels();
    }

    public int getPlayerScore(){
        return 150;
    }
}
