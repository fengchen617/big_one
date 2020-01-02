package com.bawei6.baseclass.bean;

/**
 * @author fengchen
 * @date 2020/1/2.
 * @description：添加通讯录好友
 */
public class AddFriendBean {
    private String name;
    private String phone_number;

    public AddFriendBean(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
