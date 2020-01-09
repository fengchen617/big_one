package com.bawei6.usercenter.msg.add.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baseclass.Utils.BaseConstant;
import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.adapter.FindFriendAdapter;
import com.bawei6.usercenter.bean.FindBean;
import com.bawei6.usercenter.bean.FindFriendBean;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;
import com.bawei6.usercenter.msg.chat.ChatActivity;
import com.bawei6.usercenter.mvp.contract.Contract;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：
 */
public class AddressBook_Fragment extends Fragment implements Contract.View {
    Presenter presenter=new Presenter();
    private RecyclerView recyclerView;
    private ArrayList<FindFriendBean> list_find=new ArrayList<>();
    private ArrayList<FindBean>list=new ArrayList<>();
    private FindFriendAdapter findFriendAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.im_add_friend, null);
        presenter.initview(this);
        String string = getContext().getSharedPreferences("a", Context.MODE_PRIVATE).getString("usercode", "f2c614cc443545b68d12e535107fa0a9");
        Log.i("lyj","sp:usercode"+string);
        presenter.getfinddata(string);
        initview(view);
        return view;
    }

    private void initview(View view) {
        recyclerView=view.findViewById(R.id.im_add_friend_re);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        findFriendAdapter=new FindFriendAdapter(R.layout.find_friend_item,list);
        findFriendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                if(id==R.id.find_friend_item_text){
                    BaseConstant.userName="123";
                    Intent intent = new Intent(getActivity(),ChatActivity.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(findFriendAdapter);
    }
    @Override
    public void login_data(UserInfoBean<LoginBean> loginBeanUserInfoBean) {

    }

    @Override
    public void register_data(UserInfoBean<RegisterBean> registerBeanUserInfoBean) {

    }

    @Override
    public void find_data(UserInfoBean<List<FindFriendBean>> registerBeanUserInfoBean) {
        List<FindFriendBean> data = registerBeanUserInfoBean.getData();
        int find_size = data.size() - 1;
        for (int i = 0; i < find_size; i++) {
            String username = data.get(i).getUsername();
            list.add(new FindBean(username));
        }
        findFriendAdapter.notifyDataSetChanged();
    }

}
