package com.example.gregorio.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Gregorio on 09/07/2017.
 *
 *
 */


public class HabitDbHelper  extends SQLiteOpenHelper {

    public static final String LOG_TAG1 = HabitDbHelper.class.getName();

    private HabitDbHelper mDbHelper;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT ," +
                    HabitContract.HabitEntry.COLUMN_HABIT_TYPE + " TEXT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_DATE + " TEXT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_TIME + " TEXT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_DURATION + " INTEGER," +
                    HabitContract.HabitEntry.COLUMN_HABIT_COST + " INTEGER) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + com.example.gregorio.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Habit.db";

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

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertHabit(String name, String type, String date, String time, int duration, int cost){

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        // Create a ContentValues object where column names are the keys,
        // and Water attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TYPE, type);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, date);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME, time);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DURATION, duration);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_COST, cost);


        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.i(LOG_TAG1, "New habit added to database ID No. " + newRowId);
    }

    public Cursor readDatabase() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

            String[] projection = {
                    HabitContract.HabitEntry._ID,
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                    HabitContract.HabitEntry.COLUMN_HABIT_TYPE,
                    HabitContract.HabitEntry.COLUMN_HABIT_DATE,
                    HabitContract.HabitEntry.COLUMN_HABIT_TIME,
                    HabitContract.HabitEntry.COLUMN_HABIT_DURATION,
                    HabitContract.HabitEntry.COLUMN_HABIT_COST
            };

            return db.query(
                    HabitContract.HabitEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }


