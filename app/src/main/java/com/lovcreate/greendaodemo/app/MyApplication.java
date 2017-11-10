package com.lovcreate.greendaodemo.app;

import android.app.Application;
import android.content.Context;

import com.lovcreate.greendaodemo.GreenDaoManager;

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
