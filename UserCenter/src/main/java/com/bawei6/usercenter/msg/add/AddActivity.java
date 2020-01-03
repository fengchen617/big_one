package com.bawei6.usercenter.msg.add;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.bawei6.baseclass.ui.TitleBar;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.AddCommonBean;
import com.bawei6.usercenter.msg.add.fragment.add.Add_Preson_Fragment;
import com.bawei6.usercenter.msg.add.fragment.add.Add_Qun_Fragment;
import com.bawei6.usercenter.inituser.UserActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：添加的功能
 */
public class AddActivity extends UserActivity {
    //页面的切换
    private ArrayList<CustomTabEntity> list_common = new ArrayList<>();
    private CommonTabLayout add_main_common;
    //头布局
    private TitleBar add_titlebar;
    private FrameLayout add_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        add_titlebar = (TitleBar) findViewById(R.id.add_titlebar);
        add_main_common = (CommonTabLayout) findViewById(R.id.add_main_common);
        add_f = (FrameLayout) findViewById(R.id.add_f);
        initdata();
    }

    private void initdata() {
        list_common.add(new AddCommonBean("找人"));
        list_common.add(new AddCommonBean("找群"));
        add_main_common.setTabData(list_common);
        add_main_common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.add_f, new Add_Preson_Fragment()).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.add_f, new Add_Qun_Fragment()).commit();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
