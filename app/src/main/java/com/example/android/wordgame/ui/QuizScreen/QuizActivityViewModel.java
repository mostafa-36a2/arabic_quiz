package com.example.android.wordgame.ui.QuizScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.MyLogger;
import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.QuestionManager;
import com.example.android.wordgame.StageManager;
import com.example.android.wordgame.models.Question;
import com.example.android.wordgame.repositories.Repository;

import java.util.Collections;
import java.util.List;

public class QuizActivityViewModel extends ViewModel {

    private Repository repo;
    private QuestionManager questionManager;
    private MutableLiveData<Question> displayedQuestion = new MutableLiveData<>();

    public QuizActivityViewModel() {
       repo = new Repository();

       //stage manager must be initialzed before
        fetchQuestions();
    }

    public LiveData<Question> getQuestion(){
        return displayedQuestion;
    }

    private void fetchQuestions(){
        repo.getQuestions(new Connectivity.ResponseHandler() {
            @Override
            public void handleResponse(String response) {
                MyLogger.printAndStore("response is : "+response);
                questionManager = QuestionManager.build(response);
                nextQuestion();
            }
        });
    }

    public boolean answerQuestion(String answer) {
        return questionManager.answerQuestion(answer);
    }


    public void nextQuestion() {
        Question question = questionManager.nextQuestion();
        displayedQuestion.setValue(question);
    }




}
