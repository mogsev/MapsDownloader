package com.mogsev.mapsdownloader.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mogsev.mapsdownloader.R;
import com.mogsev.mapsdownloader.adapter.RegionsRvAdapter;
import com.mogsev.mapsdownloader.model.Continent;
import com.mogsev.mapsdownloader.model.DownloadItem;
import com.mogsev.mapsdownloader.model.DownloadResult;
import com.mogsev.mapsdownloader.service.DownloadService;
import com.mogsev.mapsdownloader.utils.RegionHelper;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class RegionActivity extends AppCompatActivity {
    private static final String TAG = RegionActivity.class.getSimpleName();

    private Continent mContinent;

    private RecyclerView mRecyclerView;
    private RegionsRvAdapter mRegionRvAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        if (savedInstanceState != null) {
            mContinent = savedInstanceState.getParcelable(Continent.BUNDLE_NAME);
        } else {
            mContinent = getIntent().getExtras().getParcelable(Continent.BUNDLE_NAME);
        }

        // initialize view elements
        initView();

        // fill adapter
        mRegionRvAdapter.setContinent(mContinent);

        // register receiver
        registerReceiver();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putParcelable(Continent.BUNDLE_NAME, mContinent);
    }

    private void registerReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadService.MESSAGE_DOWNLOAD_PROGRESS);
        broadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadService.MESSAGE_DOWNLOAD_PROGRESS)) {
                DownloadResult download = intent.getParcelableExtra(DownloadResult.BUNDLE_NAME);
                if (download != null) {
                    updateItem(download);
                    Log.i(TAG, "DownloadResult: " + download.toString());
                    if (download.getProgress() == 100) {
                        Log.i(TAG, "File Download Complete");
                    } else {
                        String result = String.format("Downloaded (%d/%d) MB", download.getCurrentFileSize(), download.getTotalFileSize());
                        Log.i(TAG, result);
                    }
                }
            }
        }
    };

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // show name of repository
        getSupportActionBar().setTitle(RegionHelper.takeTranslateNameOfContinent(mContinent));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_default);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRegionRvAdapter = new RegionsRvAdapter(RegionActivity.this);
        mRecyclerView.setAdapter(mRegionRvAdapter);
    }

    private void updateItem(DownloadResult download) {
        View view = null;
        ProgressBar progressBar = null;
        if (download.getCurrentItem().getContinentName().equals(mContinent.getName())) {
            int position = download.getCurrentItem().getPosition();
            view = mLinearLayoutManager.findViewByPosition(position);
            if (view != null) {
                progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
                if (download.getProgress() == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(download.getProgress());
                }
            }
        }
        List<DownloadItem> list = download.getDownloadItems();
        if (list != null && !list.isEmpty()) {
            Log.i(TAG, "List");
            for (DownloadItem item : list) {
                view = mLinearLayoutManager.findViewByPosition(item.getPosition());
                if (view != null) {
                    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                }
            }
        }
    }

}
