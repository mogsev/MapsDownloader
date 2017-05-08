package com.mogsev.mapsdownloader.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class DownloadResult implements Parcelable {

    public static final String BUNDLE_NAME = DownloadResult.class.getSimpleName();

    private List<DownloadItem> downloadItems = new ArrayList<>();
    private DownloadItem currentItem;

    private int progress;
    private int currentFileSize;
    private int totalFileSize;

    public DownloadResult() {

    }

    protected DownloadResult(final Parcel in) {
        downloadItems = in.readArrayList(getClass().getClassLoader());
        currentItem = in.readParcelable(getClass().getClassLoader());
        progress = in.readInt();
        currentFileSize = in.readInt();
        totalFileSize = in.readInt();
    }

    public List<DownloadItem> getDownloadItems() {
        return downloadItems;
    }

    public void setDownloadItems(List<DownloadItem> downloadItems) {
        this.downloadItems = downloadItems;
    }

    public DownloadItem getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(DownloadItem currentItem) {
        this.currentItem = currentItem;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeList(downloadItems);
        parcel.writeParcelable(currentItem, flags);
        parcel.writeInt(progress);
        parcel.writeInt(currentFileSize);
        parcel.writeInt(totalFileSize);
    }

    public static final Parcelable.Creator<DownloadResult> CREATOR = new Parcelable.Creator<DownloadResult>() {
        public DownloadResult createFromParcel(Parcel in) {
            return new DownloadResult(in);
        }

        public DownloadResult[] newArray(int size) {
            return new DownloadResult[size];
        }
    };

    @Override
    public String toString() {
        return "DownloadResult{" +
                "downloadItems=" + downloadItems +
                ", currentItem=" + currentItem +
                ", progress=" + progress +
                ", currentFileSize=" + currentFileSize +
                ", totalFileSize=" + totalFileSize +
                '}';
    }
}