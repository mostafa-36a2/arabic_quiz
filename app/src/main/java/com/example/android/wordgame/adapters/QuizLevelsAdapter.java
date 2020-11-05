package com.example.android.wordgame.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.wordgame.R;
import com.example.android.wordgame.models.QuizLevel;
import com.example.android.wordgame.ui.QuizScreen.QuizActivity;

import java.util.List;

public class QuizLevelsAdapter extends RecyclerView.Adapter<QuizLevelsAdapter.VH> {
    private List<QuizLevel> levelList;
    private int playerScore ;
    private static final String TAG = "QuizLevelsAdapter";

    public QuizLevelsAdapter(List<QuizLevel> levelList, int playerScore) {
        this.levelList = levelList;
        this.playerScore = playerScore;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_level,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindData(levelList.get(position));
    }

    @Override
    public int getItemCount() {
        if(levelList == null)
            return 0;
        return levelList.size();
    }


    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewLevelNumber;
        private TextView textViewLevelCollectedScore;
        private TextView textViewTotalLevelScore;

        public VH(@NonNull View itemView) {
            super(itemView);
            textViewLevelNumber = itemView.findViewById(R.id.textViewLevelNumber);
            textViewLevelCollectedScore = itemView.findViewById(R.id.textViewLevelCollectedScore);
            textViewTotalLevelScore = itemView.findViewById(R.id.textViewTotalLevelPoints);
            itemView.setOnClickListener(this);
        }
        private void bindData(QuizLevel level){
            textViewLevelNumber.setText(String.valueOf(level.getLevelNumber()));
            textViewLevelCollectedScore.setText(String.valueOf(level.getCollectedScore()));
            textViewTotalLevelScore.setText(String.valueOf(level.getTotalScore()));
            if(level.getScoreToUnlock()>playerScore)
                setLocked();
            else
                setOpen(level);
        }

        private void setLocked(){
            textViewLevelNumber.setText("LOCK");
        }

        private void setOpen(QuizLevel level){
            textViewLevelNumber.setText(String.valueOf(level.getLevelNumber()));
        }

        @Override
        public void onClick(View view) {
            Context context=view.getContext();
            Intent i = new Intent(context, QuizActivity.class);
            context.startActivity(i);
        }
    }
}
