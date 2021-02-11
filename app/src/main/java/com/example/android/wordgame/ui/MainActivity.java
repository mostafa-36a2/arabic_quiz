package com.example.android.wordgame.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.MainActivityViewModel;
import com.example.android.wordgame.ui.QuizScreen.QuizActivityViewModel;
import com.example.android.wordgame.ui.stagesScreen.StagesActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private TextView textViewPlayerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton button =findViewById(R.id.buttonStart);
        initialViewModel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, StagesActivity.class);
                startActivity(i);

            }
        });

        setPlayerScore();
    }

    private void setPlayerScore(){
        int score =viewModel.getPlayerScore(this);
        textViewPlayerScore.setText(""+score);
    }


    private void initialViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }
}