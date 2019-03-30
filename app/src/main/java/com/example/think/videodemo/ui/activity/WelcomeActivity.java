package com.example.think.videodemo.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.LocationBean;
import com.example.think.videodemo.MainActivity;
import com.example.think.videodemo.R;
import com.example.think.videodemo.mvp.Contract.WelcomContract;
import com.example.think.videodemo.mvp.Presenter.MainVideoAdversePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WelcomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
