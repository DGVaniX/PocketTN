package com.example.pocket_tn.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocket_tn.R;
import com.example.pocket_tn.room.entity.ModelWorkout;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.List;

public class AdapterWorkouts extends RecyclerView.Adapter<AdapterWorkouts.MyViewHolder> {

    List<ModelWorkout> modelWorkoutList;
    Context context;

    public AdapterWorkouts(List<ModelWorkout> modelWorkoutList, Context context) {
        this.modelWorkoutList = modelWorkoutList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        ModelWorkout modelWorkout = modelWorkoutList.get(position);

        holder.workoutName.setText(modelWorkout.getWorkoutType());
        holder.noOfReps.setText(modelWorkout.getReps());
        holder.time.setText(modelWorkout.getTime());
        holder.noOfCalories.setText(MessageFormat.format("{0}kcal", modelWorkout.getCalories()));
        switch (modelWorkout.getWorkoutType()) {
            case "Biceps":
                holder.workoutImg.setImageResource(R.drawable.ic_biceps);
                break;

            case "Chest":
                holder.workoutImg.setImageResource(R.drawable.ic_chest);
                break;

            case "Triceps":
                holder.workoutImg.setImageResource(R.drawable.ic_arm);
                break;

            case "Shoulder":
                holder.workoutImg.setImageResource(R.drawable.ic_shoulder);
                break;

            case "Back":
                holder.workoutImg.setImageResource(R.drawable.ic_back_);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return modelWorkoutList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView workoutImg;
        TextView workoutName, noOfReps, noOfCalories, time;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            workoutImg = itemView.findViewById(R.id.workoutimg);
            workoutName = itemView.findViewById(R.id.workoutname);
            noOfReps = itemView.findViewById(R.id.reps);
            noOfCalories = itemView.findViewById(R.id.calories);
            time = itemView.findViewById(R.id.time);
        }
    }
}
