package com.example.think.videodemo.ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.think.videodemo.base.BaseFragment;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragmentList;

    private List<String> categoryList;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> categoryList) {
        super(fm);
        this.categoryList = categoryList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position);
    }
}
