package com.alnamaa.arabic_quiz;

public class MyLogger {
    static StringBuilder stringBuilder;
    public static void printAndStore(String string) {
        System.out.println(string);
        if(stringBuilder==null)
            stringBuilder=new StringBuilder();
        stringBuilder.append(string).append("\n");
    }
    public static String getLog(){
        if(stringBuilder==null)
            return "";
        return stringBuilder.toString();
    }
}
