package com.bawei6.usercenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.bean.MyChatBean;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

/**
 * @author fengchen
 * @date 2020/1/5.
 * @description：
 */
public class ChatAdapter extends BaseQuickAdapter<MyChatBean, BaseViewHolder> {


    public ChatAdapter(@Nullable List<MyChatBean> data) {
        super(data);
        //设置布局
        setMultiTypeDelegate(new MultiTypeDelegate<MyChatBean>() {
            @Override
            protected int getItemType(MyChatBean beanDate) {
                return beanDate.getType();
            }
        });
        //添加布局
        getMultiTypeDelegate()
                .registerItemType(MyChatBean.one,R.layout.chatroom_msglistfrom_layout_item)
                .registerItemType(MyChatBean.two,R.layout.chatroom_msglistto_layout_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyChatBean item) {
        int itemViewType = helper.getItemViewType();
        if(itemViewType==0){
            helper.setText(R.id.critem_from_tvmsg,item.getMsg());
            ImageView view = helper.getView(R.id.critem_from_img);
            Glide.with(mContext).load(item.getImageView()).into(view);
        }else if (itemViewType==1){
            helper.setText(R.id.critem_to_tvmsg,item.getMsg());
            ImageView view = helper.getView(R.id.critem_to_img);
            Glide.with(mContext).load(item.getImageView()).into(view);
        }
    }
}

