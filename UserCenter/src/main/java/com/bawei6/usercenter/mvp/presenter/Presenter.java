package com.bawei6.usercenter.mvp.presenter;

import android.util.Log;

import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.baseclass.net.BaseObserver;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;
import com.bawei6.usercenter.mvp.contract.Contract;
import com.bawei6.usercenter.mvp.model.Model;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：
 */
public class Presenter extends Contract.Presenter {
    private Model model = new Model();
    Contract.View view;
    //登录
    private BaseObserver<UserInfoBean<LoginBean>> ob_login=new BaseObserver<UserInfoBean<LoginBean>>(){
        @Override
        public void onNext(UserInfoBean<LoginBean> loginBeanUserInfoBean) {
            super.onNext(loginBeanUserInfoBean);
            view.login_data(loginBeanUserInfoBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.i("lyj",e.getMessage());
        }
    };
    //注册
    private BaseObserver<UserInfoBean<RegisterBean>> ob_re=new BaseObserver<UserInfoBean<RegisterBean>>(){
        @Override
        public void onNext(UserInfoBean<RegisterBean> registerBeanUserInfoBean) {
            super.onNext(registerBeanUserInfoBean);
            view.register_data(registerBeanUserInfoBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    };
    @Override
    public void getlogindata(String username, String password) {
        model.getmodellogindata(username,password,ob_login);
    }

    @Override
    public void getredata(String id,String phoneusername, String password) {
        model.getmodelredata(id,phoneusername,password,ob_re);
    }

    @Override
    public void initview(Contract.View view) {
        this.view=view;
    }
}
