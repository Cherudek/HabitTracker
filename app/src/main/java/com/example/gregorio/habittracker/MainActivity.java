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

    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();


    }

    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper = new HabitDbHelper(this);

    /**
     * Temporary helper method to display information in the LogCat about the state of
     * the habit database.
     */
    private void displayDatabaseInfo() {



        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Water attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Water");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TYPE, "Drinking");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, "2017-07-09");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME, "09:30");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DURATION, 1);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_COST, 0);


        // Insert a new row for Water Habit in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Water.

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.i(LOG_TAG, "New habit added to database ID No. " + newRowId);



        // Perform this raw SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the habit table.
        //Cursor cursor = db.rawQuery("SELECT * FROM " + HabitEntry.TABLE_NAME, null);

        String[] projection = { HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_TYPE,
                HabitContract.HabitEntry.COLUMN_HABIT_DATE,
                HabitContract.HabitEntry.COLUMN_HABIT_TIME,
                HabitContract.HabitEntry.COLUMN_HABIT_DURATION,
                HabitContract.HabitEntry.COLUMN_HABIT_COST };

        Cursor c = db.query(HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


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
