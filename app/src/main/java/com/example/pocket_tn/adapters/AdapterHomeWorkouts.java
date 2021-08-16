package com.example.pocket_tn.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocket_tn.R;
import com.example.pocket_tn.room.database.DbPocket;
import com.example.pocket_tn.room.entity.ModelWorkout;
import com.example.pocket_tn.utils.SharedPref;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdapterHomeWorkouts extends RecyclerView.Adapter<AdapterHomeWorkouts.MyViewHolder> {

    List<ModelWorkout> modelWorkoutList;
    Context context;
    SharedPref pref;
    int caloryCount;
    DbPocket dbPocket;
    CallbackUpdate callbackUpdate;
    int selectedTime;

    public AdapterHomeWorkouts(List<ModelWorkout> modelWorkoutList, Context context, CallbackUpdate callbackUpdate) {
        this.modelWorkoutList = modelWorkoutList;
        this.context = context;
        pref = new SharedPref(context);
        caloryCount = pref.getConsumedCalories();
        dbPocket = DbPocket.getDatabaseInstance(context);
        this.callbackUpdate = callbackUpdate;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_workouts_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        ModelWorkout modelWorkout = modelWorkoutList.get(position);
        holder.workoutName.setText(modelWorkout.getWorkoutType());
        holder.workoutReps.setText(modelWorkout.getReps());
        holder.workoutTime.setText(modelWorkout.getTime());
        holder.workoutCalories.setText(MessageFormat.format("{0}kcal", modelWorkout.getCalories()));
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
        if (modelWorkout.isDone()) {
            holder.statusCheck.setImageResource(R.drawable.checked);
        }

        holder.statusCheck.setOnClickListener(v -> {
            if (!modelWorkout.isDone()) {
                int updated = dbPocket.getWorkoutDao().updateWorkout(true, modelWorkout.getId());
                if (updated > 0) {
                    Log.d("dng", "food calories" + modelWorkout.getCalories());
                    holder.statusCheck.setImageResource(R.drawable.checked);
                    caloryCount = caloryCount + modelWorkout.getCalories();
                    Log.d("dng", "calory count added" + caloryCount);
                    pref.setConsumedCalories(caloryCount);
                    notifyDataSetChanged();
                    callbackUpdate.updateData(getTime(modelWorkout.getTime()));
                    Toasty.success(context, "Workout Completed!", Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(context, "Something Went Wrong", Toast.LENGTH_SHORT, true).show();

                }
            } else {
                Toasty.error(context, "Workout Already Completed", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public int getTime(String time) {

        switch (time) {
            case "5 Minutes":
                selectedTime = 5;
                break;

            case "10 Minutes":
                selectedTime = 10;
                break;

            case "15 Minutes":
                selectedTime = 15;
                break;

            case "20 Minutes":
                selectedTime = 20;
                break;

            case "25 Minutes":
                selectedTime = 25;
                break;

            case "30 Minutes":
                selectedTime = 30;
                break;

            case "35 Minutes":
                selectedTime = 35;
                break;

            case "40 Minutes":
                selectedTime = 40;
                break;
        }
        return selectedTime;
    }

    @Override
    public int getItemCount() {
        return modelWorkoutList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView workoutImg, statusCheck;
        TextView workoutName, workoutReps, workoutTime, workoutCalories;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            workoutImg = itemView.findViewById(R.id.workoutimg);
            workoutName = itemView.findViewById(R.id.workoutname);
            workoutReps = itemView.findViewById(R.id.reps);
            workoutCalories = itemView.findViewById(R.id.calories);
            workoutTime = itemView.findViewById(R.id.time);
            statusCheck = itemView.findViewById(R.id.checked);
        }
    }

    public interface CallbackUpdate {
        public void updateData(int time);
    }
}
