package com.example.pocket_tn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pocket_tn.databinding.ActivityMealDetailBinding;

import java.text.MessageFormat;
import java.util.Objects;

public class MealDetailActivity extends AppCompatActivity {
    ActivityMealDetailBinding binding;
    String mealname, ingridients, type, time;
    int calories;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mealname = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        time = getIntent().getStringExtra("time");
        calories = getIntent().getIntExtra("calories",0);
        ingridients = getIntent().getStringExtra("ingrid");


        binding.mealName.setText(mealname);
        binding.ingridients.setText(ingridients);
        binding.mealtype.setText(type);
        binding.calories.setText(MessageFormat.format("{0}kcal", calories));
        binding.time.setText(time);


        switch (mealname) {
            case "Fruit Granola":
                binding.thumbnailImageCard.setImageResource(R.drawable.fruit_granola);
                break;
            case "Pesto Pasta":
                binding.thumbnailImageCard.setImageResource(R.drawable.pesto_pasta);
                break;
            case "Keto Pasta":
                binding.thumbnailImageCard.setImageResource(R.drawable.keto_pasta);
                break;
            case "Stuffed Peppers":
                binding.thumbnailImageCard.setImageResource(R.drawable.umpluti);
                break;
            case "Pork Goulash":
                binding.thumbnailImageCard.setImageResource(R.drawable.pork_goulash);
                break;
            case "Pizza":
                binding.thumbnailImageCard.setImageResource(R.drawable.pizza);
                break;
            case "Stuffed Pepper":
                binding.thumbnailImageCard.setImageResource(R.drawable.stuffed_pepper);
                break;
        }


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mealname);
    }
}