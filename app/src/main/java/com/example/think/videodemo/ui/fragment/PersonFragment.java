package com.example.think.videodemo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.ui.activity.MoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonFragment extends BaseFragment {

    private Unbinder unbinder;

    //九个选项卡
    @BindView(R.id.item_1)
    LinearLayout tab_item_1;

    @BindView(R.id.item_2)
    LinearLayout tab_item_2;

    @BindView(R.id.item_3)
    LinearLayout tab_item_3;

    @BindView(R.id.item_4)
    LinearLayout tab_item_4;

    @BindView(R.id.item_5)
    LinearLayout tab_item_5;





    @BindView(R.id.item_text_1)
    TextView item_text_1;

    @BindView(R.id.item_text_2)
    TextView item_text_2;

    @BindView(R.id.item_text_3)
    TextView item_text_3;

    @BindView(R.id.item_text_4)
    TextView item_text_4;

    @BindView(R.id.item_text_5)
    TextView item_text_5;


    @BindView(R.id.item_image_1)
    ImageView item_image_1;

    @BindView(R.id.item_image_2)
    ImageView item_image_2;

    @BindView(R.id.item_image_3)
    ImageView item_image_3;

    @BindView(R.id.item_image_4)
    ImageView item_image_4;

    @BindView(R.id.item_image_5)
    ImageView item_image_5;


    //头像
    @BindView(R.id.user_head)
    CircleImageView user_head;

    //昵称
    @BindView(R.id.user_name)
    TextView user_name;

    //详情
    @BindView(R.id.more)
    TextView more;


    //



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_person, null);

        unbinder = ButterKnife.bind(this,view);

        //初始化选项卡内容
        initTabItem();

        //点击事件
        clickAction();

        return view;
    }

    private void initTabItem() {
        //头像设置
        user_head.setImageResource(R.drawable.user_head);

        //昵称
        user_name.setText("Boomerrr");

        //选项卡内容设定
        item_text_1.setText("我的资讯");

        item_text_2.setText("历史记录");

        item_text_3.setText("我的收藏");

        item_text_4.setText("意见反馈");

        item_text_5.setText("关于我们");


        item_image_1.setImageResource(R.drawable.item_info);

        item_image_2.setImageResource(R.drawable.item_history);

        item_image_3.setImageResource(R.drawable.item_collect);

        item_image_4.setImageResource(R.drawable.item_advice);

        item_image_5.setImageResource(R.drawable.item_about_us);

    }



    //默认选项卡有9项   没有内容的以空白存在  每个对应点击方法
    private void clickAction() {


        //五个选项卡的点击事件
        tab_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_1_Function();
            }
        });

        tab_item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_2_Function();
            }
        });

        tab_item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_3_Function();
            }
        });

        tab_item_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_4_Function();
            }
        });

        tab_item_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_5_Function();
            }
        });




        //头像的点击事件   可以更换本地图片更换头像
        user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_head_change_Function();
            }
        });


        //更多详情点击事件
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreFunction();
            }
        });

    }

    //更换头像逻辑
    private void user_head_change_Function() {

    }

    //更多内容
    private void moreFunction() {

    }

    private void item_1_Function() {
        toast("你好");
    }

    private void item_2_Function() {

    }

    private void item_3_Function() {

    }

    private void item_4_Function() {

    }

    private void item_5_Function() {
        startActivity(new Intent(getActivity(), MoreActivity.class));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
