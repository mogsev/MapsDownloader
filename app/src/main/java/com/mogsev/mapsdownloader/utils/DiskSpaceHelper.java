package com.mogsev.mapsdownloader.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public final class DiskSpaceHelper {
    private static final String TAG = DiskSpaceHelper.class.getSimpleName();

    public static long getTotalSpace() {
        String externalStorage = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(externalStorage);
        long bytesTotal = (long) statFs.getBlockSize() * (long) statFs.getBlockCount();
        return bytesTotal;
    }

    public static long getAvailableSpace() {
        String externalStorage = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(externalStorage);
        long bytesAvailable = (long) statFs.getBlockSize() * (long) statFs.getAvailableBlocks();
        return bytesAvailable;
    }

    public static String takeSize(@NonNull Context context, long bytes) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        return android.text.format.Formatter.formatFileSize(context, bytes);
    }

    public static float takeRateAvailableSpaceToTotalSpace() {
        return (float) DiskSpaceHelper.getAvailableSpace() / (float) DiskSpaceHelper.getTotalSpace();
    }

    public static float takeRateTotalSpaceToAvailableSpace() {
        return (float) DiskSpaceHelper.getTotalSpace() / (float) DiskSpaceHelper.getAvailableSpace();
    }

}
