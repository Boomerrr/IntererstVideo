package com.example.think.videodemo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @BindView(R.id.container)
    RelativeLayout container;

    @BindView(R.id.appBar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.video_player)
    JCVideoPlayerStandard video_player;

    @BindView(R.id.video_title)
    TextView video_title;

    @BindView(R.id.video_description)
    TextView video_description;

    @BindView(R.id.video_category)
    TextView video_category;

    @BindView(R.id.imageview)
    ImageView feed_image;

    @BindView(R.id.avatar)
    CircleImageView author_avatar;

    @BindView(R.id.user_head)
    CircleImageView user_head;

    @BindView(R.id.user_name)
    TextView user_name;

    @BindView(R.id.user_description)
    TextView user_description;

    private boolean mIsTheTitleVisible = false;

    private boolean mIsTheTitleContainerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_info);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.arrow_left);

       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

       init();

    }

    private void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String playUrl = intent.getStringExtra("playUrl");
        String category = intent.getStringExtra("category");
        String feed = intent.getStringExtra("feed");
        String author = intent.getStringExtra("author");
        String userName = intent.getStringExtra("userName");
        String userDescription = intent.getStringExtra("userDescription");
        String blurred = intent.getStringExtra("blurred");

        user_name.setText(userName);
        user_description.setText(userDescription);

        Glide.with(this).load(author)
                .into(user_head);

        //author_avatar.setImageURI(Uri.parse(author));
        //feed_image.setImageURI(Uri.parse(feed));
        Glide.with(this).load(author)
                .into(author_avatar);
        Glide.with(this).load(feed)
                .into(feed_image);
        video_category.setText("#" + category);
        mAppBarLayout.addOnOffsetChangedListener(this);
        video_title.setText(title);
        video_description.setText(description);
        video_player.setUp(playUrl, video_player.SCREEN_LAYOUT_NORMAL,"");
        Glide.with(this).load(blurred)
                .into(video_player.thumbImageView);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {

        int maxScroll = appBarLayout.getTotalScrollRange();

        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);

        handleToolbarTitleVisibility(percentage);

    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleName, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleName, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(container, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(container, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        video_player.release();
    }
}
