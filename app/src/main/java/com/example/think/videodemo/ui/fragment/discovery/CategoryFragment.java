package com.example.think.videodemo.ui.fragment.discovery;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.mvp.Contract.CategoryContract;
import com.example.think.videodemo.mvp.Presenter.CategoryPresenter;
import com.example.think.videodemo.ui.Adapter.CategoryAdapter;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.ui.activity.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends BaseFragment implements CategoryContract.IView,CategoryAdapter.OnItemClickListener{

    private Unbinder unbinder;

    @BindView(R.id.recycelerview)
    RecyclerView recyclerView;

    private CategoryPresenter categoryPresenter;

    private List<CategoryItem> dataList;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_category,null);
        unbinder = ButterKnife.bind(this,view);
        categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.getData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        categoryPresenter = null;
    }

    @Override
    public void showData(List<CategoryItem> dataList) {
        this.dataList = dataList;
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter mainNewAdapter=new CategoryAdapter(dataList);
        mainNewAdapter.setItemClickListener(this);
        recyclerView.setAdapter(mainNewAdapter);
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
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra("query",dataList.get(position).getName());
        startActivity(intent);
        Log.d("Boomerr---test","category clicked");
        Log.d("Boomerr---test",dataList.get(position).getName());
    }
}
