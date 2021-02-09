package com.example.android.wordgame;

public class ConnectionResponse {
    private String ErrorMessage;
    private String response;

    public ConnectionResponse(String errorMessage, String response) {
        ErrorMessage = errorMessage;
        this.response = response;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getResponse() {
        return response;
    }
}
