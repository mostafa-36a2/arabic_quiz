package com.alnamaa.arabic_quiz;

import com.alnamaa.arabic_quiz.models.Question;
import com.alnamaa.arabic_quiz.models.QuizLevel;

import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static List<Question> dummyQuestion(){
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("who are you","CORRECT","BB","CC","D",'a'));
        questions.add(new Question("who made this","1","CORRECT","3","4",'b'));
        questions.add(new Question("what are you doing","hi","df","sd","CORRECT",'d'));

        return questions;
    }

    public static List<QuizLevel> dummyQuizLevels(){
        List<QuizLevel> levelList = new ArrayList<>();
        levelList.add(new QuizLevel(156,50,1,0));
        levelList.add(new QuizLevel(156,30,2,150));
        levelList.add(new QuizLevel(156,20,3,300));
        levelList.add(new QuizLevel(156,50,4,450));
        levelList.add(new QuizLevel(156,120,5,600));
        levelList.add(new QuizLevel(156,100,6,750));
        levelList.add(new QuizLevel(156,50,7,0));
        levelList.add(new QuizLevel(156,30,8,150));
        levelList.add(new QuizLevel(156,20,9,300));
        levelList.add(new QuizLevel(156,50,10,450));
        levelList.add(new QuizLevel(156,120,11,600));
        levelList.add(new QuizLevel(156,100,12,750));
        return levelList;
    }
}