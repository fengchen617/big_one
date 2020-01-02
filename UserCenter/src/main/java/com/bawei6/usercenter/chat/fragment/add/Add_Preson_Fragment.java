package com.bawei6.usercenter.chat.fragment.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei6.usercenter.R;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：找人的Fragment——Add下面的
 */
public class Add_Preson_Fragment extends Fragment {
    private TextView textView_phone;
    private TextView textView_friend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_preson_fragment_item, null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        textView_phone=view.findViewById(R.id.add_preson_phone);
        textView_friend=view.findViewById(R.id.add_preson_friend);
        textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhoneActivity.class);
                startActivity(intent);
            }
        });
    }
}
