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
public class Continent implements Parcelable, IRegion {

    public static final String BUNDLE_NAME = Continent.class.getSimpleName();

    @RegionType
    @Attribute(name = "type", required = false)
    private String type;

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "translate", required = false)
    private String translate;

    @Attribute(name = "inner_download_suffix", required = false)
    private String innerDownloadSuffix;

    @Attribute(name = "boundary", required = false)
    private String boundary;

    @Attribute(name = "poly_extract", required = false)
    private String polyExtract;

    @ElementList(entry = "region", required = false, inline = true)
    private List<Region> regions;

    public Continent() {

    }

    @SuppressWarnings("ResourceType")
    protected Continent(final Parcel in) {
        type = in.readString();
        name = in.readString();
        translate = in.readString();
        innerDownloadSuffix = in.readString();
        boundary = in.readString();
        polyExtract = in.readString();
        regions = in.readArrayList(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(type);
        parcel.writeString(name);
        parcel.writeString(translate);
        parcel.writeString(innerDownloadSuffix);
        parcel.writeString(boundary);
        parcel.writeString(polyExtract);
        parcel.writeList(regions);
    }

    @RegionType
    public String getType() {
        return type;
    }

    public void setType(@RegionType String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getInnerDownloadSuffix() {
        return innerDownloadSuffix;
    }

    public void setInnerDownloadSuffix(String innerDownloadSuffix) {
        this.innerDownloadSuffix = innerDownloadSuffix;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getPolyExtract() {
        return polyExtract;
    }

    public void setPolyExtract(String polyExtract) {
        this.polyExtract = polyExtract;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> list) {
        this.regions = list;
    }

    public static final Parcelable.Creator<Continent> CREATOR = new Parcelable.Creator<Continent>() {
        @Override
        public Continent createFromParcel(Parcel in) {
            return new Continent(in);
        }

        @Override
        public Continent[] newArray(int size) {
            return new Continent[size];
        }
    };

    @Override
    public String toString() {
        return "Continent{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", translate='" + translate + '\'' +
                ", innerDownloadSuffix='" + innerDownloadSuffix + '\'' +
                ", boundary='" + boundary + '\'' +
                ", polyExtract='" + polyExtract + '\'' +
                ", regions=" + regions +
                '}';
    }
}
