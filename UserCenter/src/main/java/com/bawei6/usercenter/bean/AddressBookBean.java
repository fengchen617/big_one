package com.bawei6.usercenter.bean;

/**
 * @author fengchen
 * @date 2019/12/31.
 * @description：通讯录的Bean类
 */
public class AddressBookBean {
    //联系人的姓名
    private String name;
    //联系人的电话
    private String phone;
    //phonebook_label用于排序和判断
    private String phonebook_label;
    //判断多布局
    private int type;

    public AddressBookBean(String name, String phone, String phonebook_label, int type) {
        this.name = name;
        this.phone = phone;
        this.phonebook_label = phonebook_label;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhonebook_label() {
        return phonebook_label;
    }

    public void setPhonebook_label(String phonebook_label) {
        this.phonebook_label = phonebook_label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
