package com.example.pocket_tn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pocket_tn.databinding.ActivityAddWorkoutBinding;
import com.example.pocket_tn.room.database.DbPocket;
import com.example.pocket_tn.room.entity.ModelWorkout;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class AddWorkoutActivity extends AppCompatActivity {


    String selectedDay;
    String selectedWorkout;
    String selectedReps;
    String selectedTime;

    ActivityAddWorkoutBinding binding;
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] workouts = {"Biceps", "Chest", "Triceps", "Shoulder", "Back"};
    String[] reps = {"4 Reps", "8 Reps", "10 Reps", "12 Reps", "16 Reps", "20 Reps", "24 Reps", "28 Reps", "30 Reps", "32 Reps"};
    String[] time = {"5 Minutes", "10 Minutes", "15 Minutes", "20 Minutes", "25 Minutes", "30 Minutes", "35 Minutes", "40 Minutes"};

    DbPocket dbPocket;


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbPocket = DbPocket.getDatabaseInstance(this);

        binding = ActivityAddWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Workout");


        ArrayAdapter<CharSequence> daysAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, days);
        daysAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        binding.countrySpinner.setAdapter(daysAdapter);

        binding.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
                Log.d("dng", selectedDay);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Workout Spinner
        ArrayAdapter<CharSequence> workoutAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, workouts);
        workoutAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        binding.workoutspinner.setAdapter(workoutAdapter);

        binding.workoutspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedWorkout = parent.getItemAtPosition(position).toString();
                Log.d("dng", selectedWorkout);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Reps Spinner
        ArrayAdapter<CharSequence> repsAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, reps);
        repsAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        binding.repsspinner.setAdapter(repsAdapter);

        binding.repsspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedReps = parent.getItemAtPosition(position).toString();
                Log.d("dng", selectedReps);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Time Spinner
        ArrayAdapter<CharSequence> timeAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, time);
        timeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        binding.timespinner.setAdapter(timeAdapter);

        binding.timespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = parent.getItemAtPosition(position).toString();
                Log.d("dng", selectedTime);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        binding.saveWorkout.setOnClickListener(v -> saveData());

    }

    private void saveData() {
        ModelWorkout modelWorkout = new ModelWorkout();
        modelWorkout.setDay(selectedDay);
        modelWorkout.setReps(selectedReps);
        modelWorkout.setTime(selectedTime);
        modelWorkout.setWorkoutType(selectedWorkout);
        modelWorkout.setDone(false);

        if (!binding.etCalories.getText().toString().isEmpty()) {
            if (Integer.parseInt(binding.etCalories.getText().toString()) <= 2000) {
                modelWorkout.setCalories(Integer.parseInt(binding.etCalories.getText().toString()));
                int check = (int) dbPocket.getWorkoutDao().insertWorkout(modelWorkout);
                Log.d("dng", "res " + check);
                if (check > 0) {
                    binding.etCalories.setText("");
                    Toasty.success(this, "Workout Added!", Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(this, "Something went wrong please try again!.", Toast.LENGTH_SHORT, true).show();
                }
            }else{
                binding.etCalories.setError("Number of calories cannot be higher than 2.000!");
            }
        } else{
            binding.etCalories.setError("Enter calories");
        }

        //modelWorkout.setId(dbPocket.getWorkoutDao().insertWorkout(modelWorkout));
    }
}