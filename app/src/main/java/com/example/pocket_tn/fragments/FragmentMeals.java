package com.example.pocket_tn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocket_tn.R;
import com.example.pocket_tn.adapters.AdapterHomeMeals;
import com.example.pocket_tn.adapters.AdapterMeals;
import com.example.pocket_tn.databinding.FragmentMealsBinding;
import com.example.pocket_tn.models.ModelMeals;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentMeals extends Fragment {
    AdapterMeals adapterMeals;
    List<ModelMeals> dataList;
    FragmentMealsBinding binding;

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentMealsBinding.inflate(inflater, container, false);
        dataList = new ArrayList<>();


        adapterMeals = new AdapterMeals(dataList, requireActivity());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapterMeals);

        loadMeals();

        return binding.getRoot();

    }

    private void loadMeals() {
        dataList.add(new ModelMeals(R.drawable.fruit_granola, "Breakfast", "Fruit Granola", 230, "5 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pesto_pasta, "Lunch", "Pesto Pasta", 336, "20 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.keto_pasta, "Lunch", "Keto Pasta", 291, "22 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.umpluti, "Dinner", "umpluti", 410, "30 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pork_goulash, "Dinner", "Pork Goulash", 310, "25 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.pizza, "Snacks", "Pizza", 210, "10 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));
        dataList.add(new ModelMeals(R.drawable.stuffed_pepper, "Snacks", "Stuffed Pepper", 370, "15 minutes", "2 Rabbit Eggs\n 1kg Potatoes\n,3oz gold essence\n, 2liter milk"));

    }
}
