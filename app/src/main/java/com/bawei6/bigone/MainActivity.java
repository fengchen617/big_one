package com.bawei6.bigone;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.baseclass.net.ValPool;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.bigone.custom.Custom_Bottom;
import com.bawei6.usercenter.SelectActivity;
import com.bawei6.usercenter.chat.AddressBook_Activity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author fengchen
 * @date 2019.12.27
 */

public class MainActivity extends BaseActivity {
    MapView mapView;
    private MapView map;
    private Custom_Bottom main_bb;
    //接收自定义View中的图片
    private Drawable drawable;
    private Drawable drawable_;
    private ImageView main_title_img;
    private SearchView main_title_search;
    private ImageView main_sao_img;
    private DrawerLayout drawer_layout;
    //抽屉中的
    private ImageView d_item_title;
    private ImageView d_item_camera;
    private TextView d_title_name;
    private EditText d_title_ex;
    //抽屉中的X(关闭)
    private ImageView drawer_close;

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
        //主页图片的切换(自定义的)
        initimg();
        main_title_img = (ImageView) findViewById(R.id.main_title_img);
        main_title_search = (SearchView) findViewById(R.id.main_title_search);
        main_sao_img = (ImageView) findViewById(R.id.main_sao_img);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //初始化主页的TITLE
        init_title();
        d_item_title = (ImageView) findViewById(R.id.d_item_title);
        d_item_camera = (ImageView) findViewById(R.id.d_item_camera);
        d_title_name = (TextView) findViewById(R.id.d_title_name);
        d_title_ex = (EditText) findViewById(R.id.d_title_ex);
        drawer_close = (ImageView) findViewById(R.id.drawer_close);
        //初始化抽屉中的控件
        init_drawer();

    }

    //抽屉中的
    private void init_drawer() {
        //加载头像
        Glide.with(this).load(ValPool.Main_Title_Img)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(d_item_title);
        d_item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //打开本地相册
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT<19){
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                }else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                    startActivityForResult(intent,101);
                }
            }
        });
        //关闭抽屉
        drawer_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    //初始化主页的TITLE
    private void init_title() {
        //加载头像
        Glide.with(this).load(ValPool.Main_Title_Img)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(main_title_img);
        //打开抽屉
        main_title_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
    }

    //主页图片的切换
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
                Intent intent = new Intent(MainActivity.this, AddressBook_Activity.class);
                startActivity(intent);

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
                ARouter.getInstance().build("/usercenter/selectActivity")
                        .navigation();
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                main_title_img.setImageURI(uri);
            }
        }
    }

}