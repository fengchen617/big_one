package com.bawei6.usercenter.msg.add.fragment.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.adapter.PresonAdapter;
import com.bawei6.usercenter.bean.AddressBookBean;
import com.bawei6.usercenter.bean.PresonBean;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.contract.IXmppFriend;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.library.ZXing3;

import java.util.ArrayList;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：找人的Fragment——Add下面的
 */
public class Add_Preson_Fragment extends Fragment {
    private TextView textView_phone;
    private TextView textView_friend;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<PresonBean>list=new ArrayList<>();
    private PresonAdapter presonAdapter;
    private String friend="";


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
        searchView=view.findViewById(R.id.add_preson_sear);
        recyclerView=view.findViewById(R.id.add_preson_re);
        //设置recyclerView与适配器
        setrecyclerView_and_adapter();
        //搜索框
        searchView_search();
       //适配器的ITEM点击事件
        onclick_adapter();
        //跳转通讯录
      jump_address_book();
      //扫描二维码 添加好友
        sao_add_friend();
    }

    private void sao_add_friend() {
        textView_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZXing3.openCamera(getActivity(), 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String dispose = ZXing3.dispose(data);
            Toast.makeText(getContext(), "" + dispose, Toast.LENGTH_SHORT).show();
        }
    }

    private void jump_address_book() {
        textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhoneActivity.class);
                startActivity(intent);
//                getFragmentManager().beginTransaction()
//                        .addToBackStack(null)
//                        .setCustomAnimations(R.anim.guo, R.anim.guo,R.anim.guo,R.anim.guo)
//                        .remove(getParentFragment())
////                        .replace(R.id.content_2, fragment)
//                        .commit();
            }
        });
    }

    private void onclick_adapter() {
        presonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.preson_item_click){
                    IXmppFriend xmppFriendManager = XmppManager.getInstance().getXmppFriendManager();
                    xmppFriendManager.addFriend(friend+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(), friend);
                    Toast.makeText(getContext(), "添加好友成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchView_search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for (int i = 0; i < PhoneActivity.addressbook_all.size(); i++) {
                    AddressBookBean addressBookBean = PhoneActivity.addressbook_all.get(i);
                    String name = addressBookBean.getName();
                    String phone = addressBookBean.getPhone();
                    if(query.equals(name)){
                        friend=name;
                        list.clear();
                        list.add(new PresonBean(name));
                        presonAdapter.notifyDataSetChanged();
                    }else if (query.equals(phone)){
                        friend=phone;
                        list.clear();
                        list.add(new PresonBean(phone));
                        presonAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setrecyclerView_and_adapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presonAdapter=new PresonAdapter(R.layout.preson_item,list);
        recyclerView.setAdapter(presonAdapter);
    }



}
