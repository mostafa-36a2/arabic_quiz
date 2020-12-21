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
    private StageManager stageManager;//TODO SET STAGE MANAGER
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
                stageManager.initialQuestionManager(response);
                stageManager.buildQuiz(10);
            }
        });
    }

    public boolean answerQuestion(String answer) {
       return stageManager.getQuestionManager().answerQuestion(answer);
    }


    public void nextQuestion() {
        Question question = stageManager.getQuestionManager().nextQuestion();
        displayedQuestion.setValue(question);
    }




}
