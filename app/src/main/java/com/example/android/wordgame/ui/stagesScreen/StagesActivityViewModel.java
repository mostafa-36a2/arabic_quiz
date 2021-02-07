package com.example.android.wordgame.ui.stagesScreen;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.StageManager;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.repositories.Repository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StagesActivityViewModel extends ViewModel {

    private int playerScore;
    private Repository repo;
    private MutableLiveData<List<Stage>> mutableStages = new MutableLiveData<>();
    private List<StageManager> stageManagers = new ArrayList<>();



    public StagesActivityViewModel() {
        repo = new Repository();
        //setPlayerScore();
        fetchStages();

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
                     Gson gson = new Gson();
                     Stage[] stages = gson.fromJson(response,Stage[].class);
                     for (Stage stage :stages) {
                         stageManagers.add(new StageManager(stage));
                     }
                     mutableStages.setValue(Arrays.asList(stages));
                 }
             }
         });


    }

    public LiveData<List<Stage>> getStages() {
        return mutableStages;
    }

    public void startQuiz(int i){
        //stageManagers.get(i).initialQuestionManager();
    }
}
