package com.example.think.videodemo.ui.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.think.videodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 *
 * 关于我们的信息
 *
 * 加载网页
 *
 * */
public class MoreActivity extends Activity {


    @BindView(R.id.more_web_view)
    WebView more_Web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        more_Web_view.loadUrl("http://nmid.cqupt.edu.cn.cqu.pt/");

    }
}
