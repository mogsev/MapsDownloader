package com.mogsev.mapsdownloader.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.mogsev.mapsdownloader.model.DownloadItem;
import com.mogsev.mapsdownloader.model.DownloadResult;
import com.mogsev.mapsdownloader.network.Network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class DownloadService extends IntentService {
    private static final String TAG = DownloadService.class.getSimpleName();

    public static final String MESSAGE_DOWNLOAD_PROGRESS = "message_download_progress";
    public static final String EXTRA_DOWNLOAD_ITEM = "extra_download_item";

    private final DownloadResult mDownloadResult = new DownloadResult();

    public DownloadService() {
        super("DownloadIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        DownloadItem downloadItem = intent.getParcelableExtra(EXTRA_DOWNLOAD_ITEM);
        mDownloadResult.getDownloadItems().add(downloadItem);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent");
        DownloadItem downloadItem = intent.getParcelableExtra(EXTRA_DOWNLOAD_ITEM);
        startDownload(downloadItem);
    }

    private void startDownload(@NonNull DownloadItem downloadItem) {
        Log.i(TAG, "startDownload");
        mDownloadResult.getDownloadItems().remove(downloadItem);
        mDownloadResult.setCurrentItem(downloadItem);

        String fileName = downloadItem.getFileName();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("standard", "yes");
        queryMap.put("file", fileName);

        Call<ResponseBody> request = Network.API_OSMAND.downloadFile(queryMap);
        try {
            downloadFile(request.execute().body(), downloadItem);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadFile(@NonNull ResponseBody body, DownloadItem downloadItem) throws IOException {
        Log.i(TAG, "downloadFile");

        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test.zip");
        OutputStream output = new FileOutputStream(outputFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;

        int totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
        mDownloadResult.setTotalFileSize(totalFileSize);

        while ((count = bis.read(data)) != -1) {

            total += count;

            double current = Math.round(total / (Math.pow(1024, 2)));

            int progress = (int) ((total * 100) / fileSize);

            long currentTime = System.currentTimeMillis() - startTime;

            if (currentTime > 1000 * timeCount) {
                mDownloadResult.setCurrentFileSize((int) current);
                mDownloadResult.setProgress(progress);
                sendIntent(mDownloadResult);
                timeCount++;
            }

            output.write(data, 0, count);
        }
        onDownloadComplete();
        output.flush();
        output.close();
        bis.close();

    }

    private void onDownloadComplete(){
        mDownloadResult.setProgress(100);
        sendIntent(mDownloadResult);
    }

    private void sendIntent(DownloadResult download){
        Intent intent = new Intent(MESSAGE_DOWNLOAD_PROGRESS);
        intent.putExtra(DownloadResult.BUNDLE_NAME, download);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }
}
