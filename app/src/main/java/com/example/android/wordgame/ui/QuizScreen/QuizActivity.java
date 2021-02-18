package com.example.android.wordgame.ui.QuizScreen;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private TextView textViewQuestionScore;
    private TextView textViewTotalScore;

    private ProgressBar loadingProgressBar;
    public static final String EXTRA_STAGE_ID = "extra.stage.id";
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ToastMaker.initialize(this, null);

        setUpViews();
        initialViewModel();
        handleConnectionError();
        loadingHandling();
        handleScoring();
        setQuestion();
        textViewQuestionScore = findViewById(R.id.textViewQuestionScore);
        int id = getIntent().getIntExtra(EXTRA_STAGE_ID, 0);
        viewModel.startQuiz(id);

    }


    @Override
    public void onClick(View view) {
        answerQuestion(view);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewModel.nextQuestion();
        }
    };

    public void run_codes() {
        handler.postDelayed(runnable, 2 * 1000);

    }

    private void initialViewModel() {
        viewModel = new ViewModelProvider(this).get(QuizActivityViewModel.class);
    }

    private void setQuestion() {
        viewModel.getQuestion().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                boolean correct;
                if (question != null) {
                    MyLogger.printAndStore("ques : " + question.getQuestion());
                    questionTv.setText(question.getQuestion());
                    if (question.getTimer() > 0)
                        startTimer(question.getTimer());
                    buttonChoiceA.setText(question.getChoices().get(0).getChoice());
                    correct = question.getChoices().get(0).isCorrect();
                    buttonChoiceA.setTag(correct);
                    buttonChoiceB.setText(question.getChoices().get(1).getChoice());
                    correct = question.getChoices().get(1).isCorrect();
                    buttonChoiceB.setTag(correct);
                    int color1 = getResources().getColor(R.color.PNG);
                    buttonChoiceA.setBackgroundColor(color1);
                    buttonChoiceB.setBackgroundColor(color1);
                    textViewQuestionScore.setText("" + question.getScore());

                    if (question.getChoices().size() < 3) return;
                    buttonChoiceC.setText(question.getChoices().get(2).getChoice());
                    correct = question.getChoices().get(2).isCorrect();
                    buttonChoiceC.setTag(correct);
                    buttonChoiceD.setText(question.getChoices().get(3).getChoice());
                    correct = question.getChoices().get(3).isCorrect();
                    buttonChoiceD.setTag(correct);
                    buttonChoiceC.setBackgroundColor(color1);
                    buttonChoiceD.setBackgroundColor(color1);

                } else {
                    if (timer != null)
                        timer.cancel();
                    textViewTimer.setText(String.valueOf(0));
                    showQuizEndDialog();
                }

            }
        });
    }

    private void loadingHandling() {
        viewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {

                if (loading) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    buttonChoiceA.setVisibility(View.INVISIBLE);
                    buttonChoiceB.setVisibility(View.INVISIBLE);
                    buttonChoiceC.setVisibility(View.INVISIBLE);
                    buttonChoiceD.setVisibility(View.INVISIBLE);
                    questionTv.setVisibility(View.INVISIBLE);
                    textViewTimer.setVisibility(View.INVISIBLE);

                } else {
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    buttonChoiceA.setVisibility(View.VISIBLE);
                    buttonChoiceB.setVisibility(View.VISIBLE);
                    buttonChoiceC.setVisibility(View.VISIBLE);
                    buttonChoiceD.setVisibility(View.VISIBLE);
                    questionTv.setVisibility(View.VISIBLE);
                    textViewTimer.setVisibility(View.VISIBLE);
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
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        textViewQuestionScore = findViewById(R.id.textViewQuestionScore);
        textViewTotalScore = findViewById(R.id.textViewTotalScore);

    }

    private void startTimer(int time) {

        if (timer != null)
            timer.cancel();

        timer = new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(QuizActivity.this, "time is out", Toast.LENGTH_SHORT).show();
                answerQuestion(null);
            }
        };
        timer.start();
    }

    private void showQuizEndDialog() {
        //here
        int earnedScore = viewModel.getTotalEarnedScore();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.end_quiz_dialog, null, false);
        builder = builder.setView(dialogView);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.textViewDialogMessage)).setText("" + earnedScore);
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

        dialogView.findViewById(R.id.buttonDialogRetry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.resetQuiz();
                dialog.cancel();

            }
        });


    }

    private void handleConnectionError() {
        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastMaker.showMessage(s);
                finish();
            }
        });
    }

    private void handleScoring() {
        viewModel.getEarnedScoreMutableData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer score) {
                textViewTotalScore.setText("" + score + "نقطة");
            }
        });
    }

    private void markCorrectAnswers() {
        int correctColor = getResources().getColor(R.color.correctAnswer);
        ;
        boolean correct;
        correct = (boolean) buttonChoiceA.getTag();
        if (correct) {
            buttonChoiceA.setBackgroundColor(correctColor);
        }

        correct = (boolean) buttonChoiceB.getTag();
        if (correct) {
            buttonChoiceB.setBackgroundColor(correctColor);
        }

        correct = (boolean) buttonChoiceC.getTag();
        if (correct) {
            buttonChoiceC.setBackgroundColor(correctColor);
        }

        correct = (boolean) buttonChoiceD.getTag();
        if (correct) {
            buttonChoiceD.setBackgroundColor(correctColor);
        }


    }

    private void answerQuestion(View view) {

        String answer = ((Button) view).getText().toString();
        int color;
        Animation animShake;
        boolean correct = viewModel.answerQuestion(answer);
        if (correct) {
            color = getResources().getColor(R.color.correctAnswer);
            animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            view.startAnimation(animShake);
        } else {
            color = getResources().getColor(R.color.wrongAnswer);
            animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            view.startAnimation(animShake);
            markCorrectAnswers();
        }

        view.setBackgroundColor(color);
        run_codes();
    }

}