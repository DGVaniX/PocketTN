package com.example.pocket_tn.room.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pocket_tn.room.dao.WorkoutDao;
import com.example.pocket_tn.room.entity.ModelWorkout;

@Database(entities = {ModelWorkout.class,}, version = 1)
public abstract class DbPocket extends RoomDatabase {

    public abstract WorkoutDao getWorkoutDao();

    private static final String DB_NAME = "Dbpocket";

    private static DbPocket sInstance;

    public static DbPocket getDatabaseInstance(final Context context) {
        if (sInstance == null) {
            synchronized (DbPocket.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), DbPocket.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new PopulateDbAsync(sInstance).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return sInstance;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        PopulateDbAsync(DbPocket instance) {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
