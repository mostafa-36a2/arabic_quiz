package com.example.android.wordgame.ui.stagesScreen;

import android.util.Log;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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



    public StagesActivityViewModel() {
        repo = new Repository();
        fetchStages();

    }

    private void fetchStages(){
        loadingProgressBar.setValue(true);
        Log.i(TAG, "fetchStages: progress bar is on");
         repo.getStages(new Connectivity.ResponseHandler() {
             @Override
             public void handleResponse(ConnectionResponse response) {
                 loadingProgressBar.setValue(false);
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
}
