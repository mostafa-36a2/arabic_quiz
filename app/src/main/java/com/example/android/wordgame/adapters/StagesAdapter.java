package com.example.android.wordgame.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.models.Stage;
import com.example.android.wordgame.ui.QuizScreen.QuizActivity;
import java.util.List;


//TODO not complete

public class StagesAdapter extends RecyclerView.Adapter<StagesAdapter.VH> {
    private List<Stage> stages;
    private static final String TAG = "StagesAdapter";

    public StagesAdapter(List<Stage> levelList) {
        this.stages = levelList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_stage,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindData(stages.get(position));
    }

    @Override
    public int getItemCount() {
        if(stages == null)
            return 0;
        return stages.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        private TextView textViewLevelNumber;
        private TextView textViewLevelCollectedScore;
        private TextView textViewTotalLevelScore;

        public VH(@NonNull View itemView) {
            super(itemView);
            textViewLevelNumber = itemView.findViewById(R.id.textViewLevelNumber);
            textViewLevelCollectedScore = itemView.findViewById(R.id.textViewLevelCollectedScore);
            textViewTotalLevelScore = itemView.findViewById(R.id.textViewTotalLevelPoints);
        }
        private void bindData(Stage stage){
        }

        private void setLocked(){

        }

        private void setOpen(Stage stage){
            textViewLevelNumber.setText(String.valueOf(stage.getStageID()));
        }


    }
}
