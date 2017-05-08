package com.mogsev.mapsdownloader.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mogsev.mapsdownloader.model.Continent;
import com.mogsev.mapsdownloader.model.IRegion;
import com.mogsev.mapsdownloader.model.Region;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public final class RegionHelper {
    private static final String TAG = RegionHelper.class.getSimpleName();

    @NonNull
    public static String takeTranslateNameOfContinent(@Nullable IRegion region) {
        if (region == null) {
            throw new IllegalArgumentException("IRegion cannot be null");
        }
        String translate = region.getTranslate();
        if (TextUtils.isEmpty(translate)) {
            String name = region.getName();
            return StringHelper.toUpperCaseFirstLetter(name);
        } else {
            String[] split = translate.split(";");
            return StringHelper.removeFirstLetter(split[0], "=");
        }
    }

    @NonNull
    public static String takeFileName(Continent continent, Region region) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringHelper.toUpperCaseFirstLetter(region.getName()));
        builder.append("_");
        builder.append(continent.getName());
        builder.append("_2.obf.zip");
        return builder.toString();
    }

}
