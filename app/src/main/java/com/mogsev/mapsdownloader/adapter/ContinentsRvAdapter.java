package com.mogsev.mapsdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mogsev.mapsdownloader.R;
import com.mogsev.mapsdownloader.activity.RegionActivity;
import com.mogsev.mapsdownloader.model.Continent;
import com.mogsev.mapsdownloader.model.RegionType;
import com.mogsev.mapsdownloader.utils.RegionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class ContinentsRvAdapter extends RecyclerView.Adapter<ContinentsRvAdapter.ViewHolder> {
    private static final String TAG = ContinentsRvAdapter.class.getSimpleName();

    private final List<Continent> mList;
    private final Context mContext;

    public ContinentsRvAdapter(@NonNull Context context) {
        Log.i(TAG, "ContinentsRvAdapter");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view_continent, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Continent continent = mList.get(position);

        // show name of continent
        String translate = RegionHelper.takeTranslateNameOfContinent(continent);
        holder.tvName.setText(translate);

        holder.viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + continent.toString());
                Intent intent = new Intent(mContext, RegionActivity.class);
                intent.putExtra(Continent.BUNDLE_NAME, continent);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(@Nullable List<Continent> list) {
        if (list != null && !list.isEmpty()) {
            for (Continent continent: list) {
                String type = continent.getType();
                if (!TextUtils.isEmpty(type)) {
                    switch (type) {
                        case RegionType.MAP:
                            break;
                        default:
                            mList.add(continent);
                            break;
                    }
                } else {
                    mList.add(continent);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * Class is like the helper
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup viewGroup;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            viewGroup = (ViewGroup) itemView.findViewById(R.id.constraint_layout);
            tvName = (TextView) itemView.findViewById(R.id.text_continent_name);
        }
    }
}
