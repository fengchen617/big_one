package com.bawei6.usercenter.inituser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.usercenter.R;

public class FindActivity extends AppCompatActivity {
    private Button select_find_commit;
    private EditText select_find_phone;
    private EditText select_find_code;
    private EditText select_find_new_possword;
    private EditText select_find_two_possword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
    }

    private void initView() {
        select_find_commit = (Button) findViewById(R.id.select_find_commit);
        select_find_phone = (EditText) findViewById(R.id.select_find_phone);
        select_find_code = (EditText) findViewById(R.id.select_find_code);
        select_find_new_possword = (EditText) findViewById(R.id.select_find_new_possword);
        select_find_two_possword = (EditText) findViewById(R.id.select_find_two_possword);
        //提交修改
        onclick_commit();
    }

    private void onclick_commit() {
        select_find_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = select_find_phone.getText().toString();
                String code = select_find_code.getText().toString();
                
            }
        });
    }
}
