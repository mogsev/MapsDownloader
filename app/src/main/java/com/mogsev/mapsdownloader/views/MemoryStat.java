package com.mogsev.mapsdownloader.views;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mogsev.mapsdownloader.R;
import com.mogsev.mapsdownloader.utils.DiskSpaceHelper;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class MemoryStat extends FrameLayout {
    private static final String TAG = MemoryStat.class.getSimpleName();

    private ImageView mImageView;

    public MemoryStat(@NonNull Context context) {
        super(context);
        initView();
    }

    public MemoryStat(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MemoryStat(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int height = getMeasuredHeight();
        final int width = getMeasuredWidth();
        Log.i(TAG, "height: " + height + " width: " + width);

        int widthStat = width - (int) (width * DiskSpaceHelper.takeRateAvailableSpaceToTotalSpace());
        mImageView.getLayoutParams().width = widthStat;
        mImageView.requestLayout();
    }

    private void initView() {
        Log.i(TAG, "initView");

        // create ImageView
        mImageView = new ImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.LEFT;
        mImageView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        addView(mImageView, layoutParams);
    }
}
