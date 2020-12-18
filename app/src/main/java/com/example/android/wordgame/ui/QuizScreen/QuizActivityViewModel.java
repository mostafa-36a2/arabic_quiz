package com.example.android.wordgame.ui.QuizScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.QuestionManager;
import com.example.android.wordgame.StageManager;
import com.example.android.wordgame.models.Question;
import com.example.android.wordgame.repositories.Repository;

import java.util.Collections;
import java.util.List;

public class QuizActivityViewModel extends ViewModel {

    private Repository repo;
    StageManager manager;

    public QuizActivityViewModel() {
       repo = new Repository();
       //stage manager must be initialzed before
    }

    public boolean answerQuestion(String answer) {
       return manager.getQuestionManager().answerQuestion(answer);
    }


    public void nextQuestion() {
        Question question = manager.getQuestionManager().nextQuestion();
    }




}
