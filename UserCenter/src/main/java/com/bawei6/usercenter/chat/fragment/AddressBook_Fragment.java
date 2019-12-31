package com.bawei6.usercenter.chat.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usercenter.R;
import com.bawei6.usercenter.adapter.AddressBookAdapter;
import com.bawei6.usercenter.bean.AddressBookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：
 */
public class AddressBook_Fragment extends Fragment {
    //数据源
    private List<AddressBookBean> list = new ArrayList<>();
    private RecyclerView addressbook_re;
    //联系人的适配器
    private AddressBookAdapter addressBookAdapter;

    //通讯录TITLE
    private ArrayAdapter address_title;
    private List<String> list_count = new ArrayList();
    private ListView addressbook_list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addressbook_fragment_item, null);
        //        动态权限（读取联系人）
        requestPermissions(new String[]{
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS"
        },1);
        initView(view);
        return view;
    }

    private void initView(View view) {
        addressbook_re = view.findViewById(R.id.addressbook_re);
        addressbook_list = view.findViewById(R.id.addressbook_list);

        //设置布局样式
        addressbook_re.setLayoutManager(new LinearLayoutManager(getContext()));
        //实例化适配器
        addressBookAdapter=new AddressBookAdapter(list);
        //设置适配器
        addressbook_re.setAdapter(addressBookAdapter);

        //
        initphone();
    }
    private void initphone() {
        //获取联系人的URI
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //获取资源
        ContentResolver contentResolver = getActivity().getContentResolver();
        //查询并排序
        Cursor phonebook_label = contentResolver.query(contentUri, null, null, null, "phonebook_label");
        //用于判断
        String s="";
        while (phonebook_label.moveToNext()){
            //获取姓名
            String name = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //获取电话号
            String number = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //获取首写的大写字母
            String phonebook_label1 = phonebook_label.getString(phonebook_label.getColumnIndex("phonebook_label"));
            //添加数据
            AddressBookBean myBean = new AddressBookBean(name, number, phonebook_label1, 1);
            //头标题
            if(s.equals(phonebook_label1)){
                list.add(myBean);
            }else {
                //加数据
                list.add(new AddressBookBean(null,null,phonebook_label1,0));
                list.add(myBean);
                s = phonebook_label1;
            }
        }
        //更新适配器
        addressBookAdapter.notifyDataSetChanged();
        initListView();
    }

    private void initListView() {
        //联动添加的信息
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <str.length(); i ++){
            //转成Char类型
            list_count.add(str.charAt(i)+"");
        }
        //实例化适配器
        address_title=new ArrayAdapter(getContext(),R.layout.address_item_title,R.id.address_title,list_count);
        //设置适配器
        addressbook_list.setAdapter(address_title);
        //联动时的点击监听
        addressbook_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取数据的个数
                String count = list_count.get(position);
                for (int i=0;i<=list.size()-1;i++){
                    //与头字母做比较
                    if(count.equals(list.get(i).getPhonebook_label())){
                        //变换位置
                        addressbook_re.scrollToPosition(i);
                    }
                }
            }
        });
        //更新适配器
        address_title.notifyDataSetChanged();
    }
}
