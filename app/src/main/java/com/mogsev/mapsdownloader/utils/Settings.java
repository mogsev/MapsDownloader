package com.mogsev.mapsdownloader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Class is like helper
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public final class Settings {
    private static final String TAG = Settings.class.getSimpleName();

    private static Settings sInstance = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    // Tags of settings
    private static final String PREF_IS_FIRST_START = "pref_is_first_start";

    private Settings(@NonNull Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
    }

    public static void initSettings(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        Log.i(TAG, "initSettings");
        sInstance = new Settings(context);
    }


    @NonNull
    public static synchronized Settings getInstance() {
        return sInstance;
    }

    public void saveIsFirstStart(boolean isFirstStart) {
        mEditor.putBoolean(PREF_IS_FIRST_START, isFirstStart);
        mEditor.apply();
    }

    public boolean isFirstStart() {
        return mSharedPreferences.getBoolean(PREF_IS_FIRST_START, true);
    }
}
