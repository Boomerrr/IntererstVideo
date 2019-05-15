package com.example.think.videodemo.ui.fragment.main;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.MainActivity;
import com.example.think.videodemo.R;
import com.example.think.videodemo.Util.Database.MyDatabaseHelper;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Presenter.MainVideoAdversePresenter;
import com.example.think.videodemo.mvp.Presenter.MainVideoPresenter;
import com.example.think.videodemo.ui.Adapter.AdvertiseBroadAdapter;
import com.example.think.videodemo.ui.Adapter.MainVideoAdapter;
import com.example.think.videodemo.ui.activity.CategoryActivity;
import com.example.think.videodemo.ui.activity.VideoInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  Boomerr 2019/3/20
 *  email yi.bochen@foxmail.com
 *  主页内容
 *  由一块广告牌和一个recyclerview热门视频构成
 * */

public class MainFragment extends BaseFragment implements
        MainVideoContract.IView,MainVideoContract.AdvView,MainVideoAdapter.OnItemClickListener{

    private Unbinder unbinder;

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.history)
    ImageView history;

    @BindView(R.id.mainVideo_recycelerview)
    RecyclerView mainVideo_recyclerview;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.mShowPointer)
    LinearLayout mShowPointer;

    @BindView(R.id.image_description)
    TextView mImageDescription;

    public static final String packageName = MainFragment.class.getName();

    private View view;

    private MainVideoPresenter mainVideoPresenter;

    private List<MainVideoBean.DetailVideo> dataList;

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase sqLiteDatabase;

    private MainVideoAdversePresenter mainVideoAdversePresenter;

    private List<HotVideoBean.HotABean.itemBean> hotABeanList;

    private AdvertiseBroadAdapter advertiseBroadAdapter;

    public static final int ADV_INIT = 1;

    private ArrayList<ImageView> imageViewlist;

    private ArrayList<String> descriptionList;

    private Handler handler = new Handler();

    private int previousPoint = 0;

    @Override
    protected View initView() {
        view = View.inflate(mContext, R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this,view);
        initViewFunction(view);
        initListeners(view);
        return view;
    }

    //初始化点击事件
    private void initListeners(View view) {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPopupWindow();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity workActivity = (MainActivity) getActivity();
                DrawerLayout drawerLayout = workActivity.findViewById(R.id.main_drawerlayout);
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainVideoPresenter.getMainData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //初始化
    private void initViewFunction(View view) {
        mainVideoPresenter = new MainVideoPresenter(this);
        mainVideoAdversePresenter = new MainVideoAdversePresenter(this);
        mainVideoPresenter.getMainData();
        dbHelper = new MyDatabaseHelper(getActivity(),"history.db",null,1);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    //搜索弹窗
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

    //设置品目透明度
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

    //主页热门视频
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
        handler.post(adv_init);
    }

    Runnable adv_init = new Runnable() {
        @Override
        public void run() {
            mainVideoAdversePresenter.getData();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mainVideoAdversePresenter.disposeThis();
        mainVideoPresenter.disposeThis();
        mainVideoPresenter = null;
        mainVideoAdversePresenter = null;
    }

    //热门视频点击事件
    @Override
    public void onItemClick(int position) {
        ContentValues values = new ContentValues();
        values.put("title",dataList.get(position).getData().getTitle());
        values.put("description",dataList.get(position).getData().getDescription());
        values.put("playUrl",dataList.get(position).getData().getPlayUrl());
        values.put("category",dataList.get(position).getData().getCategory());
        values.put("author",dataList.get(position).getData().getAuthor().getIcon());
        values.put("feed",dataList.get(position).getData().getCover().getFeed());
        values.put("userName",dataList.get(position).getData().getAuthor().getName());
        values.put("userDescription",dataList.get(position).getData().getAuthor().getDescription());
        values.put("blurred",dataList.get(position).getData().getCover().getBlurred());
        sqLiteDatabase.insert("VIDEO_HISTORY",null,values);

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

    //广告牌视频
    @Override
    public void showData(List<HotVideoBean.HotABean.itemBean> hotABeanList) {
        this.hotABeanList = hotABeanList;
        LogUtil.loging(packageName,1,hotABeanList.get(1).getData().getDescription());
        Log.d("Boomerr---test","Advertise-----------" + hotABeanList.size());
        imageViewlist = new ArrayList<>();
        descriptionList = new ArrayList<>();
        ImageView iv;
        // 存放小点的布局文件
        // 遍历图片id
        for (int i = 1; i < hotABeanList.size(); i++) {
            // 构造新的图片对象，并根据id给图片设置背景
            iv = new ImageView(viewPager.getContext());
            ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
            iv.setScaleType(scaleType);
            Glide.with(mContext).load(hotABeanList.get(i).getData().getCover().getFeed())
                    .into(iv);
            Log.d("Boomerr---test广告牌构建",hotABeanList.get(i).getData().getCover().getFeed());
            // 所有的图片存放在imageViewlist中
            imageViewlist.add(iv);
            descriptionList.add(hotABeanList.get(i).getData().getTitle());
            // 构造小点
            View v = new View(mContext);
            // 设置小点的宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(12, 12);
            // 设置小点的左边距
            params.leftMargin = 12;
            v.setLayoutParams(params);
            // 设置小点是否可用，默认都不可用，当不可用时，小点是透明的，否则是白色的
            v.setEnabled(false);
            // 设置小点的背景，这个背景是使用xml文件画的一个小圆点
            v.setBackgroundResource(R.drawable.pointer_selector);
            // 把小点添加到它的布局文件中
            mShowPointer.addView(v);
        }
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(1);
        adv_runnable();
    }

    //广告牌轮播任务
    private void adv_runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //休息7秒钟
                    SystemClock.sleep(7000);
                    if(mContext != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(viewPager
                                        .getCurrentItem() + 1);
                            }
                        });
                    }else{
                        break;
                    }
                }
            }
        }).start();
    }

    //广告牌viewpager的适配器
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewlist.get(position
                    % imageViewlist.size()));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageViewlist.get(position % imageViewlist.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick1(viewPager.getCurrentItem());
                }
            });
            container.addView(view);
            return view;
        }

    }

    //广告牌的滑动监听器
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        // 开始
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        // 正在进行时
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        // 结束
        @Override
        public void onPageSelected(int position) {
            position = position % imageViewlist.size();
            mImageDescription.setText(descriptionList.get(position));
            mShowPointer.getChildAt(previousPoint).setEnabled(false);
            mShowPointer.getChildAt(position).setEnabled(true);
            previousPoint = position;
        }
    }

    //广告牌点击事件
    public void onItemClick1(int position) {
        position++;
        Intent intent = new Intent(mContext, VideoInfoActivity.class);
        intent.putExtra("title",hotABeanList.get(position).getData().getTitle());
        intent.putExtra("description",hotABeanList.get(position).getData().getDescription());
        intent.putExtra("playUrl",hotABeanList.get(position).getData().getPlayUrl());
        intent.putExtra("category",hotABeanList.get(position).getData().getCategory());
        intent.putExtra("author",hotABeanList.get(position).getData().getAuthor().getIcon());
        intent.putExtra("feed",hotABeanList.get(position).getData().getCover().getFeed());
        intent.putExtra("userName",hotABeanList.get(position).getData().getAuthor().getName());
        intent.putExtra("userDescription",hotABeanList.get(position).getData().getAuthor().getDescription());
        intent.putExtra("blurred",hotABeanList.get(position).getData().getCover().getBlurred());
        startActivity(intent);
    }

}
