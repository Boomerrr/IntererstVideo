package com.example.think.videodemo.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.MvpActivity;
import com.example.think.videodemo.mvp.Contract.DetailVideoContract;
import com.example.think.videodemo.mvp.Presenter.DetailVideoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVideoActivity extends MvpActivity implements DetailVideoContract.IView {

    private DetailVideoPresenter presenter;

    @BindView(R.id.textview)
    TextView textView;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        ButterKnife.bind(this);
        presenter = new DetailVideoPresenter(this);
        presenter.getData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Boomerr---test", "6---");
            }
        });
    }

    @Override
    protected DetailVideoPresenter createPresenter() {
        return new DetailVideoPresenter(this);
    }


    @Override
    public void showData(final List<TestBean.StoriesBean> dataList) {
        Log.d("Boomerr---test", "4---" + dataList.get(1).getId());
        textView.setText(String.valueOf(dataList.get(1).getId()));
        Log.d("Boomerr---test", "5---" + dataList.get(1).getId());
    }

    @Override
    public void dismissLoad() {
        hideLoading();
    }

    @Override
    public void showLoad() {
        showLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }
}
