package com.mogsev.mapsdownloader.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        RegionType.CONTINENT,
        RegionType.SRTM,
        RegionType.MAP})
public @interface RegionType {

    public static final String CONTINENT = "continent";
    public static final String SRTM = "srtm";
    public static final String MAP = "map";

}
