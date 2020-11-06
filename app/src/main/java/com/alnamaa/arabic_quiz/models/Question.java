package com.alnamaa.arabic_quiz.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private String question;
    private ArrayList<String> choices;
    private int answerId;//1,2,3,4

    public Question(String question, ArrayList<String> choices, int answerId) {
        this.question = question;
        this.choices = choices;
        this.answerId = answerId;
    }
    public Question(String question,
                    final String choice1,
                    final String choice2,
                    final String choice3,
                    final String choice4,
                    char answerLetter) {
        this.question = question;
        this.choices = new ArrayList<String>(){
            {
                add(choice1);
                add(choice2);
                add(choice3);
                add(choice4);
            }
        };
        switch (answerLetter)
        {
            case 'a': case 'A':
                this.answerId = 1;
                break;
            case 'b': case 'B':
                this.answerId = 2;
                break;
            case 'c': case 'C':
                this.answerId = 3;
                break;
            case 'd': case 'D':
                this.answerId = 4;
                break;
            default:
                this.answerId = 0;
        }
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        switch (answerId) {
            case 1:
                return choices.get(0);
            case 2:
                return choices.get(1);
            case 3:
                return choices.get(2);
            case 4:
                return choices.get(3);
            default:
                return null;
        }
    }

    public List<String> getShuffledAnswers() {
        ArrayList<String> shuffeledChoices = choices;
        Collections.shuffle(shuffeledChoices);
        return shuffeledChoices;
    }
}
