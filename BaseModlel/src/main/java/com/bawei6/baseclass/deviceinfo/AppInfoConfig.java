package com.bawei6.baseclass.deviceinfo;

import android.content.Context;
import android.content.pm.PackageManager;
/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：获取配置信息
 */
public class AppInfoConfig {

    private static volatile AppInfoConfig singleton;

    private AppInfoConfig() {
    }

    public static AppInfoConfig getInstance() {
        if (singleton == null) {
            synchronized (AppInfoConfig.class) {
                if (singleton == null) {
                    singleton = new AppInfoConfig();
                }
            }
        }
        return singleton;
    }
    private Context context;
    public void init(Context context){
        this.context = context;
    }


    public String getPackageNames(){
        return context.getPackageName();
    }

    public String getVersionCode(){
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getVersionName(){
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).versionName+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}