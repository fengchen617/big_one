package com.bawei6.usercenter.inituser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei6.baseclass.Utils.ThreadUtils;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.SelectActivity;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.baweigame.xmpplibrary.XmppManager;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：注册
 */
public class RegisterActivity extends UserActivity {
    private Presenter presenter = new Presenter();
    private EditText register_ex_name;
    private EditText register_ex_poss;
    private Button register_button_ok;

    private String zh;
    private EditText register_ex_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Intent intent = new Intent();
//        zh = intent.getStringExtra("zh");
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                XmppManager.getInstance();
            }
        });
        initView();
    }

    private void initView() {
        register_ex_name = (EditText) findViewById(R.id.register_ex_name);
        register_ex_poss = (EditText) findViewById(R.id.register_ex_poss);
        register_button_ok = (Button) findViewById(R.id.register_button_ok);
        register_ex_id = (EditText) findViewById(R.id.register_ex_id);
        presenter.initview(this);
        register_button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getredata(register_ex_id.getText().toString(),register_ex_name.getText().toString(), register_ex_poss.getText().toString());
                ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                        XmppManager.getInstance()
                                .getXmppUserManager()
                                .createAccount(register_ex_name.getText().toString(), register_ex_poss.getText().toString());
                        Intent intent = new Intent(RegisterActivity.this, SelectActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

}