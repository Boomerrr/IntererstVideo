package com.example.think.videodemo.ui.fragment.discovery;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.think.videodemo.Bean.JokeBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.JokeContract;
import com.example.think.videodemo.mvp.Presenter.JokePresenter;
import com.example.think.videodemo.ui.Adapter.JokeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JokeFragment extends BaseFragment implements JokeContract.IView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;

    private JokePresenter jokePresenter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_joke,null);
        unbinder = ButterKnife.bind(this,view);
        jokePresenter = new JokePresenter(this);
        jokePresenter.getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jokePresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    public void showData(List<JokeBean.Result.JokeItem> dataList) {
        JokeAdapter jokeAdapter = new JokeAdapter(getActivity(),dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jokeAdapter);
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
        jokePresenter = null;
    }
}
