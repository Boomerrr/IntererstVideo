package com.example.think.videodemo.ui.fragment.Rank;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.example.think.videodemo.ui.Adapter.FragmentAdapter;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RankFragment extends BaseFragment {

    private Unbinder unbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_rank, null);
        unbinder = ButterKnife.bind(this,view);
        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
        List<String> categoryList = new ArrayList<>();

        categoryList.add("周排行");
        categoryList.add("月排行");
        categoryList.add("历史排行");

        for (int i = 0; i < categoryList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(categoryList.get(i)));
        }

        List<BaseFragment> fragmentList = new ArrayList<>();

        fragmentList.add(new WeekRankFragment());
        fragmentList.add(new MonthRankFragment());
        fragmentList.add(new HistoricalRankFragment());

        Log.d("Boomerr---test","Loading...");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, categoryList);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


}
