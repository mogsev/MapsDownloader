package com.mogsev.mapsdownloader.network;

import android.support.annotation.NonNull;

import com.mogsev.mapsdownloader.osmand.ApiOsmand;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public final class Network {

    @NonNull
    public static final ApiOsmand API_OSMAND = new Retrofit.Builder()
            .baseUrl(ApiOsmand.BASE_URL)
            .client(new OkHttpClient())
            .build()
            .create(ApiOsmand.class);

}
