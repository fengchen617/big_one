package com.bawei6.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.baseclass.Utils.ThreadUtils;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.baweigame.xmpplibrary.XmppManager;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：注册
 */
public class RegisterActivity extends UserActivity {
    private Presenter presenter=new Presenter();
    private EditText register_ex_name;
    private EditText register_ex_poss;
    private Button register_button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        presenter.initview(this);
        register_button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getredata(register_ex_name.getText().toString(),register_ex_poss.getText().toString());
                ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                        XmppManager.getInstance()
                                .getXmppUserManager()
                                .createAccount(register_ex_name.getText().toString(),register_ex_poss.getText().toString());
                        Intent intent = new Intent(RegisterActivity.this, SelectActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}