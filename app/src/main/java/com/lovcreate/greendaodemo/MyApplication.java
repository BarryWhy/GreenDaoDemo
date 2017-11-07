package com.lovcreate.greendaodemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by wanghaoyu on 2017/11/6.
 */

public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        GreenDaoManager.getInstance();
    }
}
