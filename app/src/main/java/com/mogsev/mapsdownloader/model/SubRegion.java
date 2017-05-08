package com.mogsev.mapsdownloader.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@Element(name = "region", required = false)
public class SubRegion implements Parcelable, IRegion {

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "translate", required = false)
    private String translate;

    public SubRegion() {

    }

    protected SubRegion(final Parcel in) {
        name = in.readString();
        translate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(translate);
    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTranslate() {
        return null;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public static final Parcelable.Creator<SubRegion> CREATOR = new Parcelable.Creator<SubRegion>() {
        @Override
        public SubRegion createFromParcel(Parcel in) {
            return new SubRegion(in);
        }

        @Override
        public SubRegion[] newArray(int size) {
            return new SubRegion[size];
        }
    };

    @Override
    public String toString() {
        return "SubRegion{" +
                "name='" + name + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
