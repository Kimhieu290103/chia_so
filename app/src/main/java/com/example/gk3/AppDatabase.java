package com.example.gk3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = HistoryItem.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryItemDao historyItemDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "history_items")
                    .build();
        }
        return instance;
    }
}
