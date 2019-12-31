package com.bawei6.usercenter.inituser;

import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.baseclass.ui.BaseActivity;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;
import com.bawei6.usercenter.mvp.contract.Contract;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：重新View的方法，避免代码重复
 */
public class UserActivity extends BaseActivity implements Contract.View {
    @Override
    public void login_data(UserInfoBean<LoginBean> loginBeanUserInfoBean) {

    }

    @Override
    public void register_data(UserInfoBean<RegisterBean> registerBeanUserInfoBean) {

    }
}
