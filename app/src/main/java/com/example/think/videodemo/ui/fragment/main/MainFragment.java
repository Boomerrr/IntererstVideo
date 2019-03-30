package com.example.think.videodemo.ui.fragment.main;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Presenter.MainVideoPresenter;
import com.example.think.videodemo.ui.Adapter.MainVideoAdapter;
import com.example.think.videodemo.ui.activity.CategoryActivity;
import com.example.think.videodemo.ui.activity.VideoInfoActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment implements MainVideoContract.IView,MainVideoAdapter.OnItemClickListener{

    private Unbinder unbinder;

    @BindView(R.id.search)
    ImageView search;


    @BindView(R.id.mainVideo_recycelerview)
    RecyclerView mainVideo_recyclerview;

    @BindView(R.id.adv_container)
    FrameLayout adv_container;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private View view;

    private MainVideoPresenter mainVideoPresenter;

    private List<MainVideoBean.DetailVideo> dataList;

    @Override
    protected View initView() {
        view = View.inflate(mContext, R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this,view);
        initViewFunction(view);
        return view;
    }

    private void initViewFunction(View view) {
        mainVideoPresenter = new MainVideoPresenter(this);
        mainVideoPresenter.getMainData();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPopupWindow();
            }
        });
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        AdvertiseFragment advertiseFragment = new AdvertiseFragment();
        if(! advertiseFragment.isAdded()){
            ft.add(R.id.adv_container,new AdvertiseFragment());
            ft.show(advertiseFragment);
        }else{
            ft.show(advertiseFragment);
        }
        ft.commit();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainVideoPresenter.getMainData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void searchPopupWindow() {

        View view = View.inflate(getActivity(), R.layout.popupwindow_search, null);
        //此处可按需求为各控件设置属性

        final PopupWindow popupWindow = new PopupWindow(view);
        //设置弹出窗口大小
        popupWindow.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        popupWindow.setFocusable(true);
        setBackgroundAlpha(0.5f);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

        Button search=(Button)view.findViewById(R.id.search);
        Button cancel=(Button)view.findViewById(R.id.cancel);
        final EditText editText=(EditText)view.findViewById(R.id.edit_query) ;
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.cancel:
                        popupWindow.dismiss();
                        break;
                    case R.id.search:
                        //搜索逻辑 跳转到关键字详情界面
                        popupWindow.dismiss();
                        if( ! editText.getText().toString() .equals("") ){
                            Intent intent = new Intent(getActivity(), CategoryActivity.class);
                            intent.putExtra("query",editText.getText().toString());
                            startActivity(intent);
                        }else{
                            popupWindow.dismiss();
                            toast("无输入");
                        }
                        break;
                    default:
                        break;
                }
            }};
        cancel.setOnClickListener(listener);
        search.setOnClickListener(listener);
        //设置动画效果
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);

    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
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

    public void showMainData(List<MainVideoBean.DetailVideo> detailVideos){
        this.dataList = detailVideos;
        MainVideoAdapter mainVideoAdapter = new MainVideoAdapter(mContext,dataList);
        Log.d("Boomerr---test","test fees    " + detailVideos.get(10).getData().getCover().getFeed());
        Log.d("Boomerr---test","test fees    " + detailVideos.get(10).getData().getDescription());
        Log.d("Boomerr---test","test fees    " + detailVideos.get(10).getData().getPlayUrl());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainVideoAdapter.setItemClickListener(this);
        mainVideo_recyclerview.setLayoutManager(layoutManager);
        mainVideo_recyclerview.setAdapter(mainVideoAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mainVideoPresenter = null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), VideoInfoActivity.class);
        intent.putExtra("title",dataList.get(position).getData().getTitle());
        intent.putExtra("description",dataList.get(position).getData().getDescription());
        intent.putExtra("playUrl",dataList.get(position).getData().getPlayUrl());
        intent.putExtra("category",dataList.get(position).getData().getCategory());
        intent.putExtra("author",dataList.get(position).getData().getAuthor().getIcon());
        intent.putExtra("feed",dataList.get(position).getData().getCover().getFeed());
        intent.putExtra("userName",dataList.get(position).getData().getAuthor().getName());
        intent.putExtra("userDescription",dataList.get(position).getData().getAuthor().getDescription());
        intent.putExtra("blurred",dataList.get(position).getData().getCover().getBlurred());
        startActivity(intent);
    }
}
