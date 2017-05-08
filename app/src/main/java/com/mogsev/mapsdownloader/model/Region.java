package com.mogsev.mapsdownloader.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@Element(name = "region", required = false)
public class Region implements Parcelable, IRegion {

    public static final String BUNDLE_NAME = Region.class.getSimpleName();

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "lang", required = false)
    private String lang;

    @Attribute(name = "translate", required = false)
    private String translate;

    @Attribute(name = "poly_extract", required = false)
    private String polyExtract;

    @ElementList(entry = "region", required = false, inline = true)
    private List<SubRegion> subRegions;

    public Region() {

    }

    protected Region(final Parcel in) {
        name = in.readString();
        lang = in.readString();
        translate = in.readString();
        polyExtract = in.readString();
        subRegions = in.readArrayList(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(lang);
        parcel.writeString(translate);
        parcel.writeString(polyExtract);
        parcel.writeList(subRegions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getPolyExtract() {
        return polyExtract;
    }

    public void setPolyExtract(String polyExtract) {
        this.polyExtract = polyExtract;
    }

    public List<SubRegion> getSubRegions() {
        return subRegions;
    }

    public void setSubRegions(List<SubRegion> subRegions) {
        this.subRegions = subRegions;
    }

    public static final Parcelable.Creator<Region> CREATOR = new Parcelable.Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel in) {
            return new Region(in);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", lang='" + lang + '\'' +
                ", translate='" + translate + '\'' +
                ", polyExtract='" + polyExtract + '\'' +
                ", subRegions=" + subRegions +
                '}';
    }
}
