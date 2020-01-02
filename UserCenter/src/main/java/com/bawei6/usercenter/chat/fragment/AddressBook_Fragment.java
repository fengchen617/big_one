package com.bawei6.usercenter.chat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usercenter.R;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.impl.DefaultXmppUserImpl;

import org.jxmpp.jid.EntityFullJid;

import java.util.List;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：
 */
public class AddressBook_Fragment extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.im_add_friend, null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        recyclerView=view.findViewById(R.id.im_add_friend_re);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        EntityFullJid user = XmppManager.getInstance().getConnection().getUser();
//        List< EntityFullJid> friendList = XmppManager.getInstance().getXmppUserManager().getFriendList();
//
//        for (int i = 0; i < friendList.size(); i++) {
//           friendList.get(i);
//        }
//        recyclerView.setAdapter();
    }


}
