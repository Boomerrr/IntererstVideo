package com.example.think.videodemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Presenter.MainVideoAdversePresenter;
import com.example.think.videodemo.mvp.Presenter.TestPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TestActivity extends AppCompatActivity implements MainVideoContract.IView,MainVideoContract.AdvView{

    @BindView(R.id.button1)
    Button button1;

    @BindView(R.id.button2)
    Button button2;

    TestPresenter testPresenter = new TestPresenter(this);
    MainVideoAdversePresenter mainVideoAdversePresenter = new MainVideoAdversePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPresenter.getMainData();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testPresenter.dispose();
                mainVideoAdversePresenter.getData();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showMainData(List<MainVideoBean.DetailVideo> detailVideos) {
        Toast.makeText(this,detailVideos.get(2).getData().getTitle(),Toast.LENGTH_SHORT).show();
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
    public void showData(List<HotVideoBean.HotABean.itemBean> hotABeanList) {
        Toast.makeText(this,hotABeanList.get(2).getData().getTitle(),Toast.LENGTH_SHORT).show();
    }
}
