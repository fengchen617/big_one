package com.bawei6.usercenter.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：Add添加
 */
public class AddCommonBean implements CustomTabEntity {
    private String message;

    public AddCommonBean(String message) {
        this.message = message;
    }

    @Override
    public String getTabTitle() {
        return message;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
