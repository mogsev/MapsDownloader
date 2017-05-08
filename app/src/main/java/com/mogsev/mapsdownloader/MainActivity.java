package com.mogsev.mapsdownloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mogsev.mapsdownloader.adapter.ContinentsRvAdapter;
import com.mogsev.mapsdownloader.model.RegionsList;
import com.mogsev.mapsdownloader.utils.DiskSpaceHelper;
import com.mogsev.mapsdownloader.utils.Settings;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String FILE_REGIONS = "regions.xml";

    private RecyclerView mRecyclerView;
    private ContinentsRvAdapter mContinentRvAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private TextView mTextAvailableMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize view elements
        initView();

        if (Settings.getInstance().isFirstStart()) {
            //The good way is to save the data into SQLite but not this time
            try {

                Serializer serializer = new Persister();
                InputStream inputStream = getAssets().open(FILE_REGIONS);
                RegionsList regionsList = serializer.read(RegionsList.class, inputStream, false);
                Log.d(TAG, regionsList.toString());
                mContinentRvAdapter.setList(regionsList.getRegionList());
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                Toast.makeText(this, "Error:", Toast.LENGTH_LONG).show();
            }
        } else {
            // I think, if we open app not first time,
            // we can load data from URL and to save a data into SQLite.
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_download_maps);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_default);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mContinentRvAdapter = new ContinentsRvAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mContinentRvAdapter);

        mTextAvailableMemory = (TextView) findViewById(R.id.text_available_memory);
        long space = DiskSpaceHelper.getAvailableSpace();
        String memory = getString(R.string.device_free_memory, DiskSpaceHelper.takeSize(MainActivity.this, space));
        mTextAvailableMemory.setText(memory);
    }


}
