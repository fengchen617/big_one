package com.bawei6.bigone;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.baseclass.net.ValPool;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.bigone.custom.Custom_Bottom;
import com.bawei6.bigone.time.TiemTextBean;
import com.bawei6.bigone.time.TimeAdapter;
import com.bawei6.usercenter.SelectActivity;
import com.bawei6.usercenter.msg.add.AddressBook_Activity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.ZXing3;

import java.util.ArrayList;

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

    //时间轴的适配器
    private TimeAdapter timeAdapter;
    private ArrayList<TiemTextBean> list = new ArrayList<>();
    private boolean iftime=false;
    //    ValueAnimator valueAnimator;
    //活动
    private RecyclerView main_time_re;
    private TextView main_time_text;

    //
    boolean ischun=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义了一个地图view
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        initView();
        //初始化地图控制器对象
        initmap();
    }
    private void initmap() {
        AMap aMap = mapView.getMap();
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LYJ","SDADASDSA");
                if(ischun){
                    main_bb.setVisibility(View.GONE);
                    main_time_re.setVisibility(View.GONE);
                    main_time_text.setVisibility(View.GONE);
                    main_title_img.setVisibility(View.GONE);
                    main_title_search.setVisibility(View.GONE);
                    main_sao_img.setVisibility(View.GONE);
                    ischun=false;
                }else {
                    main_bb.setVisibility(View.VISIBLE);
                    main_time_re.setVisibility(View.VISIBLE);
                    main_time_text.setVisibility(View.VISIBLE);
                    main_title_img.setVisibility(View.VISIBLE);
                    main_title_search.setVisibility(View.VISIBLE);
                    main_sao_img.setVisibility(View.VISIBLE);
                    ischun=true;
                }
            }
        });
        //去掉地图右下角缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }
    private void initView() {
        main_time_re = findViewById(R.id.main_time_re);
        map = (MapView) findViewById(R.id.map);
        main_bb = (Custom_Bottom) findViewById(R.id.main_bb);
        //时间轴
        init_time();
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

        main_time_text = (TextView) findViewById(R.id.main_time_text);
        //时间轴的展开与缩小
        isshow_time();
        //扫描二维码
        scan_two_code();
    }
    private void scan_two_code() {
        main_sao_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZXing3.openCamera(MainActivity.this, 2);
            }
        });
    }
    private void isshow_time() {
        main_time_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iftime){
                    main_time_re.setVisibility(View.VISIBLE);
                    iftime=false;
                }else {
                    main_time_re.setVisibility(View.GONE);
                    iftime=true;
                }
            }
        });
    }
    private void init_time() {
        main_time_re.setLayoutManager(new LinearLayoutManager(this));
        list.add(new TiemTextBean("第一次加载的第一个TextView", "第一次加载的第二个TextView", "第一次加载的第三个TextView"));
        list.add(new TiemTextBean("第二次加载的第一个TextView", "第二次加载的第二个TextView", "第二次加载的第三个TextView"));
        list.add(new TiemTextBean("第三次加载的第一个TextView", "第三次加载的第二个TextView", "第三次加载的第三个TextView"));
        timeAdapter = new TimeAdapter(R.layout.time_item, list);
        main_time_re.setAdapter(timeAdapter);
        timeAdapter.notifyDataSetChanged();
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
                if (Build.VERSION.SDK_INT < 19) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 101);
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

        d_item_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                valueAnimator = ValueAnimator.ofInt(0, 3);
//                valueAnimator.setDuration(2000);
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        int animatedValue = (int) animation.getAnimatedValue();
//                        d_item_camera.setPadding(animatedValue, animatedValue, 300, 300);
//                    }
//                });
//                valueAnimator.start();
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
                overridePendingTransition(R.anim.guo,R.anim.guo);
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
        }else if (requestCode == 2) {
            String dispose = ZXing3.dispose(data);
            Toast.makeText(this, "" + dispose, Toast.LENGTH_SHORT).show();
        }
    }
}