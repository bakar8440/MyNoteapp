package com.example.mynoteapp.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class},version = 3)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public static UserDatabase INSTANCE;
    public static UserDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,UserDatabase.class,"UserDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
