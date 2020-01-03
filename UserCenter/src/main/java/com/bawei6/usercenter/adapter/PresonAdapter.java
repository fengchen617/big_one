package com.bawei6.usercenter.adapter;

import androidx.annotation.Nullable;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.AddressBookBean;
import com.bawei6.usercenter.bean.PresonBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：通讯录的适配器
 */
public class PresonAdapter extends BaseQuickAdapter<PresonBean, BaseViewHolder> {


    public PresonAdapter(int layoutResId, @Nullable List<PresonBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PresonBean item) {
        helper.setText(R.id.preson_item_str,item.getMsg())
                .addOnClickListener(R.id.preson_item_click);
    }
}
