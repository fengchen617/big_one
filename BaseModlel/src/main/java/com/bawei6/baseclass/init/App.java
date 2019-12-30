package com.bawei6.baseclass.init;

import android.app.Application;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：初始化
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Arouter
        ARouter.init(this);
        ARouter.openDebug();
        ARouter.openLog();
        //分割Dex
        MultiDex.install(this);
    }
}
