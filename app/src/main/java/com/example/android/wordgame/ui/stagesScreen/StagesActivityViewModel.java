package com.example.android.wordgame.ui.stagesScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.StageManager;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StagesActivityViewModel extends ViewModel {

    private int playerScore;
    private Repository repo;
    private StageManager stageManager;
    private MutableLiveData<List<Stage>> mutableStages = new MutableLiveData<>();

    public StagesActivityViewModel() {
        repo = new Repository();
        setPlayerScore();
        setStages();

    }

    private void setStages(){
        //TODO (1) : set quiz levels
        repo.getStages(new Connectivity.ResponseHandler() {
            @Override
            public void handleResponse(String response) {
                //TODO NOT IMPLEMENTED BUILD STAGE MANAGER FROM RESPONSE
            }
        });
    }
    private void setPlayerScore(){
        //TODO (2) : set player score
    }


    public int getPlayerScore(){
        return playerScore;
    }

    private void fetchStages(){
         repo.getStages(new Connectivity.ResponseHandler() {
             @Override
             public void handleResponse(String response) {
                 if(response != null) {
                     int playerScore = 100;
                     stageManager = StageManager.build(response);
                     List<Stage> stages = stageManager.getStages();
                     mutableStages.setValue(stages);
                 }
             }
         });


    }

    public LiveData<List<Stage>> getStages() {
        return mutableStages;
    }
}
