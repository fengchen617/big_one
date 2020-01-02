package com.bawei6.usercenter.chat.fragment.add;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baseclass.Utils.ThreadUtils;
import com.bawei6.baseclass.ui.TitleBar;
import com.bawei6.usercenter.R;
import com.bawei6.usercenter.SelectActivity;
import com.bawei6.usercenter.adapter.AddressBookAdapter;
import com.bawei6.usercenter.bean.AddressBookBean;
import com.bawei6.usercenter.chat.AddActivity;
import com.bawei6.usercenter.inituser.RegisterActivity;
import com.bawei6.usercenter.inituser.UserActivity;
import com.bawei6.usercenter.mvp.presenter.Presenter;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.contract.IXmppFriend;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：手机联系人
 */
public class PhoneActivity extends UserActivity {
    Presenter presenter=new Presenter();
    //数据源
    private List<AddressBookBean> list = new ArrayList<>();
    private RecyclerView addressbook_re;
    //联系人的适配器
    private AddressBookAdapter addressBookAdapter;

    //通讯录TITLE
    private ArrayAdapter address_title;
    private List<String> list_count = new ArrayList();
    private ListView addressbook_list;
    private TitleBar phone_titlebar;
    private SearchView phone_search;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        //        动态权限（读取联系人）
        requestPermissions(new String[]{
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS"
        }, 1);
        presenter.initview(this);
        initView();
    }

    private void initView() {
        addressbook_re = findViewById(R.id.addressbook_re);
        addressbook_list = findViewById(R.id.addressbook_list);
        phone_titlebar = (TitleBar) findViewById(R.id.phone_titlebar);
        phone_search = (SearchView) findViewById(R.id.phone_search);
        ImageView imageView_left = phone_titlebar.getImageView_left();
        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置布局样式
        addressbook_re.setLayoutManager(new LinearLayoutManager(this));
        //实例化适配器
        addressBookAdapter = new AddressBookAdapter(list);
        //设置适配器
        addressbook_re.setAdapter(addressBookAdapter);
        initphone();
        //适配的点击事件
        addressBookAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                if(id==R.id.address_item_phone_jia){
                    AddressBookBean addressBookBean = list.get(position);
                    String phone = addressBookBean.getPhone();
                    Log.i("lyj","添加好友");
                    try {
                        VCard vCard = VCardManager.getInstanceFor(XmppManager.getInstance().getConnection()).loadVCard(JidCreate.entityBareFrom(phone));
                        Log.i("lyj","添加好友+v"+vCard.toString());
                        if (vCard!=null){
                            view.setVisibility(View.GONE);
                            //IM添加好友
                            IXmppFriend xmppFriendManager = XmppManager.getInstance().getXmppFriendManager();
                            xmppFriendManager.addFriend(phone+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(), phone);
                            Toast.makeText(PhoneActivity.this, "添加好友成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PhoneActivity.this, AddActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(PhoneActivity.this, "好友还未注册", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SmackException.NoResponseException e) {
                        e.printStackTrace();
                    } catch (XMPPException.XMPPErrorException e) {
                        e.printStackTrace();
                    } catch (SmackException.NotConnectedException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (XmppStringprepException e) {
                        e.printStackTrace();
                    }


//                    Intent intent = new Intent(PhoneActivity.this, RegisterActivity.class);
//                    intent.putExtra("zh",phone);
//                    startActivity(intent);

//                    presenter.getredata(phone,"123");
//                    ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            XmppManager.getInstance()
//                                    .getXmppUserManager()
//                                    .createAccount(phone,"123");
//                            Intent intent = new Intent(RegisterActivity.this, SelectActivity.class);
//                            startActivity(intent);
//                        }
//                    });

                }
            }
        });
        addressBookAdapter.notifyDataSetChanged();
        //搜索框
        phone_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for (int i = 0; i < list.size(); i++) {
                    AddressBookBean addressBookBean = list.get(i);
                    String name = addressBookBean.getName();
                    if(query.equals(name)){
                        list.clear();
                        list.add(addressBookBean);
                        addressBookAdapter.notifyDataSetChanged();
                        break;
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

    private void initphone() {
        //获取联系人的URI
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //获取资源
        ContentResolver contentResolver = this.getContentResolver();
        //查询并排序
        Cursor phonebook_label = contentResolver.query(contentUri, null, null, null, "phonebook_label");
        //用于判断
        String s = "";
        while (phonebook_label.moveToNext()) {
            //获取姓名
            String name = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //获取电话号
            String number = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //获取首写的大写字母
            String phonebook_label1 = phonebook_label.getString(phonebook_label.getColumnIndex("phonebook_label"));
            //添加数据
            AddressBookBean myBean = new AddressBookBean(name, number, phonebook_label1, 1);

            //头标题
            if (s.equals(phonebook_label1)) {
                list.add(myBean);
            } else {
                list.add(new AddressBookBean(null, null, phonebook_label1, 0));
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
        for (int i = 0; i < str.length(); i++) {
            //转成Char类型
            list_count.add(str.charAt(i) + "");
        }
        //实例化适配器
        address_title = new ArrayAdapter(this, R.layout.address_item_title, R.id.address_title, list_count);
        //设置适配器
        addressbook_list.setAdapter(address_title);
        //联动时的点击监听
        addressbook_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取数据的个数
                String count = list_count.get(position);
                for (int i = 0; i <= list.size() - 1; i++) {
                    //与头字母做比较
                    if (count.equals(list.get(i).getPhonebook_label())) {
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
