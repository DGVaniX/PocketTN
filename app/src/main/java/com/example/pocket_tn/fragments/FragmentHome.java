package com.example.pocket_tn.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocket_tn.R;
import com.example.pocket_tn.adapters.AdapterHomeMeals;
import com.example.pocket_tn.adapters.AdapterHomeWorkouts;
import com.example.pocket_tn.databinding.FragmentHomeBinding;
import com.example.pocket_tn.models.ModelMeals;
import com.example.pocket_tn.room.database.DbPocket;
import com.example.pocket_tn.room.entity.ModelWorkout;
import com.example.pocket_tn.utils.SharedPref;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.Callback;

public class FragmentHome extends Fragment implements AdapterHomeWorkouts.CallbackUpdate {

    FragmentHomeBinding binding;
    AdapterHomeMeals adapterHomeMeals;
    List<ModelMeals> dataList;
    SharedPref sharedPref;
    AdapterHomeWorkouts adapterHomeWorkouts;
    String currentSystemDay;
    List<ModelWorkout> workoutsList;
    DbPocket dbPocket;
    private CountDownTimer countDownTimer;
    private int timeCountInMilliSeconds = 1 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;


    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        sharedPref = new SharedPref(requireActivity());
        dataList = new ArrayList<>();
        workoutsList = new ArrayList<>();
        dbPocket = DbPocket.getDatabaseInstance(requireActivity());

        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("EEEE, d MMMM").format(Calendar.getInstance().getTime());
        binding.todaysdate.setText(timeStamp);

        currentSystemDay = new SimpleDateFormat("EEEE").format(Calendar.getInstance().getTime());

        Log.d("dng", currentSystemDay);

        adapterHomeMeals = new AdapterHomeMeals(dataList, requireActivity());
        binding.rvMeals.setAdapter(adapterHomeMeals);

        loadMeals();

        binding.workoutcard.setOnClickListener(v -> showBottomSheetSavedDialog());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.progressBar.setMaximum(sharedPref.getCaloriesGoal());
        binding.progressBar.setProgress(sharedPref.getConsumedCalories());

        int remaining = sharedPref.getCaloriesGoal() - sharedPref.getConsumedCalories();

        binding.percent.setText(MessageFormat.format("{0}\nkcal Left", remaining));

    }

    public void initCountDownTimer(int time) {
        binding.workoutTime.setMaximum(time * 1000);
        new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.workoutTime.setProgress(millisUntilFinished / 1000);

                long minutes = (millisUntilFinished / 1000) / 60;
                int seconds = (int) ((millisUntilFinished / 1000) % 60);
                binding.timeLeft.setText(MessageFormat.format("{0}:{1}\n workout left", minutes, seconds));


            }

            public void onFinish() {
//                binding.workoutTime.setProgress(100);
                binding.timeLeft.setText("done!");
            }
        }.start();
    }

    private void loadMeals() {
        dataList.add(new ModelMeals(R.drawable.fruit_granola, "Breakfast", "Fruit Granola", 230, "5 minutes", "2 Rabbit Eggs\n1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pesto_pasta, "Lunch", "Pesto Pasta", 336, "20 minutes", "2 Rabbit Eggs\n1kg Potatoes\n3oz gold essence\n2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pizza, "Snacks", "Pizza", 270, "10 minutes", "2 Rabbit Eggs\n1kg Potatoes\n,3oz gold essence\n2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pork_goulash, "Dinner", "Pork Goulash", 310, "25 minutes", "2 Rabbit Eggs\n1kg Potatoes\n3oz gold essence\n2liter milk"));

    }


    private void showBottomSheetSavedDialog() {
        workoutsList = dbPocket.getWorkoutDao().getWorkouts(currentSystemDay);
        LayoutInflater inflater = getLayoutInflater();
        final BottomSheetDialog dialog = new BottomSheetDialog(requireActivity());
        final View sheetView = inflater.inflate(R.layout.dilaog_folders_list, (ViewGroup) requireActivity().getWindow().getDecorView().getRootView(), false);
        dialog.setContentView(sheetView);

        final BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) sheetView.getParent());

        dialog.setOnShowListener(dialogInterface -> {
            mBehavior.setPeekHeight(sheetView.getHeight());
        });
        dialog.show();

        TextView layoutCreatePlayList;
        layoutCreatePlayList = dialog.findViewById(R.id.workoutday);

        Objects.requireNonNull(layoutCreatePlayList).setText(MessageFormat.format("{0}''s Workout", currentSystemDay));

        ImageView tvCancel = dialog.findViewById(R.id.closebutton);

        RecyclerView recyclerViewPlayList;
        recyclerViewPlayList = dialog.findViewById(R.id.recycler_view_playlist);
        Objects.requireNonNull(recyclerViewPlayList).setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterHomeWorkouts = new AdapterHomeWorkouts(workoutsList, requireActivity(), this);
        recyclerViewPlayList.setAdapter(adapterHomeWorkouts);


        Objects.requireNonNull(tvCancel).setOnClickListener(view -> dialog.dismiss());

    }

    @Override
    public void updateData(int time) {
        binding.progressBar.setMaximum(sharedPref.getCaloriesGoal());
        binding.progressBar.setProgress(sharedPref.getConsumedCalories());

        int remaining = sharedPref.getCaloriesGoal() - sharedPref.getConsumedCalories();

        binding.percent.setText(MessageFormat.format("{0}\nkcal Left", remaining));

//        initCountDownTimer(time);
        startStop(time);
    }

    private void startStop(int time) {
        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues(time);
            // call to initialize the progress bar values
            setProgressBarValues();

            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }
    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues(int time) {
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                binding.timeLeft.setText(hmsTimeFormatter(millisUntilFinished));

                binding.workoutTime.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

                binding.timeLeft.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;
            }

        }.start();
        countDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        binding.workoutTime.setMaximum((int) timeCountInMilliSeconds / 1000);
        binding.workoutTime.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }
}
