package com.bawei6.usercenter.msg.chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.baseclass.ui.TitleBar;
import com.bawei6.usercenter.R;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：聊天
 */
public class ChatActivity extends BaseActivity{

    //TitleBar
    private TitleBar chat_titlebar;
    private RecyclerView chat_re;
    //输入框与发送按钮
    private EditText chat_ex_send;
    private Button chat_button_send;
    //最下方的图片
    private ImageView chat_yuying;
    private ImageView chat_img;
    private ImageView chat_carmer;
    private ImageView chat_dingwei;
    private ImageView chat_biaoqing;
    private ImageView chat_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
    }

    private void initView() {
        chat_titlebar = (TitleBar) findViewById(R.id.chat_titlebar);
        chat_re = (RecyclerView) findViewById(R.id.chat_re);
        chat_ex_send = (EditText) findViewById(R.id.chat_ex_send);
        chat_button_send = (Button) findViewById(R.id.chat_button_send);
        chat_yuying = (ImageView) findViewById(R.id.chat_yuying);
        chat_img = (ImageView) findViewById(R.id.chat_img);
        chat_carmer = (ImageView) findViewById(R.id.chat_carmer);
        chat_dingwei = (ImageView) findViewById(R.id.chat_dingwei);
        chat_biaoqing = (ImageView) findViewById(R.id.chat_biaoqing);
        chat_more = (ImageView) findViewById(R.id.chat_more);
        //TitleBar的事件处理
        set_chat_titlebar_onclick();
        //发送消息
        set_chat_button_send_onclick();
    }

    private void set_chat_titlebar_onclick() {
        ImageView imageView_left = chat_titlebar.getImageView_left();
        ImageView imageView_center = chat_titlebar.getImageView_center();
        ImageView imageView_right = chat_titlebar.getImageView_right();
        //返回上一层页面
        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //打电话
        imageView_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐示跳转
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);//设置频道
                //tel 加电话号
                intent.setData(Uri.parse("tel:" + "15831488471"));
                startActivity(intent);
            }
        });
        //聊天设置
        imageView_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, Chat_Settings_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void set_chat_button_send_onclick() {
        chat_button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取要发送的消息
                String msg = chat_ex_send.getText().toString();
            }
        });
    }


}
