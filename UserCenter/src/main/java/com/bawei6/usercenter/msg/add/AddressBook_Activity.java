package com.bawei6.usercenter.msg.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.baseclass.ui.TitleBar;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.AddressCommonBean;
import com.bawei6.usercenter.msg.add.fragment.AddressBook_Fragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：通讯录
 */
public class AddressBook_Activity extends BaseActivity {

    private RecyclerView address_main_re;
    private CommonTabLayout address_main_common;
    private FrameLayout address_main_f;
    private ArrayList<CustomTabEntity> list_common = new ArrayList<>();
    private TitleBar addressbook_titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book_);
        initView();

    }


    private void initView() {
        address_main_re = (RecyclerView) findViewById(R.id.address_main_re);
        address_main_common = (CommonTabLayout) findViewById(R.id.address_main_common);
        address_main_f = (FrameLayout) findViewById(R.id.address_main_f);
        addressbook_titlebar = (TitleBar) findViewById(R.id.addressbook_titlebar);
        onclickbar();
        initdata();
    }

    private void onclickbar() {
        //TITLEBar的左方图片的处理
        ImageView imageView_left = addressbook_titlebar.getImageView_left();
        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //TITLEBar的you方图片的处理
        ImageView imageView_right = addressbook_titlebar.getImageView_right();
        imageView_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressBook_Activity.this, AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.guo,R.anim.guo);
            }
        });
    }

    private void initdata() {
        list_common.add(new AddressCommonBean("好友"));
        list_common.add(new AddressCommonBean("分组"));
        list_common.add(new AddressCommonBean("群聊"));

        address_main_common.setTabData(list_common);
        address_main_common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.address_main_f, new AddressBook_Fragment()).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.address_main_f, new AddressBook_Fragment()).commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.address_main_f, new AddressBook_Fragment()).commit();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
