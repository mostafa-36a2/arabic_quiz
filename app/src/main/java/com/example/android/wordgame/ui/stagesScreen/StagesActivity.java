package com.example.android.wordgame.ui.stagesScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.adapters.StagesAdapter;
import com.example.android.wordgame.models.Stage;

import java.util.List;

public class StagesActivity extends AppCompatActivity {

    private StagesActivityViewModel viewModel;

    private RecyclerView recyclerViewLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        setUpRecycleView();
        setUpViewModel();
        setStages();
        setPlayerScore();
    }

    private void setUpViewModel(){
        viewModel = new ViewModelProvider(this).get(StagesActivityViewModel.class);
    }

    private void setStages(){
        viewModel.getStages().observe(this, new Observer<List<Stage>>() {
            @Override
            public void onChanged(List<Stage> quizLevels) {
                int playerScore = viewModel.getPlayerScore();
                StagesAdapter mAdapter = new StagesAdapter(quizLevels,playerScore);
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