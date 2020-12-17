package com.example.android.wordgame.ui.stagesScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.wordgame.StageManager;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.repositories.Repository;

import java.util.List;

public class StagesActivityViewModel extends ViewModel {

    private int playerScore;
    private Repository repo;
    private List<StageManager> managers;
    private MutableLiveData<List<Stage>> mutableStages = new MutableLiveData<>();

    public StagesActivityViewModel() {
        repo = new Repository();
        //quizLevels = new MutableLiveData<>();
        setPlayerScore();
        setStages();
    }

    private void setStages(){
        //TODO (1) : set quiz levels
    }
    private void setPlayerScore(){
        //TODO (2) : set player score
    }

    public LiveData<List<Stage>> getStages(){
        return mutableStages;
    }

    public int getPlayerScore(){
        return playerScore;
    }

}
