package com.alnamaa.arabic_quiz.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private List<String> shuffledAnswers;
    private char answer;

    public Question(String question, String choiceA, String choiceB, String choiceC, String choiceD, char answer) {
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.answer = answer;
        shuffledAnswers = shuffleAnswers();
    }

    public String getQuestion() {
        return question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public String getAnswer() {
        switch (answer) {
            case 'A':
            case 'a':
                return getChoiceA();
            case 'B':
            case 'b':
                return getChoiceB();
            case 'C':
            case 'c':
                return getChoiceC();
            case 'D':
            case 'd':
                return getChoiceD();
            default:
                return "";
        }
    }

    public List<String> getShuffledAnswers() {
        return shuffledAnswers;
    }

    private List<String> shuffleAnswers() {
        String[] temp = new String[]{getChoiceA(), getChoiceB(), getChoiceC(), getChoiceD()};

        List<String> answers = Arrays.asList(temp);
        Collections.shuffle(answers);
        return answers;
    }
}
