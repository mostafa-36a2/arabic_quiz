package com.example.android.wordgame.ui.QuizScreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.wordgame.R;
import com.example.android.wordgame.databinding.ActivityQuizBinding;
import com.example.android.wordgame.models.Question;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private QuizActivityViewModel viewModel;
    private ActivityQuizBinding binding;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        setTimer();
        setUpViews();
        initialViewModel();
        setQuestion();

    }

    @Override
    public void onClick(View view) {
        boolean correct = viewModel.answerQuestion(((Button) view).getText().toString());
        if (correct) {
            //TODO : ANIMATE CORRECT ANSWER
        } else {
            // TODO : ANIMATE WRONG ANSWER
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
                    binding.setQuestion(question);
                    startTimer();
                } else {
                    timer.cancel();
                    binding.textViewTimer.setText(String.valueOf(0));
                    showQuizEndDialog();
                }
            }
        });
    }

    private void setUpViews() {
        binding.buttonChoiceA.setOnClickListener(this);
        binding.buttonChoiceB.setOnClickListener(this);
        binding.buttonChoiceC.setOnClickListener(this);
        binding.buttonChoiceD.setOnClickListener(this);
    }

    private void setTimer() {
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                binding.textViewTimer.setText("" + millisUntilFinished / 1000);
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
        dialogView.findViewById(R.id.buttonDialogRetry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.startQuiz();
                dialog.cancel();
            }
        });


    }
}