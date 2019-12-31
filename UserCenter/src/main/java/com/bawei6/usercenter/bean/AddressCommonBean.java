package com.bawei6.usercenter.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：
 */
public class AddressCommonBean implements CustomTabEntity {
    private String message;

    public AddressCommonBean(String message) {
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
