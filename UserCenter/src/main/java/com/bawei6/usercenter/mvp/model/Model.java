package com.bawei6.usercenter.mvp.model;

import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.baseclass.net.RetrofitUtils;
import com.bawei6.usercenter.api.RxApi;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;
import com.bawei6.usercenter.mvp.contract.Contract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：
 */
public class Model implements Contract.Model {
    @Override
    public void getmodellogindata(String username, String password, Observer<UserInfoBean<LoginBean>> observer) {
        RetrofitUtils.getInstance().create(RxApi.class)
                .ob_login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getmodelredata(String phoneusername, String password, Observer<UserInfoBean<RegisterBean>> observer) {
        RetrofitUtils.getInstance().create(RxApi.class)
                .re_ob(new RegisterBean(1,phoneusername,password,"1","1","1","1","1",8,"1","1",1,1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
