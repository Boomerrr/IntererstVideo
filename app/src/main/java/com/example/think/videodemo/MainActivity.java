package com.example.think.videodemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;


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

    private List<BaseFragment> fragmentList;

    private int position;

    private Fragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        setListener();
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

}