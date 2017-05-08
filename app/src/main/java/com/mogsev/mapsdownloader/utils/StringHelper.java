package com.mogsev.mapsdownloader.utils;

import android.support.annotation.NonNull;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public final class StringHelper {

    @NonNull
    public static String toUpperCaseFirstLetter(@NonNull String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    @NonNull
    public static String removeFirstLetter(@NonNull String str, @NonNull String letter) {
        if (str.substring(0, 1).equals(letter)) {
            return str.substring(1);
        } else {
            return str;
        }
    }

}
