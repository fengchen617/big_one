package com.bawei6.usercenter.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baseclass.Utils.BaseConstant;
import com.bawei6.usercenter.R;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchen
 * @date 2020/1/6
 * @description
 */
public class MyChatRecylerAdapter extends RecyclerView.Adapter<MyChatRecylerAdapter.MyViewHolder> {

    private Context context;

    public MyChatRecylerAdapter(Context context) {
        this.context = context;
    }

    private List<MsgEntity> msgEntities = new ArrayList<>();

    public void setMsgEntities(MsgEntity msgEntity){
        msgEntities.add(msgEntity);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (msgEntities.get(position).getTo().equals(BaseConstant.userName)){
            return 0;
        }else {
            return 1;
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_to,parent,false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_from,parent,false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (msgEntities.get(position).getTo().equals(BaseConstant.userName)){
            switch (msgEntities.get(position).getMsgType()){
                case Txt:
                    holder.text_message.setText(msgEntities.get(position).getMsg());
                    break;
                case Audio:
                    holder.text_message.setText("[语音]");
                    holder.text_message.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            try {
                                mediaPlayer.setDataSource(msgEntities.get(position).getMsg());
                                mediaPlayer.prepareAsync();
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mediaPlayer) {
                                        mediaPlayer.start();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
                case Video:
                    holder.text_message.setVisibility(View.GONE);
                    holder.image_chat.setVisibility(View.VISIBLE);
                    holder.image_chat.setImageBitmap(BitmapFactory.decodeFile(msgEntities.get(position).getMsg()));
                    break;
                    default:
            }
        }else {
            if (msgEntities.get(position).getMsg().contains(".mp3")){
                holder.text_message.setText("[语音]");
                holder.text_message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(msgEntities.get(position).getMsg());
                            mediaPlayer.prepareAsync();
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mediaPlayer) {
                                    mediaPlayer.start();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else if (msgEntities.get(position).getMsg().contains(".png")){
                holder.text_message.setVisibility(View.GONE);
                holder.image_chat.setVisibility(View.VISIBLE);
                Glide.with(context).load(msgEntities.get(position).getMsg()).into(holder.image_chat);
            }
            else {
                holder.text_message.setText(msgEntities.get(position).getMsg());
            }
        }

    }

    @Override
    public int getItemCount() {
        return msgEntities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text_message;
        private ImageView image_chat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_message = itemView.findViewById(R.id.text_chat_to_message);
            image_chat = itemView.findViewById(R.id.image_chat);
        }
    }
}
