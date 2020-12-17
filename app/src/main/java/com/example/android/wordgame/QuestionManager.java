package com.example.android.wordgame;

import com.example.android.wordgame.models.Choice;
import com.example.android.wordgame.models.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    private List<Question> listOfQuestion;
    private int currentQuesIndex;

    public boolean answerQuestion(String answer){
        return getCurrentQuestion().answer(answer);
    }

    public Question nextQuestion(){
        currentQuesIndex++;
        if(currentQuesIndex == listOfQuestion.size())
            return null;
        return getCurrentQuestion();
    }



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


    public static QuestionManager build (String jsonString){
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(jsonString,Question[].class);
        return new QuestionManager(questions);
    }

    private QuestionManager(Question[] questions){
        listOfQuestion = new ArrayList<>();
        listOfQuestion.addAll(Arrays.asList(questions));
        currentQuesIndex =-1;
    }

}
