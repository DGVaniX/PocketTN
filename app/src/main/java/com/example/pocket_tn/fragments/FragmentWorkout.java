package com.example.pocket_tn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocket_tn.AddWorkoutActivity;
import com.example.pocket_tn.R;
import com.example.pocket_tn.adapters.AdapterMeals;
import com.example.pocket_tn.adapters.AdapterWorkouts;
import com.example.pocket_tn.databinding.FragmentWorkoutBinding;
import com.example.pocket_tn.room.database.DbPocket;
import com.example.pocket_tn.room.entity.ModelWorkout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class FragmentWorkout extends Fragment {

    FragmentWorkoutBinding binding;
    private boolean flagmonday = false;
    private boolean flagtuesday = false;
    private boolean flagwednesday = false;
    private boolean flagthursday = false;
    private boolean flagfriday = false;
    private boolean flagsaturday = false;
    private boolean flagsunday = false;

    private List<ModelWorkout> mondaysWorkoutList;
    private List<ModelWorkout> tuesdaysWorkoutList;
    private List<ModelWorkout> wednesdayssWorkoutList;
    private List<ModelWorkout> thursdaysWorkoutList;
    private List<ModelWorkout> fridaysWorkoutList;
    private List<ModelWorkout> saturdayssWorkoutList;
    private List<ModelWorkout> sundayssWorkoutList;

    AdapterWorkouts adapterWorkouts;

    DbPocket dbPocket;

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        dbPocket = DbPocket.getDatabaseInstance(requireContext());

        initLists();


        clickListners();


        removeListeners();

        binding.addWorkout.setOnClickListener(v -> startActivity(new Intent(requireContext(), AddWorkoutActivity.class)));

        return binding.getRoot();
    }

    private void removeListeners() {
        binding.removeMonday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Monday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardMondayRecycler.setVisibility(View.GONE);
            binding.removeMonday.setVisibility(View.GONE);
            mondaysWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });

        binding.removeTuesday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Tuesday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardTuesdayRecycler.setVisibility(View.GONE);
            binding.removeTuesday.setVisibility(View.GONE);
            tuesdaysWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });

        binding.removeWednesday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Wednesday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardWednesdayRecycler.setVisibility(View.GONE);
            binding.removeWednesday.setVisibility(View.GONE);
            wednesdayssWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });

        binding.removeThursday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Thursday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardthursdayRecycler.setVisibility(View.GONE);
            binding.removeFriday.setVisibility(View.GONE);
            thursdaysWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });



        binding.removeFriday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Friday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardfridayRecycler.setVisibility(View.GONE);
            binding.removeFriday.setVisibility(View.GONE);
            fridaysWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });

        binding.removeSaturday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Saturday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardsaturdayRecycler.setVisibility(View.GONE);
            binding.removeSaturday.setVisibility(View.GONE);
            saturdayssWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });

        binding.removeSunday.setOnClickListener(v -> {
            dbPocket.getWorkoutDao().deleteSingleChat("Sunday");
            Toasty.success(requireContext(), "Workout Removed!", Toast.LENGTH_SHORT, true).show();
            binding.cardsundayRecycler.setVisibility(View.GONE);
            binding.removeSunday.setVisibility(View.GONE);
            sundayssWorkoutList.clear();
            adapterWorkouts.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapters();
    }

    private void setupAdapters() {
        mondaysWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Monday");
        adapterWorkouts = new AdapterWorkouts(mondaysWorkoutList, requireActivity());
        binding.rvMonday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvMonday.setAdapter(adapterWorkouts);


        tuesdaysWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Tuesday");
        adapterWorkouts = new AdapterWorkouts(tuesdaysWorkoutList, requireActivity());
        binding.rvTuesday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTuesday.setAdapter(adapterWorkouts);

        wednesdayssWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Wednesday");
        adapterWorkouts = new AdapterWorkouts(wednesdayssWorkoutList, requireActivity());
        binding.rvWednesday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvWednesday.setAdapter(adapterWorkouts);


        thursdaysWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Thursday");
        adapterWorkouts = new AdapterWorkouts(thursdaysWorkoutList, requireActivity());
        binding.rvThursday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvThursday.setAdapter(adapterWorkouts);


        fridaysWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Friday");
        adapterWorkouts = new AdapterWorkouts(fridaysWorkoutList, requireActivity());
        binding.rvFriday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvFriday.setAdapter(adapterWorkouts);

        saturdayssWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Saturday");
        adapterWorkouts = new AdapterWorkouts(saturdayssWorkoutList, requireActivity());
        binding.rvSaturday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSaturday.setAdapter(adapterWorkouts);

        sundayssWorkoutList = dbPocket.getWorkoutDao().getWorkouts("Sunday");
        adapterWorkouts = new AdapterWorkouts(sundayssWorkoutList, requireActivity());
        binding.rvSunday.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSunday.setAdapter(adapterWorkouts);
    }

    private void clickListners() {
        binding.cardmonday.setOnClickListener(v -> {
            if (flagmonday) {
                flagmonday = false;
                binding.cardMondayRecycler.setVisibility(View.GONE);
                binding.removeMonday.setVisibility(View.GONE);
                binding.headingMon.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardMondayRecycler.setVisibility(View.VISIBLE);

                if(mondaysWorkoutList.size()>0){
                binding.removeMonday.setVisibility(View.VISIBLE);}
                binding.headingMon.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagmonday = true;

            }
        });

        binding.cardtuesday.setOnClickListener(v -> {
            if (flagtuesday) {
                flagtuesday = false;
                binding.cardTuesdayRecycler.setVisibility(View.GONE);
                binding.removeTuesday.setVisibility(View.GONE);
                binding.headingTue.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardTuesdayRecycler.setVisibility(View.VISIBLE);
                if(tuesdaysWorkoutList.size()>0){
                    binding.removeTuesday.setVisibility(View.VISIBLE);
                }
                binding.headingTue.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagtuesday = true;

            }
        });

        binding.cardwednesday.setOnClickListener(v -> {
            if (flagwednesday) {
                flagwednesday = false;
                binding.removeWednesday.setVisibility(View.GONE);
                binding.cardWednesdayRecycler.setVisibility(View.GONE);

                binding.headingWed.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardWednesdayRecycler.setVisibility(View.VISIBLE);
                if(wednesdayssWorkoutList.size()>0){
                    binding.removeWednesday.setVisibility(View.VISIBLE);
                }

                binding.headingWed.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagwednesday = true;

            }
        });

        binding.cardthursday.setOnClickListener(v -> {
            if (flagthursday) {
                flagthursday = false;
                binding.cardthursdayRecycler.setVisibility(View.GONE);
                binding.removeThursday.setVisibility(View.GONE);
                binding.headingThu.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardthursdayRecycler.setVisibility(View.VISIBLE);
                if(thursdaysWorkoutList.size()>0){
                    binding.removeThursday.setVisibility(View.VISIBLE);
                }
                binding.headingThu.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagthursday = true;

            }
        });

        binding.cardfriday.setOnClickListener(v -> {
            if (flagfriday) {
                flagfriday = false;
                binding.cardfridayRecycler.setVisibility(View.GONE);
                binding.removeFriday.setVisibility(View.GONE);
                binding.headingFri.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardfridayRecycler.setVisibility(View.VISIBLE);
                if(fridaysWorkoutList.size()>0){
                    binding.removeFriday.setVisibility(View.VISIBLE);
                }
                binding.headingFri.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagfriday = true;

            }
        });

        binding.cardsaturday.setOnClickListener(v -> {
            if (flagsaturday) {
                flagsaturday = false;
                binding.removeSaturday.setVisibility(View.GONE);
                binding.cardsaturdayRecycler.setVisibility(View.GONE);

                binding.headingSat.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardsaturdayRecycler.setVisibility(View.VISIBLE);
                if(saturdayssWorkoutList.size()>0){
                    binding.removeSaturday.setVisibility(View.VISIBLE);
                }
                binding.headingSat.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagsaturday = true;

            }
        });

        binding.cardsunday.setOnClickListener(v -> {
            if (flagsunday) {
                flagsunday = false;
                binding.removeSunday.setVisibility(View.GONE);
                binding.cardsundayRecycler.setVisibility(View.GONE);

                binding.headingSun.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowdown,
                        0
                );

            } else {
                binding.cardsundayRecycler.setVisibility(View.VISIBLE);
                if(sundayssWorkoutList.size()>0){
                    binding.removeSunday.setVisibility(View.VISIBLE);
                }
                binding.headingSun.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrowup,
                        0
                );

                flagsunday = true;

            }
        });

    }

    private void initLists() {
        mondaysWorkoutList = new ArrayList<>();
        tuesdaysWorkoutList = new ArrayList<>();
        wednesdayssWorkoutList = new ArrayList<>();
        thursdaysWorkoutList = new ArrayList<>();
        fridaysWorkoutList = new ArrayList<>();
        saturdayssWorkoutList = new ArrayList<>();
        sundayssWorkoutList = new ArrayList<>();
    }
}
