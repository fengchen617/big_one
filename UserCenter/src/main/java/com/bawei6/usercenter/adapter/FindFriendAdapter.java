package com.bawei6.usercenter.adapter;

import androidx.annotation.Nullable;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.FindBean;
import com.bawei6.usercenter.bean.FindFriendBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author fengchen
 * @date 2020/1/3.
 * @description：好友列表的适配器
 */
public class FindFriendAdapter extends BaseQuickAdapter<FindBean, BaseViewHolder> {
    public FindFriendAdapter(int layoutResId, @Nullable List<FindBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindBean item) {
        helper.setText(R.id.find_friend_item_text,item.getString())
        .addOnClickListener(R.id.find_friend_item_text);
    }
}
