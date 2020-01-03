package com.bawei6.usercenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baseclass.Utils.ThreadUtils;
import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.msg.add.AddressBook_Activity;
import com.bawei6.usercenter.inituser.FindActivity;
import com.bawei6.usercenter.inituser.RegisterActivity;
import com.bawei6.usercenter.inituser.UserActivity;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.baweigame.xmpplibrary.XmppManager;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：登录
 */
@Route(path = "/usercenter/selectActivity")
public class SelectActivity extends UserActivity {
    private Presenter presenter = new Presenter();
    //头像
    private ImageView select_title_img;
    //用户名和密码
    private EditText select_ex_username;
    private EditText select_mm_password;
    //登录
    private Button select_login;
    //注册
    private TextView select_register;
    //记住密码和自动登录
    private CheckBox select_mima_remember;
    private CheckBox select_mima_auto;
    //找回密码
    private TextView select_find;

    //判断是否第一次登录
    boolean selected;
    boolean isflag;
    //SP存储
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ARouter.getInstance().inject(this);
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                XmppManager.getInstance();
            }
        });
        presenter.initview(this);
        initView();
    }

    private void initView() {
        select_title_img = (ImageView) findViewById(R.id.select_title_img);
        select_ex_username = (EditText) findViewById(R.id.select_ex_username);
        select_mm_password = (EditText) findViewById(R.id.select_mm_password);
        select_login = (Button) findViewById(R.id.select_login);
        select_register = (TextView) findViewById(R.id.select_register);
        select_mima_remember = (CheckBox) findViewById(R.id.select_mima_remember);
        select_mima_auto = (CheckBox) findViewById(R.id.select_mima_auto);
        select_find = (TextView) findViewById(R.id.select_find);
        sp = getSharedPreferences("a", MODE_PRIVATE);
         selected = select_mima_remember.isChecked();
         isflag = sp.getBoolean("isflag", false);
        String username = sp.getString("username", "");
        String password = sp.getString("password", "");
        select_ex_username.setText(username);
        select_mm_password.setText(password);
        select_mima_remember.setChecked(true);

        //登录
        onclick_login();
        //注册
        onclick_register();
        //头像
        onclick_title_img();
        //找回密码
        onclick_find();
    }

    private void onclick_find() {
        select_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, FindActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.guo,R.anim.guo);
            }
        });
    }

    private void onclick_title_img() {
        select_title_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开本地相册
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT < 19) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 101);
                    overridePendingTransition(R.anim.guo,R.anim.guo);
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
                overridePendingTransition(R.anim.guo,R.anim.guo);
            }
        });
    }

    private void onclick_login() {
        select_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isflag) {
                    String name = select_ex_username.getText().toString();
                    String poss = select_mm_password.getText().toString();
                    if(!name.equals(null) && !poss.equals(null)){
                        login(name, poss);
                    }else {
                        String username = sp.getString("username", "");
                        String password = sp.getString("password", "");
                        select_ex_username.setText(username);
                        select_mm_password.setText(password);
                        select_mima_remember.setChecked(true);
                        login(username, password);
                    }
                } else {
                    sp.edit().putBoolean("isflag", selected).apply();
                    final String user = select_ex_username.getText().toString();
                    final String pass = select_mm_password.getText().toString();
                    sp.edit().putString("username", user).putString("password", pass).apply();
                    login(user, pass);
                }
            }
        });
    }

    //登录
    public void login(String user, String pass) {
        presenter.getlogindata(user, pass);
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                if (XmppManager.getInstance().getXmppUserManager().login(user, pass)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SelectActivity.this, "登录成功", Toast.LENGTH_LONG);
                            Intent intent = new Intent(SelectActivity.this, AddressBook_Activity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.guo,R.anim.guo);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SelectActivity.this, "登录失败", Toast.LENGTH_LONG);
                        }
                    });
                }
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

    @Override
    public void login_data(UserInfoBean<LoginBean> loginBeanUserInfoBean) {
        super.login_data(loginBeanUserInfoBean);
        String usercode = loginBeanUserInfoBean.getData().getUsercode();
        Log.i("lyj","登陆后返回的usercode"+usercode);
        if(usercode!=null){
            sp.edit().putString("usercode",usercode).commit();
        }
    }
}
