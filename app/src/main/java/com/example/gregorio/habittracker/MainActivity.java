package com.example.gregorio.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gregorio.habittracker.data.HabitContract;
import com.example.gregorio.habittracker.data.HabitDbHelper;

/**
 * Created by Gregorio on 09/07/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** Database helper that will provide us access to the database */
        HabitDbHelper mDbHelper = new HabitDbHelper(this);


        mDbHelper.insertHabit("Water", "Drinking", "2017-07-09", "09:30", 1, 0 );
        mDbHelper.insertHabit("Yoga", "Sport", "2017-07-09", "11:30", 30, 0 );
        mDbHelper.insertHabit("Android Studio", "Coding", "2017-07-09", "14:30", 180, 0 );

        Cursor c = mDbHelper.readDatabase();

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habit table in the database).
            Log.i(LOG_TAG, "The habit table contains " + c.getCount() + " habits.\n\n");
            Log.i(LOG_TAG, HabitContract.HabitEntry._ID + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_TYPE + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_DATE + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_TIME + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_DURATION + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_COST + "\n");

            // Figure out the index of each column
            int idColumnIndex = c.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int typeColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TYPE);
            int dateColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DATE);
            int timeColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TIME);
            int durationColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DURATION);
            int costColumnIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_COST);


            // Iterate through all the returned rows in the cursor
            while (c.moveToNext()) {
                int currentID = c.getInt(idColumnIndex);
                String currentName = c.getString(nameColumnIndex);
                String currentType = c.getString(typeColumnIndex);
                String currentDate = c.getString(dateColumnIndex);
                String currentTime = c.getString(timeColumnIndex);
                int currentDuration = c.getInt(durationColumnIndex);
                int currentCost = c.getInt(costColumnIndex);

                // Display the values from each column of the current row in the cursor in the Logcat
                Log.i(LOG_TAG, ("\n" + currentID + " - " +
                        currentName + " - " +
                        currentType + " - " +
                        currentDate + " - " +
                        currentTime + " - " +
                        currentDuration + " - " +
                        currentCost));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            c.close();
        }
    }
}
