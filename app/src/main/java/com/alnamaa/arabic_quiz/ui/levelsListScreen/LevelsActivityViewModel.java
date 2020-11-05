package com.alnamaa.arabic_quiz.ui.levelsListScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.Dummy;
import com.alnamaa.arabic_quiz.models.QuizLevel;
import com.alnamaa.arabic_quiz.repositories.Repository;

import java.util.List;

public class LevelsActivityViewModel extends ViewModel {

    private MutableLiveData<List<QuizLevel>> quizLevels;
    private int playerScore;
    private Repository repo;

    public LevelsActivityViewModel() {
        repo = new Repository();
        quizLevels = new MutableLiveData<>();
        setPlayerScore();
        setQuizLevels();
    }

    private void setQuizLevels(){
        //TODO (1) : set quiz levels
        quizLevels.setValue(repo.getQuizLevel());
    }
    private void setPlayerScore(){
        //TODO (2) : set player score
        playerScore = repo.getPlayerScore();
    }

    public LiveData<List<QuizLevel>> getQuizLevels(){
        return  quizLevels;
    }

    public int getPlayerScore(){
        return playerScore;
    }

}
