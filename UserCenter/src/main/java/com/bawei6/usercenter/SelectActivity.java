package com.bawei6.usercenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baseclass.Utils.ThreadUtils;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.baweigame.xmpplibrary.XmppManager;
/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：登录
 */
@Route(path = "/bawei/selectActivity")
public class SelectActivity extends UserActivity {
    private Presenter presenter=new Presenter();
    //头像
    private ImageView select_title_img;
    //用户名和密码
    private EditText select_ex_username;
    private EditText select_mm_password;
    //登录
    private Button select_login;
    //注册
    private TextView select_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                XmppManager.getInstance();
            }
        });
        presenter.initview(this);
        initView();
        ARouter.getInstance().inject(this);
    }

    private void initView() {
        select_title_img = (ImageView) findViewById(R.id.select_title_img);
        select_ex_username = (EditText) findViewById(R.id.select_ex_username);
        select_mm_password = (EditText) findViewById(R.id.select_mm_password);
        select_login = (Button) findViewById(R.id.select_login);
        select_register = (TextView) findViewById(R.id.select_register);
        //登录
        onclick_login();
        //注册
        onclick_register();
        //头像
        onclick_title_img();
    }

    private void onclick_title_img() {
        select_title_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开本地相册
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT<19){
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                }else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                    startActivityForResult(intent,101);
                }
            }
        });
    }

    private void onclick_register() {
        select_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onclick_login() {
        select_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = select_ex_username.getText().toString();
                final String pass = select_mm_password.getText().toString();
                presenter.getlogindata(user,pass);
                ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                       if(XmppManager.getInstance().getXmppUserManager().login(user, pass)){
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(SelectActivity.this,"登录成功",Toast.LENGTH_LONG);
                                   Intent intent = new Intent(SelectActivity.this, AddActivity.class);
                                   startActivity(intent);
                               }
                           });
                       }else {
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(SelectActivity.this,"登录失败",Toast.LENGTH_LONG);
                               }
                           });
                       }
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                select_title_img.setImageURI(uri);
            }
        }
    }

}
