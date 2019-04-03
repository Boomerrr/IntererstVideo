package com.example.think.videodemo.Util.net;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintAnchor;

import com.example.think.videodemo.Util.LogUtil;

import java.util.Observable;
import java.util.Observer;


// 后台网络监测服务

public class NetMonitService extends Service {

    public static final String packageName = NetMonitService.class.getName();

    private Handler hander = new Handler();

    private IBinder iBinder = new MyBinder();

    public class MyBinder extends Binder{
        public NetMonitService getService() {
            return NetMonitService.this;    //返回本服务
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.loging(packageName,1,"网络监测服务建立");
        hander.post(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.loging(packageName,1,"网络监测服务执行");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        runnable = null;
        LogUtil.loging(packageName,1,"网络监测服务销毁");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(true){
                boolean isConnected = NetMonitor.checkNet(NetMonitService.this);
                LogUtil.loging(packageName,1,"当前网络状态  = " +  isConnected);
                SystemClock.sleep(5000);
            }
        }
    };

    public boolean onUnbind(Intent intent){
        return super.onUnbind(intent);
    }

}
