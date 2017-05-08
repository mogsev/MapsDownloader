package com.mogsev.mapsdownloader.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@Root(name = "regions_list")
public class RegionsList {

    @ElementList(entry = "region", inline = true)
    private List<Continent> regionList;

    public RegionsList() {

    }

    public List<Continent> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Continent> regionList) {
        this.regionList = regionList;
    }

    @Override
    public String toString() {
        return "RegionsList{" +
                "regionList=" + regionList +
                '}';
    }
}
