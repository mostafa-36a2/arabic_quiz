package com.example.android.wordgame.repositories;

import com.example.android.wordgame.Connectivity;
import com.example.android.wordgame.Dummy;
import com.example.android.wordgame.models.Question;
import com.example.android.wordgame.models.Stage;

import java.util.List;

public class Repository {

    private final static String questionURI = "https://follow.leafwaysco.com/arabic/query_question.php";
    private final static String stagesURI = "https://follow.leafwaysco.com/arabic/stages.php";


    public void getQuestions(int stageID,Connectivity.ResponseHandler response){

        Connectivity.CommunicationWithAPI(questionURI+"?stage_id="+stageID,null,response);
    }


    public void getStages(Connectivity.ResponseHandler response){
        Connectivity.CommunicationWithAPI(stagesURI,null,response);
    }

}
