package com.alnamaa.arabic_quiz.ui.levelsListScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.adapters.QuizLevelsAdapter;
import com.alnamaa.arabic_quiz.R;
import com.alnamaa.arabic_quiz.models.QuizLevel;

import java.util.List;

public class LevelsActivity extends AppCompatActivity {

    private com.alnamaa.arabic_quiz.ui.levelsListScreen.LevelsActivityViewModel viewModel;

    private RecyclerView recyclerViewLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        setUpRecycleView();
        setUpViewModel();
        setLevels();
        setPlayerScore();
    }

    private void setUpViewModel(){
        viewModel = new ViewModelProvider(this).get(com.alnamaa.arabic_quiz.ui.levelsListScreen.LevelsActivityViewModel.class);
    }

    private void setLevels(){
        viewModel.getQuizLevels().observe(this, new Observer<List<QuizLevel>>() {
            @Override
            public void onChanged(List<QuizLevel> quizLevels) {
                int playerScore = viewModel.getPlayerScore();
                QuizLevelsAdapter mAdapter = new QuizLevelsAdapter(quizLevels,playerScore);
                recyclerViewLevels.setAdapter(mAdapter);
            }
        });
    }

    private void setUpRecycleView(){
        recyclerViewLevels = findViewById(R.id.recyclerViewQuizLevels);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(this,3));

    }

    private void setPlayerScore(){
        TextView textViewPlayerTotalScore = findViewById(R.id.textViewPlayerTotalPoints);
        textViewPlayerTotalScore.setText(String.valueOf(viewModel.getPlayerScore()));
    }
}