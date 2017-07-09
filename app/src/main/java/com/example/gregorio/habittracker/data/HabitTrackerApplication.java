package com.example.gregorio.habittracker.data;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Gregorio on 09/07/2017.
 *
 * Stetho Debugging class added to check database creation in chrome://inspect/#devices
 */

public class HabitTrackerApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
