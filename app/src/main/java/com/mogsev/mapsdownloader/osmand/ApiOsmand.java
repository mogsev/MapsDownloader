package com.mogsev.mapsdownloader.osmand;

import com.mogsev.mapsdownloader.model.RegionsList;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public interface ApiOsmand {
    public static final String BASE_URL = "http://download.osmand.net";

    @GET
    Call<RegionsList> getListOfSubscribers(@Url String url);

    @GET("download.php")
    @Streaming
    Call<ResponseBody> downloadFile(@QueryMap Map<String, String> queryMap);

}
