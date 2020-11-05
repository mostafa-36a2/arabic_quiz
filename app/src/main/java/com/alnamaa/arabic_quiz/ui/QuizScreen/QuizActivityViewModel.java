package com.alnamaa.arabic_quiz.ui.QuizScreen;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.models.Question;
import com.alnamaa.arabic_quiz.repositories.Repository;

import java.util.Collections;
import java.util.List;

public class QuizActivityViewModel extends ViewModel {
    private List<Question> questions;
    private MutableLiveData<Question> displayedQuestion;
    private int questionPosition;
    private Repository repo;

    public QuizActivityViewModel() {
        repo = new Repository();
        displayedQuestion = new MutableLiveData<>();
        questions = repo.getQuestion();
        questionPosition = -1;
        nextQuestion();
    }

    public boolean answerQuestion(String answer) {
        Question question = displayedQuestion.getValue();
        return question.getAnswer().equals(answer);
    }

    public void startQuiz(){
        questionPosition = -1;
        Collections.shuffle(questions);
        nextQuestion();
    }

    public void nextQuestion() {
        questionPosition++;
        if (questionPosition >= questions.size()) {
            displayedQuestion.setValue(null);
        } else {
            displayedQuestion.setValue(questions.get(questionPosition));
        }
    }

    public LiveData<Question> getQuestion() {
        return displayedQuestion;
    }

}
