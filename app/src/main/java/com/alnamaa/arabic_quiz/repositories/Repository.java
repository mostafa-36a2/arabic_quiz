package com.alnamaa.arabic_quiz.repositories;

import com.alnamaa.arabic_quiz.Dummy;
import com.alnamaa.arabic_quiz.models.Question;
import com.alnamaa.arabic_quiz.models.QuizLevel;

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
