package com.mogsev.mapsdownloader;

import android.app.Application;
import android.util.Log;

import com.mogsev.mapsdownloader.utils.Settings;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class MapsDownloader extends Application {
    private static final String TAG = MapsDownloader.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        //init DefaultSharedPreferences
        Settings.initSettings(this);
    }
}
