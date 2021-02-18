package com.example.android.wordgame.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.wordgame.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Repository {

    private final static String questionURI = "https://follow.leafwaysco.com/arabic/query_question.php";
    private final static String stagesURI = "https://follow.leafwaysco.com/arabic/stages.php";
    private final static String postPlayerScoreURI = "https://follow.leafwaysco.com/arabic/set_score.php";
    private final static String getPlayerScoreURI = "https://follow.leafwaysco.com/arabic/get_score.php";

    public static final String PLAYER_SCORE_KEY = "player.score.key";
    public static final String SHARED_KEY = "shared.key";


    public void getQuestions(int stageID, Connectivity.ResponseHandler response) {

        Connectivity.CommunicationWithAPI(questionURI + "?stage_id=" + stageID, null, response);
    }


    public void getStages(Connectivity.ResponseHandler response) {
        Connectivity.CommunicationWithAPI(stagesURI, null, response);
    }

    public void getPlayerTotalScore(int userID, Connectivity.ResponseHandler responseHandler) {
        Connectivity.CommunicationWithAPI(getPlayerScoreURI+"?user_id="+userID,null,responseHandler);
    }

    public void postPlayerScore(int playerID, int stageID, int score, Connectivity.ResponseHandler responseHandler) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("user_id", playerID);
            jsonObject.put("stage_id", playerID);
            jsonObject.put("score", playerID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Connectivity.CommunicationWithAPI(postPlayerScoreURI,jsonObject.toString(),responseHandler );
    }


}
