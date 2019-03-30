package com.example.think.videodemo.ui.fragment.discovery;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.think.videodemo.Bean.FunnyImageBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.FunnyImageContract;
import com.example.think.videodemo.mvp.Presenter.FunnyImagePresenter;
import com.example.think.videodemo.ui.Adapter.FunnyImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FunnyPicFragment extends BaseFragment implements FunnyImageContract.IView{


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    FunnyImagePresenter funnyImagePresenter;

    private Unbinder unbinder;


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_funnypic,null);
        unbinder = ButterKnife.bind(this,view);
        funnyImagePresenter = new FunnyImagePresenter(this);
        funnyImagePresenter.getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                funnyImagePresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    public void showData(List<FunnyImageBean.Result.FunnyImageItem> dataList) {
        FunnyImageAdapter funnyImageAdapter = new FunnyImageAdapter(getActivity(),dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(funnyImageAdapter);
    }

    @Override
    public void showLoad() {
        showLoading();
    }

    @Override
    public void dismissLoad() {
        hideLoading();
    }

    @Override
    public void showError() {
        toast("出错了");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        funnyImagePresenter = null;
    }
}
