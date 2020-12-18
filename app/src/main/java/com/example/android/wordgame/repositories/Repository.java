package com.example.android.wordgame.repositories;

import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.Dummy;
import com.example.android.wordgame.models.Question;
import com.example.android.wordgame.models.Stage;

import java.util.List;

public class Repository {

    private final static String questionURI = "";
    private final static String stagesURI = "";


    public void getQuestions(Connectivity.ResponseHandler response){
        Connectivity.CommunicationWithAPI(questionURI,null,response);
    }


    public void getStages(Connectivity.ResponseHandler response){
        Connectivity.CommunicationWithAPI(stagesURI,null,response);
    }

}
