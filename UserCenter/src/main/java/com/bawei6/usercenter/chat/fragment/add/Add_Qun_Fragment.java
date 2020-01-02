package com.bawei6.usercenter.chat.fragment.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei6.usercenter.R;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：找群的Fragment——Add下面的
 */
public class Add_Qun_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_preson_fragment_item, null);
        initview(view);
        return view;
    }

    private void initview(View view) {
    }
}
