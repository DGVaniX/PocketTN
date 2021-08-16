package com.example.pocket_tn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocket_tn.databinding.ActivitySetGoalBinding;
import com.example.pocket_tn.databinding.FragmentMealsBinding;
import com.example.pocket_tn.utils.SharedPref;

import org.jetbrains.annotations.NotNull;

import es.dmoral.toasty.Toasty;

public class FragmentProfile extends Fragment {
    ActivitySetGoalBinding binding;
    SharedPref pref;

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = ActivitySetGoalBinding.inflate(inflater, container, false);

        pref = new SharedPref(requireContext());

        binding.setGoal.setOnClickListener(v -> {
            if (!binding.caloriesGoal.getText().toString().isEmpty()) {
                if(Integer.parseInt(String.valueOf(binding.caloriesGoal.getText())) <= 50000) {
                    pref.setCaloriesGoal(Integer.parseInt(binding.caloriesGoal.getText().toString()));
                    pref.setConsumedCalories(0);

                    Toasty.success(requireContext(), "Calories goal set to " + binding.caloriesGoal.getText(), Toast.LENGTH_SHORT, true).show();
                    binding.caloriesGoal.setText("");
                }else {
                    binding.caloriesGoal.setError("Number of calories cannot exceed 50.000!");
                }
            } else binding.caloriesGoal.setError("Please enter a number of calories");
        });
        return binding.getRoot();
    }
}
