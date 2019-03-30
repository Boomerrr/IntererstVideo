package com.example.think.videodemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TestActivity extends AppCompatActivity {

   @BindView(R.id.videoplayer)
    JCVideoPlayerStandard jcVideoPlayerStandard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
                jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"test");

    }

    @Override
    protected void onPause() {
        super.onPause();
        jcVideoPlayerStandard.release();
    }
}
