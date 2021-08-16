package com.example.pocket_tn.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocket_tn.MealDetailActivity;
import com.example.pocket_tn.R;
import com.example.pocket_tn.models.ModelMeals;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.List;

public class AdapterHomeMeals extends RecyclerView.Adapter<AdapterHomeMeals.MyViewHolder> {

    List<ModelMeals> mealsList;
    Context context;

    public AdapterHomeMeals(List<ModelMeals> mealsList, Context context) {
        this.mealsList = mealsList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_meals, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        ModelMeals meals = mealsList.get(position);
        holder.mealImg.setImageResource(meals.getIcon());
        holder.mealname.setText(meals.getName());
        holder.mealtype.setText(meals.getType());
        holder.calroies.setText(MessageFormat.format("{0}kcal", meals.getCalories()));
        holder.time.setText(meals.getTime());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MealDetailActivity.class);
            intent.putExtra("name", meals.getName());
            intent.putExtra("type", meals.getType());
            intent.putExtra("time", meals.getTime());
            intent.putExtra("calories", meals.getCalories());
            intent.putExtra("ingrid", meals.getIngridients());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mealtype, mealname, calroies, time;
        ImageView mealImg;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mealImg = itemView.findViewById(R.id.thumbnail_image_card);
            mealtype = itemView.findViewById(R.id.mealtype);
            mealname = itemView.findViewById(R.id.meal_name);
            calroies = itemView.findViewById(R.id.calories);
            time = itemView.findViewById(R.id.time);
        }
    }
}
