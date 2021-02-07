package com.example.android.wordgame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alnamaa.arabic_quiz.R;
import com.example.android.wordgame.models.Stage;

import java.util.List;



public class StagesAdapter extends RecyclerView.Adapter<StagesAdapter.VH> {

    public interface OnStageClickListener{
        void stageClicked(Stage stage , int i);
    }

    private OnStageClickListener listener;

    private List<Stage> stages;
    private static final String TAG = "StagesAdapter";

    public StagesAdapter(List<Stage> stages,OnStageClickListener listener) {
        this.stages = stages;
        this.listener = listener;
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


    public class VH extends RecyclerView.ViewHolder  {
        private TextView textViewStageSequence;

        public VH(@NonNull View itemView) {
            super(itemView);
            textViewStageSequence = itemView.findViewById(R.id.textViewStageSequence);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!= null)
                        listener.stageClicked(stages.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
        private void bindData(Stage stage){
            textViewStageSequence.setText(""+stage.getSequence());
        }
    }
}
