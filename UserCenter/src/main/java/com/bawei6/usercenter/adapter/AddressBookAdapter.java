package com.bawei6.usercenter.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.AddressBookBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：通讯录的适配器
 */
public class AddressBookAdapter extends BaseQuickAdapter<AddressBookBean, BaseViewHolder> {


    public AddressBookAdapter(@Nullable List<AddressBookBean> data) {
        super(data);
        //设置多布局的个数
        setMultiTypeDelegate(new MultiTypeDelegate<AddressBookBean>() {
            @Override
            protected int getItemType(AddressBookBean myBean) {
                return myBean.getType();
            }
        });
        //获取多布局
        getMultiTypeDelegate()
                .registerItemType(0, R.layout.address_item_title)
                .registerItemType(1,R.layout.address_item_phone);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBookBean item) {
        int type = item.getType();
        switch (type){
            //字母标题布局
            case 0:
                helper.setText(R.id.address_title,item.getPhonebook_label());
                break;
            //姓名与电话号布局
            case 1:
                helper.setText(R.id.address_name,item.getName());
                helper.setText(R.id.address_phone,item.getPhone());
                helper.addOnClickListener(R.id.address_item_phone_jia);
                break;
                default:break;
        }
    }
}
