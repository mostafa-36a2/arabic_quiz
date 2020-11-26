package com.alnamaa.arabic_quiz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.Connectivity;
import com.alnamaa.arabic_quiz.R;

public class QuestionActivity extends AppCompatActivity {

    TextView question_tv;
    Button choice1_b;
    Button choice2_b;
    Button choice3_b;
    Button choice4_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question_tv = findViewById(R.id.question_tv);

        choice1_b = findViewById(R.id.choice1_b);
        choice2_b = findViewById(R.id.choice2_b);
        choice3_b = findViewById(R.id.choice3_b);
        choice4_b = findViewById(R.id.choice4_b);

        //Connectivity.CommunicationWithAPI();
    }
}