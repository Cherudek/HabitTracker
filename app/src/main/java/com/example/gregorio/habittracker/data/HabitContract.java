package com.example.gregorio.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Gregorio on 09/07/2017.
 */

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {
    }


    /* Inner class that defines the table contents */
    public static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        //Column ID <P>Type: INTEGER</P>
        public static final String _ID = BaseColumns._ID;

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_HABIT_NAME = "name";

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_HABIT_TYPE = "type";

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_HABIT_DATE = "date";

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_HABIT_TIME = "time";

        //Column ID <P>Type: INTEGER</P>
        public static final String COLUMN_HABIT_DURATION = "duration";

        //Column ID <P>Type: INTEGER</P>
        public static final String COLUMN_HABIT_COST = "cost";







    }

}
