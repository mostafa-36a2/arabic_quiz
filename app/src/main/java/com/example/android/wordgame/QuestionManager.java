package com.example.android.wordgame;

import com.alnamaa.arabic_quiz.MyLogger;
import com.example.android.wordgame.models.Choice;
import com.example.android.wordgame.models.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class QuestionManager {

    private List<Question> listOfQuestion;
    private int currentQuesIndex;


    public boolean answerQuestion(String answer){
        MyLogger.printAndStore("question was : "+getCurrentQuestion().getQuestion());
        return getCurrentQuestion().answer(answer);
    }

    public Question nextQuestion(){
        currentQuesIndex++;
        if(currentQuesIndex == listOfQuestion.size())
            return null;

        List<Choice> choices = buildChoices(4,1);
        return getCurrentQuestion();
    }

    //build choices set for the current question by specify number of choices and number of correct ones
    public List<Choice> buildChoices(int choicesNumber ,int correctNum){

        List<Choice> returnedChoices = new ArrayList<>();
        List<Choice> choices = new ArrayList<Choice>((getCurrentQuestion().getChoices()));
        Collections.shuffle(choices);

        for (Choice choice : choices) {
            if (choice.isCorrect() && correctNum!= 0) {
                returnedChoices.add(choice);
                correctNum--;
            }
            if (!choice.isCorrect() && choicesNumber != 0) {
                returnedChoices.add(choice);
                choicesNumber--;
            }
            if(choicesNumber == 0 &&correctNum == 0)
                break;
        }

        return returnedChoices;
    }

    private Question getCurrentQuestion(){
        return listOfQuestion.get(currentQuesIndex);
    }


    //build questions from jsonString
    public static QuestionManager build (String jsonString){
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(jsonString,Question[].class);
        //MyLogger.printAndStore("questions list fetched : "+ questions[0].getQuestion());

        return new QuestionManager(questions);
    }


    private QuestionManager(Question[] questions){
        listOfQuestion = new ArrayList<>();
        listOfQuestion.addAll(Arrays.asList(questions));
        currentQuesIndex =-1;
    }

}
