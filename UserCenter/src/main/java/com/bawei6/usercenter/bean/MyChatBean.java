package com.bawei6.usercenter.bean;

import android.net.Uri;
import android.widget.ImageView;

import java.net.URL;

/**
 * @author fengchen
 * @date 2020/1/6.
 * @description：
 */
public class MyChatBean {
    public static int one=0;
    public static int two=1;
    int type;
    String msg;
    Uri imageView;

    public MyChatBean(int type, String msg, Uri imageView) {
        this.type = type;
        this.msg = msg;
        this.imageView = imageView;
    }

    public static int getOne() {
        return one;
    }

    public static void setOne(int one) {
        MyChatBean.one = one;
    }

    public static int getTwo() {
        return two;
    }

    public static void setTwo(int two) {
        MyChatBean.two = two;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Uri getImageView() {
        return imageView;
    }

    public void setImageView(Uri imageView) {
        this.imageView = imageView;
    }
}
