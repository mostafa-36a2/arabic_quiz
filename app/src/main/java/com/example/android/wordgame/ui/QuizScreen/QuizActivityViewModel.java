package com.example.android.wordgame.ui.QuizScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.MyLogger;
import com.example.android.wordgame.ConnectionResponse;
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
    private MutableLiveData<Boolean> loadingState = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();




    public QuizActivityViewModel() {
       repo = new Repository();
    }

    public LiveData<Question> getQuestion(){
        return displayedQuestion;
    }

    private void fetchQuestions(int stageID){
        loadingState.setValue(true);
        repo.getQuestions(stageID,new Connectivity.ResponseHandler() {
            @Override
            public void handleResponse(ConnectionResponse response) {
                loadingState.setValue(false);
                if(response.getResponse() != null) {
                    MyLogger.printAndStore("response is : " + response);
                    questionManager = QuestionManager.build(response.getResponse());
                    nextQuestion();


                }
                else
                {
                    errorMessage.setValue(response.getErrorMessage());
                }
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

    public void startQuiz(int stageId){
        fetchQuestions(stageId);
    }


    public LiveData<Boolean> getLoadingState(){
        return loadingState;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
