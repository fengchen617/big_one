package com.bawei6.baseclass.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：测量沉浸式的高度
 */
public class StartView extends View {
    //高度
    private static int mheight;
    public StartView(Context context) {
        super(context);
    }

    public StartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mheight=getStartView(context);
    }

    public StartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //储存测量时候的宽高
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mheight);
    }



    public static int getStartView(Context context){
        if(mheight==0){
            //获取资源
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("start_bar_height", "dimen", "android");
            if (resourceId > 0) {
                mheight = resources.getDimensionPixelSize(resourceId);
            }
        }
        return mheight;
    }
}
