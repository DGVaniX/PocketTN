package com.example.pocket_tn.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pocket_tn.room.entity.ModelWorkout;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWorkout(ModelWorkout modelChats);


    @Query("select * from ModelWorkout where day= :day")
    List<ModelWorkout> getWorkouts(String day);


    @Query("delete from ModelWorkout where day= :day")
    void deleteSingleChat(String day);

    @Query("update ModelWorkout set isDone=:done where id=:id")
    int updateWorkout(boolean done, long id);
}
