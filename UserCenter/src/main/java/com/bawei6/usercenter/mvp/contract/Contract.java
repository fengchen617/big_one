package com.bawei6.usercenter.mvp.contract;

import com.bawei6.baseclass.basemvp.BasePresenter;
import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;

import io.reactivex.Observer;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：
 */
public interface Contract {
    interface Model {
        void getmodellogindata(String username, String password, Observer<UserInfoBean<LoginBean>> observer);
        void getmodelredata(String phoneusername,String password,Observer<UserInfoBean<RegisterBean>> observer);
    }

    interface View {
        void login_data(UserInfoBean<LoginBean> loginBeanUserInfoBean);
        void register_data(UserInfoBean<RegisterBean> registerBeanUserInfoBean);
    }

    abstract  class Presenter<V extends View,M extends Model> extends BasePresenter {
        public abstract void getlogindata(String username, String password);
        public abstract void getredata(String phoneusername,String password);
        public abstract void initview(Contract.View view);
    }
}
