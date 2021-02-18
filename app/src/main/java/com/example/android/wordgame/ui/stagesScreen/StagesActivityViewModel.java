package com.example.android.wordgame.ui.stagesScreen;

import android.util.Log;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnamaa.arabic_quiz.MyLogger;
import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.ConnectionResponse;
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

    private Repository repo;
    private static final String TAG = "StagesActivityViewModel";
    private MutableLiveData<List<Stage>> mutableStages = new MutableLiveData<>();
    private List<StageManager> stageManagers = new ArrayList<>();
    private MutableLiveData<Boolean> loadingProgressBar = new MutableLiveData<>();
    private MutableLiveData<String>errorMessage =new MutableLiveData<>();
    private MutableLiveData<Integer>playerScore = new MutableLiveData<>();
    private int processesWorking =0 ;
    public LiveData<Integer> getPlayerScore() {
        return playerScore;
    }

    public StagesActivityViewModel() {
        repo = new Repository();
        fetchStages();
        fetchTotalScore();

    }

    private void fetchStages(){
        processesWorking++;
        loadingProgressBar.setValue(processesWorking!= 0);
        Log.i(TAG, "fetchStages: progress bar is on");
         repo.getStages(new Connectivity.ResponseHandler() {
             @Override
             public void handleResponse(ConnectionResponse response) {
                 processesWorking--;
                 loadingProgressBar.setValue(processesWorking!=0);
                 if(response.getResponse() != null) {
                     Gson gson = new Gson();
                     Log.i(TAG, "handleResponse: "+response.getResponse());
                     Stage[] stages = gson.fromJson(response.getResponse(),Stage[].class);
                     for (Stage stage :stages) {
                         stageManagers.add(new StageManager(stage));
                     }
                     mutableStages.setValue(Arrays.asList(stages));

                 }
                 else
                 {
                     errorMessage.setValue(response.getErrorMessage());
                 }
             }
         });


    }

    public LiveData<Boolean> getLoadingState(){
        return loadingProgressBar;
    }

    public LiveData<List<Stage>> getStages() {
        return mutableStages;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    private void fetchTotalScore(){
        processesWorking++;
        loadingProgressBar.setValue(processesWorking!=0);
        repo.getPlayerTotalScore(1, new Connectivity.ResponseHandler() {
            @Override
            public void handleResponse(ConnectionResponse response) {
                loadingProgressBar.setValue(--processesWorking!= 0);
                //playerScore.setValue(Integer.valueOf(response.getResponse()));
                MyLogger.printAndStore("total score is : " +response.getResponse());
            }
        });
    }
}
