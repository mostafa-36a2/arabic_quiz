package com.alnamaa.arabic_quiz;

public class Settings {

    private static String BACKEND = "http://follow.leafwaysco.com/";

    static void toggleHTTPStatus() {
        if(BACKEND.contains("https")){
            BACKEND = BACKEND.replace("https","http");
        }else{
            BACKEND = BACKEND.replace("http","https");
        }
    }
}
