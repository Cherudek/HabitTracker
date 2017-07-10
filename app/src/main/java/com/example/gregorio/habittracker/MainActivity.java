package com.example.gregorio.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.gregorio.habittracker.data.HabitContract;
import com.example.gregorio.habittracker.data.HabitDbHelper;

/**
 * Created by Gregorio on 09/07/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        insertHabit();
        readDatabase();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + HabitContract.HabitEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habit table in the database).
            TextView displayView = (TextView) findViewById(R.id.text);
            displayView.setText("Number of rows in habits database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habit database.
     */
    public Cursor readDatabase() {
        // Create and/or open a database to read from it
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

    /**
     * Helper method to insert hardcoded habit data into the database. For debugging purposes only.
     */
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Yoga attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Yoga");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TYPE, "Sport");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, "2017-07-10");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME, "10:10");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DURATION, 7);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_COST, 2);

        // Insert a new row for Yoga in the database, returning the ID of that new row.
        // The first argument for db.insert() is the habit table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Yoga.
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.i(LOG_TAG, "a New Habit has been addedd to database with the value of: " + newRowId);
    }
}
