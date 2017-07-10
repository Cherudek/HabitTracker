package com.example.gregorio.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gregorio on 09/07/2017.
 */

public class HabitDbHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Habit.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL ," +
                    HabitContract.HabitEntry.COLUMN_HABIT_TYPE + " TEXT NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_TIME + " TEXT NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_DURATION + " INTEGER NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_COST + " INTEGER NOT NULL) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + com.example.gregorio.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;

    //HabitDbHelper constructor
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}


