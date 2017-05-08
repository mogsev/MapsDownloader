package com.mogsev.mapsdownloader.osmand;

import com.mogsev.mapsdownloader.network.Network;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class ApiOsmandTest {

    @Test
    public void downloadFile_Success() {
        String url = "http://download.osmand.net/download.php?standard=yes&file=Denmark_europe_2.obf.zip";
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("standard", "yes");
        queryMap.put("file", "Denmark_europe_2.obf.zip");

        Call<ResponseBody> call = Network.API_OSMAND.downloadFile(queryMap);
        System.out.println("Url: " + call.request().url());
        // check url
        assertTrue(call.request().url().toString().equals(url));
        try {
            Response<ResponseBody> response = call.execute();
            // check response
            assertTrue(response.isSuccessful());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
