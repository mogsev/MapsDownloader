package com.mogsev.mapsdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mogsev.mapsdownloader.R;
import com.mogsev.mapsdownloader.model.Continent;
import com.mogsev.mapsdownloader.model.DownloadItem;
import com.mogsev.mapsdownloader.model.Region;
import com.mogsev.mapsdownloader.service.DownloadService;
import com.mogsev.mapsdownloader.utils.RegionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class RegionsRvAdapter extends RecyclerView.Adapter<RegionsRvAdapter.ViewHolder> {
    private static final String TAG = RegionsRvAdapter.class.getSimpleName();

    private final List<Region> mList;
    private final Context mContext;

    private Continent mContinent;

    public RegionsRvAdapter(@NonNull Context context) {
        Log.i(TAG, "RegionsRvAdapter");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view_region, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageDownload.setVisibility(View.VISIBLE);
        holder.progressBar.setVisibility(View.GONE);
        holder.progressBar.setIndeterminate(false);

        final Region region = mList.get(position);

        Log.i(TAG, "region: " + region.toString());

        // show name of continent
        String translate = RegionHelper.takeTranslateNameOfContinent(region);
        holder.tvName.setText(translate);

        if (region.getSubRegions() != null) {
            Log.e(TAG, "SubRegions: " + region.getSubRegions().toString());
            holder.imageDownload.setVisibility(View.GONE);
            holder.viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "some actions");
                }
            });
        } else {
            holder.viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick");
                    String fileName = RegionHelper.takeFileName(mContinent, region);
                    Log.i(TAG, "Filename: " + fileName);
                    DownloadItem downloadItem = new DownloadItem();
                    downloadItem.setContinentName(mContinent.getName());
                    downloadItem.setRegionName(region.getName());
                    downloadItem.setFileName(fileName);
                    downloadItem.setPosition(position);
                    Intent intent = new Intent(mContext, DownloadService.class);
                    intent.putExtra(DownloadService.EXTRA_DOWNLOAD_ITEM, downloadItem);
                    mContext.startService(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setContinent(@NonNull Continent continent) {
        mContinent = continent;
        setList(continent.getRegions());
    }

    public void setList(@Nullable List<Region> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * Class is like the helper
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup viewGroup;
        public TextView tvName;
        public ImageView imageDownload;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            viewGroup = (ViewGroup) itemView.findViewById(R.id.constraint_layout);
            tvName = (TextView) itemView.findViewById(R.id.text_region_name);
            imageDownload = (ImageView) itemView.findViewById(R.id.image_download);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }
}
