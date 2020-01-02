package com.bawei6.bigone.time;

import androidx.annotation.Nullable;

import com.bawei6.bigone.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author fengchen
 * @date 2020/1/2.
 * @description：
 */
public class TimeAdapter extends BaseQuickAdapter<TiemTextBean, BaseViewHolder> {
    public TimeAdapter(int layoutResId, @Nullable List<TiemTextBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TiemTextBean item) {
        helper.setText(R.id.time_text_one,item.one);
        helper.setText(R.id.time_text_two,item.two);
        helper.setText(R.id.time_text_three,item.three);
    }
}
