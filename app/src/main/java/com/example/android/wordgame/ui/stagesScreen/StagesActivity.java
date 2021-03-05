package com.example.android.wordgame.ui.stagesScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.R;
import com.alnamaa.arabic_quiz.ToastMaker;
import com.example.android.wordgame.adapters.StagesAdapter;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.ui.QuizScreen.QuizActivity;

import java.util.List;

public class StagesActivity extends AppCompatActivity  {

    private StagesActivityViewModel viewModel;
    private ProgressBar loadingProgressbar;
    private RecyclerView recyclerViewLevels;
    private TextView textViewPlayerScore;
    private StagesAdapter mAdapter;
    public static int FLAG_START_NEXT_STAGE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        loadingProgressbar = findViewById(R.id.loadingProgressBar);
        ToastMaker.initialize(this,null);
        setUpRecycleView();
        textViewPlayerScore = findViewById(R.id.textViewTotalScore);
        setUpViewModel();
        handleLoadingState();
        handleSettingPlayerScore();
        handleConnectionError();

        setStages();
    }

    private void setUpViewModel(){
        viewModel = new ViewModelProvider(this).get(StagesActivityViewModel.class);
    }

    private void setStages(){
        viewModel.getStages().observe(this, new Observer<List<Stage>>() {
            @Override
            public void onChanged(List<Stage> stages) {
                mAdapter = new StagesAdapter(stages, new StagesAdapter.OnStageClickListener() {
                    @Override
                    public void stageClicked(Stage stage, int i) {

                        clickingStage(stage,i);

                    }
                });
                recyclerViewLevels.setAdapter(mAdapter);
            }
        });
    }

    private void setUpRecycleView(){
        recyclerViewLevels = findViewById(R.id.recyclerViewStages);

        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        recyclerViewLevels.setLayoutManager(layoutManager);

        recyclerViewLevels.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        //layoutManager.setReverseLayout(true);

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

    private void handleConnectionError(){
        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastMaker.showMessage(s);
            }
        });
    }

    private void handleSettingPlayerScore(){
        viewModel.getPlayerScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer score) {
                textViewPlayerScore.setText(""+score);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 1
        if(requestCode == FLAG_START_NEXT_STAGE)
        {
            if(data != null) {
                int i = viewModel.getPlayingStage();
                i++;
                clickingStage(mAdapter.getStage(i), i);
            }
            //get the result
        }
    }

    private void clickingStage(Stage stage ,int i){
        Intent intent = new Intent(StagesActivity.this,QuizActivity.class);
        intent.putExtra(QuizActivity.EXTRA_STAGE_ID,stage.getID());
        viewModel.setPlayingStageIndex(i);
        startActivityForResult(intent, FLAG_START_NEXT_STAGE);
    }

}

class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}