package com.example.think.videodemo.ui.activity;

import android.app.Activity;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private View view1,view2,view3,view4;

    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {

        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.splash_view1, null);
        view2 = inflater.inflate(R.layout.splash_view2,null);
        view3 = inflater.inflate(R.layout.splash_view3, null);
        view4 = inflater.inflate(R.layout.splash_view4, null);

        ImageView splash_image1 = view1.findViewById(R.id.splash_image1);
        ImageView splash_image2 = view2.findViewById(R.id.splash_image2);
        ImageView splash_image3 = view3.findViewById(R.id.splash_image3);
        ImageView splash_image4 = view4.findViewById(R.id.splash_image4);
        Button enter = view4.findViewById(R.id.enter);
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
    }
}
