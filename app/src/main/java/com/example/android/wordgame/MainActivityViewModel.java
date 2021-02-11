package com.example.android.wordgame;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.wordgame.repositories.Repository;
import com.google.gson.internal.$Gson$Preconditions;

public class MainActivityViewModel extends ViewModel {

    private Repository repo;
    public MainActivityViewModel() {
        repo = new Repository();
    }

    public int getPlayerScore(Context context){
        int score = repo.getPlayerScore(context);
        return score;
    }

}
