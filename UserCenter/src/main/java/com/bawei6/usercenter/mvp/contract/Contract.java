package com.bawei6.usercenter.mvp.contract;

import com.bawei6.baseclass.basemvp.BasePresenter;
import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.usercenter.bean.FindFriendBean;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：
 *   //登录时返回的数据
 *   //注册时返回的数据
 *   //好友列表返回的数据
 */
public interface Contract {
    interface Model {
        void getmodellogindata(String username, String password, Observer<UserInfoBean<LoginBean>> observer);
        void getmodelredata(String id,String phoneusername,String password,Observer<UserInfoBean<RegisterBean>> observer);
        void getmodelfinddata(String usercode,Observer<UserInfoBean<List<FindFriendBean>>> observer);
    }

    interface View {
        void login_data(UserInfoBean<LoginBean> loginBeanUserInfoBean);
        void register_data(UserInfoBean<RegisterBean> registerBeanUserInfoBean);
        void find_data(UserInfoBean<List<FindFriendBean>> registerBeanUserInfoBean);
    }

    abstract  class Presenter<V extends View,M extends Model> extends BasePresenter {
        public abstract void getlogindata(String username, String password);
        public abstract void getredata(String id,String phoneusername,String password);
        public abstract void initview(Contract.View view);
        public abstract void getfinddata(String usercode);
    }
}
