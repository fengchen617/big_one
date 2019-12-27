package com.bawei6.bigone;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.bigone.custom.Custom_Bottom;

/**
 * @author fengchen
 * @date 2019.12.27
 */
public class MainActivity extends BaseActivity {
    MapView mapView;
    private MapView map;
    private Custom_Bottom main_bb;
    private Drawable drawable;
    private Drawable drawable_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //定义了一个地图view
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        AMap aMap = mapView.getMap();
        if (aMap == null) {
            aMap = mapView.getMap();
        }

    }

    private void initView() {
        map = (MapView) findViewById(R.id.map);
        main_bb = (Custom_Bottom) findViewById(R.id.main_bb);
        initimg();
    }

    private void initimg() {
        final ImageView imageView_one = main_bb.getimg_one();
        final ImageView imageView_two = main_bb.getimg_two();
        final ImageView imageView_three = main_bb.getimg_three();
        final ImageView imageView_four = main_bb.getimg_four();
        final ImageView imageView_five = main_bb.getimg_five();

        imageView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = imageView_one.getDrawable();
                drawable_ = imageView_three.getDrawable();
                imageView_three.setImageDrawable(drawable);
                imageView_one.setImageDrawable(drawable_);
            }
        });

        imageView_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = imageView_two.getDrawable();
                drawable_ = imageView_three.getDrawable();
                imageView_three.setImageDrawable(drawable);
                imageView_two.setImageDrawable(drawable_);
            }
        });

        imageView_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = imageView_four.getDrawable();
                drawable_ = imageView_three.getDrawable();
                imageView_three.setImageDrawable(drawable);
                imageView_four.setImageDrawable(drawable_);
            }
        });

        imageView_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = imageView_five.getDrawable();
                drawable_ = imageView_three.getDrawable();
                imageView_three.setImageDrawable(drawable);
                imageView_five.setImageDrawable(drawable_);
            }
        });
    }
}