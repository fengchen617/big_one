package com.bawei6.usercenter.chat;

import android.os.Bundle;

import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.AddCommonBean;
import com.bawei6.usercenter.chat.fragment.AddressBook_Fragment;
import com.bawei6.usercenter.chat.fragment.add.Add_Preson_Fragment;
import com.bawei6.usercenter.chat.fragment.add.Add_Qun_Fragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：添加的功能
 */
public class AddActivity extends BaseActivity {

    private ArrayList<CustomTabEntity> list_common = new ArrayList<>();
    private CommonTabLayout add_main_common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        add_main_common = (CommonTabLayout) findViewById(R.id.add_main_common);
        initdata();
    }

    private void initdata() {
        list_common.add(new AddCommonBean("找人"));
        list_common.add(new AddCommonBean("找群"));
        add_main_common.setTabData(list_common);
        add_main_common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position==0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.address_main_f,new Add_Preson_Fragment()).commit();
                }else if(position==1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.address_main_f,new Add_Qun_Fragment()).commit();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
