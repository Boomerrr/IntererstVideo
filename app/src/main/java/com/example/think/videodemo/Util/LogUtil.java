package com.example.think.videodemo.Util;

import android.util.Log;

public class LogUtil {


    public static void  loging(String packageName, int priority, String description){

        String myName = "Boomerr";

        switch(priority){
            case 1:
                Log.d(myName + "  日志打印 " + packageName, description);
                break;
            case 2:
                Log.e(myName + " 日志打印 " + packageName, description);
                break;
        }
    }

}
