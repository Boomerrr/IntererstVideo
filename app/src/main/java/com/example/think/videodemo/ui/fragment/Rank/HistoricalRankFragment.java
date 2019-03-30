package com.example.think.videodemo.ui.fragment.Rank;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.RankContract;
import com.example.think.videodemo.mvp.Presenter.HistoricalRankPresenter;
import com.example.think.videodemo.ui.Adapter.RankVideoAdapter;
import com.example.think.videodemo.ui.activity.VideoInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoricalRankFragment extends BaseFragment implements RankContract.HistIView,RankVideoAdapter.OnItemClickListener {

    private Unbinder unbinder;

    private HistoricalRankPresenter historicalRankPresenter;

   @BindView(R.id.his_rank_recyclerview)
    RecyclerView his_rank_recyclerview;

   @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

   private List<RankBean.DetailRank> dataList;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_rank_historical,null);
        historicalRankPresenter = new HistoricalRankPresenter(this);
        unbinder = ButterKnife.bind(this,view);
        //textView.setText("day");
        historicalRankPresenter.getData();
        Log.d("Boomerr---test","initView");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                historicalRankPresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        historicalRankPresenter = null;
        Log.d("Boomerr---test","onDestroyView");
    }

    @Override
    public void showData(List<RankBean.DetailRank> detailRanks) {
        this.dataList = detailRanks;
        RankVideoAdapter rankVideoAdapter = new RankVideoAdapter(mContext,detailRanks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rankVideoAdapter.setItemClickListener(this);
        his_rank_recyclerview.setLayoutManager(layoutManager);
        his_rank_recyclerview.setAdapter(rankVideoAdapter);
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
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), VideoInfoActivity.class);
        intent.putExtra("title",dataList.get(position).getDate().getTitle());
        intent.putExtra("description",dataList.get(position).getDate().getDescription());
        intent.putExtra("playUrl",dataList.get(position).getDate().getPlayUrl());
        intent.putExtra("category",dataList.get(position).getDate().getCategory());
        intent.putExtra("author",dataList.get(position).getDate().getAuthor().getIcon());
        intent.putExtra("feed",dataList.get(position).getDate().getCover().getFeed());
        intent.putExtra("userName",dataList.get(position).getDate().getAuthor().getName());
        intent.putExtra("userDescription",dataList.get(position).getDate().getAuthor().getDescription());
        intent.putExtra("blurred",dataList.get(position).getDate().getCover().getBlurred());
        startActivity(intent);
    }
}
