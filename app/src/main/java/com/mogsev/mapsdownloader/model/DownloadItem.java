package com.mogsev.mapsdownloader.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class DownloadItem implements Parcelable {

    private String continentName;
    private String regionName;
    private String fileName;
    private int position;

    public DownloadItem() {

    }

    protected DownloadItem(final Parcel in) {
        continentName = in.readString();
        regionName = in.readString();
        fileName = in.readString();
        position = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(continentName);
        parcel.writeString(regionName);
        parcel.writeString(fileName);
        parcel.writeInt(position);
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static final Parcelable.Creator<DownloadItem> CREATOR = new Parcelable.Creator<DownloadItem>() {
        public DownloadItem createFromParcel(Parcel in) {
            return new DownloadItem(in);
        }

        public DownloadItem[] newArray(int size) {
            return new DownloadItem[size];
        }
    };

    @Override
    public String toString() {
        return "DownloadItem{" +
                "continentName='" + continentName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", position=" + position +
                '}';
    }
}
