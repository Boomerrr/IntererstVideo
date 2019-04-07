package com.example.think.videodemo.ui.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.think.videodemo.Bean.HistoryBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.Util.Database.MyDatabaseHelper;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.ui.Adapter.HistoryAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends BaseFragment {

    public static final String packageName = HistoryFragment.class.getName();

    private Unbinder unbinder;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.clear)
    Button clear;

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase sqLiteDatabase;

    private HistoryAdapter historyAdapter;

    private ArrayList<HistoryBean> historyBeans;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_history,null);
        initRecyclerview(view);
        return view;
    }

    private void initRecyclerview(View view) {

        unbinder = ButterKnife.bind(view);
        dbHelper = new MyDatabaseHelper(getActivity(), "history.db",null,1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        historyBeans = initDatabase();
        historyAdapter = new HistoryAdapter(getActivity(), historyBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                historyBeans.clear();
                historyBeans = initDatabase();
                historyAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private ArrayList<HistoryBean> initDatabase() {
        ArrayList<HistoryBean> historyBeanList;
        historyBeanList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("VIDEO_HISTORY", null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            do {
                HistoryBean historyBean = new HistoryBean();
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String playUrl = cursor.getString(cursor.getColumnIndex("playUrl"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String feed = cursor.getString(cursor.getColumnIndex("feed"));
                String userName = cursor.getString(cursor.getColumnIndex("userName"));
                String userDescription = cursor.getString(cursor.getColumnIndex("userDescription"));
                String blurred = cursor.getString(cursor.getColumnIndex("blurred"));

                historyBean.setTitle(title);
                historyBean.setDescription(description);
                historyBean.setPlay_url(playUrl);
                historyBean.setCategory(category);
                historyBean.setAuthorIcon(author);
                historyBean.setFeed(feed);
                historyBean.setUserName(userName);
                historyBean.setUserDescription(userDescription);
                historyBean.setBluerred(blurred);
                LogUtil.loging(packageName, 1, "数据库数据查询.......");
                historyBeanList.add(historyBean);

            } while (cursor.moveToNext());
        }
        Collections.reverse(historyBeanList);
        cursor.close();

        return historyBeanList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
