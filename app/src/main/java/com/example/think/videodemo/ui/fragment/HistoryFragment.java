package com.example.think.videodemo.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;

import java.util.zip.Inflater;

import butterknife.BindString;
import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.clear)
    Button clear;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_history,null);
        return view;
    }
}
