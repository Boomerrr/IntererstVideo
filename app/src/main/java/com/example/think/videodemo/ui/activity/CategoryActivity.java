package com.example.think.videodemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.base.MvpActivity;
import com.example.think.videodemo.mvp.Contract.CategoryActiContract;
import com.example.think.videodemo.mvp.Presenter.CategoryActiPresenter;
import com.example.think.videodemo.ui.Adapter.CategoryActiAdapter;
import com.example.think.videodemo.ui.Adapter.RankVideoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CategoryActiContract.IView,CategoryActiAdapter.OnItemClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private String category;

    private CategoryActiPresenter categoryActiPresenter;

    private List<RankBean.DetailRank> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initActivity();
        Log.d("Boomerr---test", "CategoryActivity start");
    }


    private void initActivity() {

        Intent intent = getIntent();
        category = intent.getStringExtra("query");
        Log.d("Boomerr---test",category);
        categoryActiPresenter = new CategoryActiPresenter(this,category);
        categoryActiPresenter.getData();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(category);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryActiPresenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void showData(List<RankBean.DetailRank> detailRanks) {
        this.dataList = detailRanks;
        CategoryActiAdapter adapter = new CategoryActiAdapter(this,detailRanks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter.setItemClickListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void dismissLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryActiPresenter = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, VideoInfoActivity.class);
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
