package com.example.think.videodemo.Util.net;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.telephony.NetworkScan;

import com.example.think.videodemo.Util.LogUtil;

// 后台网络监测服务

public class NetMonitService extends Service {

    private boolean isConnected;

    public static final String packageName = NetMonitService.class.getName();

    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    hander.post(runnable);
                    Message message = new Message();
                    message.what = 1;
                    hander.sendMessageDelayed(message,5000);
            }
        }
    };

    private IBinder iBinder = new MyBinder();

    public class MyBinder extends Binder{

        public NetMonitService getService() {
            return NetMonitService.this;    //返回本服务
        }

        public boolean getState(){
            return isConnected ;
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
        Message msg = new Message();
        msg.what = 1;
        hander.sendMessage(msg);
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
        hander = null;
        LogUtil.loging(packageName,1,"网络监测服务销毁");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
                isConnected = NetMonitor.checkNet(NetMonitService.this);
                LogUtil.loging(packageName,1,"当前网络状态  = " +  isConnected);
                if(isConnected){
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction("net.is.ok");
                    NetMonitService.this.sendBroadcast(intent);
                }else{
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction("net.isnot.ok");
                    NetMonitService.this.sendBroadcast(intent);
                }
        }
    };

    public boolean onUnbind(Intent intent){
        return super.onUnbind(intent);
    }


}
