package com.example.think.videodemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicShowActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pic_show);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        Glide.with(this).load(imageUrl)
                .into(imageView);
    }

}
