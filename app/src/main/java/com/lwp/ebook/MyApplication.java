package com.lwp.ebook;

import android.app.Application;

import androidx.room.Room;

import com.lwp.ebook.model.database.AppDatabase;

public class MyApplication extends Application {
    private AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "eBookReader").build();
    }

    public AppDatabase getDatabase() {
        return db;
    }
}
