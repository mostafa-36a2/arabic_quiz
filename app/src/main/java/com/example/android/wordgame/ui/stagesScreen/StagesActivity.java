package com.example.android.wordgame.ui.stagesScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.adapters.StagesAdapter;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.ui.QuizScreen.QuizActivity;

import java.util.List;

public class StagesActivity extends AppCompatActivity  {

    private StagesActivityViewModel viewModel;
    private ProgressBar loadingProgressbar;
    private RecyclerView recyclerViewLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        loadingProgressbar = findViewById(R.id.loadingProgressBar);
        setUpRecycleView();
        setUpViewModel();
        handleLoadingState();
        setStages();

        Button btn = findViewById(R.id.startQuizBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StagesActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });


    }

    private void setUpViewModel(){
        viewModel = new ViewModelProvider(this).get(StagesActivityViewModel.class);
    }

    private void setStages(){
        viewModel.getStages().observe(this, new Observer<List<Stage>>() {
            @Override
            public void onChanged(List<Stage> stages) {
                StagesAdapter mAdapter = new StagesAdapter(stages, new StagesAdapter.OnStageClickListener() {
                    @Override
                    public void stageClicked(Stage stage, int i) {
                        Intent intent = new Intent(StagesActivity.this,QuizActivity.class);
                        startActivity(intent);
                    }
                });
                recyclerViewLevels.setAdapter(mAdapter);
            }
        });
    }

    private void setUpRecycleView(){
        recyclerViewLevels = findViewById(R.id.recyclerViewStages);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(this,3));

    }


    private void handleLoadingState(){
        viewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if(loading)
                    loadingProgressbar.setVisibility(View.VISIBLE);
                else
                    loadingProgressbar.setVisibility(View.GONE);
            }
        });
    }


}