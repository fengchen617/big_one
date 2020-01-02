package com.bawei6.bigone.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bawei6.bigone.R;

/**
 * @author fengchen
 * @date 2020/1/2.
 * @description：
 */
public class TimeView extends LinearLayout {
    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context,attrs);
    }

    private void initview(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.attr_time,null);
        this.addView(view);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
