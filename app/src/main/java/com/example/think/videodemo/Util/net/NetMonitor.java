package com.example.think.videodemo.Util.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import static android.content.Context.CONNECTIVITY_SERVICE;

//网络监测类

public class NetMonitor {

    public static boolean checkNet(Context context){

        boolean isConnected = false;

        if (Build.VERSION.SDK_INT >= 23){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
            Network[] networks = connectivityManager.getAllNetworks();
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(networks[i]);
                if(networkInfo.isConnected()){
                    return true;
                }
            }
        }else if(Build.VERSION.SDK_INT < 23){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(networkInfo1.isConnected() || networkInfo2.isConnected()) {
                return true;
            }
        }
        return false;
    }

}
