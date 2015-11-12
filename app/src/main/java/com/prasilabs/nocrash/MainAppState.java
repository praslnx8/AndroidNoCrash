package com.prasilabs.nocrash;

import android.app.Application;

import com.prasilabs.nocrashlib.NoCrashHandler;

/**
 * Created by prasi on 9/11/15.
 */
public class MainAppState extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        //NoCrashHandler.install(getApplicationContext());
        //NoCrashHandler.setCustomCrashActivity(CustomCrashActivity.class);
    }
}
