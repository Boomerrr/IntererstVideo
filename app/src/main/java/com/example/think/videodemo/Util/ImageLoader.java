package com.example.think.videodemo.Util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

//图片加载

public class ImageLoader {

    public ImageLoader(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
