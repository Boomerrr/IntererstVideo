package com.example.think.videodemo;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.think.videodemo.Util.Database.MyDatabaseHelper;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.Util.net.NetMonitService;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.ui.fragment.discovery.DiscoveryFragment;
import com.example.think.videodemo.ui.fragment.main.MainFragment;
import com.example.think.videodemo.ui.fragment.PersonFragment;
import com.example.think.videodemo.ui.fragment.Rank.RankFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 *  Created by Boomerr 19/2/26
 *
 *
 * */

public class MainActivity extends FragmentActivity {

    @BindView(R.id.main_rd)
    RadioGroup radioGroup;

    private NetMonitService iService;

    private NetMonitService.MyBinder iBinder;

    private List<BaseFragment> fragmentList;

    private int position;

    private Fragment mContext;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent bindIntent = new Intent(MainActivity.this, NetMonitService.class);
        bindService(bindIntent, connection, Context.BIND_AUTO_CREATE);
        initBroadCast();
        initFragment();
        setListener();
    }

    private void initBroadCast() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("net.is.ok");
        intentFilter.addAction("net.isnot.ok");
        registerReceiver(mReceiver,intentFilter);

    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        radioGroup.check(R.id.rb_main);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new RankFragment());
        fragmentList.add(new DiscoveryFragment());
        fragmentList.add(new PersonFragment());
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_main:
                    position = 0;
                    break;
                case R.id.rb_rank:
                    position = 1;
                    break;
                case R.id.rb_discovery:
                    position = 2;
                    break;
                case R.id.rb_person:
                    position = 3;
                    break;
                default:
                    break;
            }
            BaseFragment to = getFragment();
            switchFragment(mContext,to);
        }
    }

    private BaseFragment getFragment() {
        BaseFragment fragment = fragmentList.get(position);
        return fragment;
    }

    public void switchFragment(Fragment from, Fragment to){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(from != to){
            mContext = to;
            if(! to.isAdded()){
                if(from != null){
                    ft.hide(from);
                }
                if(to != null){
                    ft.add(R.id.main_framlayout,to).commit();
                }
            }else{
                if(from != null){
                    ft.hide(from);
                }
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService = ((NetMonitService.MyBinder)service).getService();
            iBinder = (NetMonitService.MyBinder)service;
            iBinder.getState();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iService = null;
        }
    };


    protected final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if("net.isnot.ok".equals(action)){
                Toast.makeText(MainActivity.this,"无网络",Toast.LENGTH_SHORT).show();
            }else if("net.is.ok".equals(action)){
                //Toast.makeText(MainActivity.this,"网络连接",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        unregisterReceiver(mReceiver);
    }
}
