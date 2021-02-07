package com.example.android.wordgame.ui.QuizScreen;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alnamaa.arabic_quiz.MyLogger;
import com.alnamaa.arabic_quiz.R;
import com.alnamaa.arabic_quiz.ToastMaker;
import com.example.android.wordgame.models.Question;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private QuizActivityViewModel viewModel;
    private CountDownTimer timer;

    private Button buttonChoiceA;
    private Button buttonChoiceB;
    private Button buttonChoiceC;
    private Button buttonChoiceD;
    private TextView questionTv;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToastMaker.initialize(this, null);

        setContentView(R.layout.activity_quiz);
        setUpViews();
        setTimer();
        initialViewModel();
        setQuestion();
    }

    @Override
    public void onClick(View view) {
        String answer = ((Button) view).getText().toString();

        boolean correct = viewModel.answerQuestion(answer);
        if (correct) {
            ToastMaker.showMessage(answer +"is : correct answer");
        } else {
            ToastMaker.showMessage(answer+" : wrong answer");
        }
        viewModel.nextQuestion();
    }

    private void initialViewModel() {
        viewModel = new ViewModelProvider(this).get(QuizActivityViewModel.class);
    }

    private void setQuestion() {
        viewModel.getQuestion().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                if (question != null) {
                    //TODO display the question

                    questionTv.setText(question.getQuestion());

                    buttonChoiceA.setText(question.getChoices().get(0).getChoice());
                    buttonChoiceB.setText(question.getChoices().get(1).getChoice());
                    if(question.getChoices().size() <3)return;
                    buttonChoiceC.setText(question.getChoices().get(2).getChoice());
                    buttonChoiceD.setText(question.getChoices().get(3).getChoice());
                    startTimer();
                } else {
                    timer.cancel();
                    textViewTimer.setText(String.valueOf(0));
                    showQuizEndDialog();
                }
            }
        });
    }


    private void setUpViews() {
        buttonChoiceA = findViewById(R.id.buttonChoiceA);
        buttonChoiceA.setOnClickListener(this);
        buttonChoiceB = findViewById(R.id.buttonChoiceB);
        buttonChoiceB.setOnClickListener(this);
        buttonChoiceC = findViewById(R.id.buttonChoiceC);
        buttonChoiceC.setOnClickListener(this);
        buttonChoiceD = findViewById(R.id.buttonChoiceD);
        buttonChoiceD.setOnClickListener(this);
        questionTv = findViewById(R.id.textViewQuestion);
        textViewTimer = findViewById(R.id.textViewTimer);
    }

    private void setTimer() {
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(QuizActivity.this, "time is out", Toast.LENGTH_SHORT).show();
                viewModel.nextQuestion();
            }
        };
    }

    private void startTimer() {
        timer.cancel();
        timer.start();
    }

    private void showQuizEndDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.end_quiz_dialog, null, false);
        builder = builder.setView(dialogView);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialogView.findViewById(R.id.buttonDialogHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialogView.findViewById(R.id.buttonDialogNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuizActivity.this, "not implemented yet", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
//        dialogView.findViewById(R.id.buttonDialogRetry).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewModel.startQuiz();
//                dialog.cancel();
//            }
//        });


    }

}