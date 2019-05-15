package com.example.think.videodemo.ui.fragment.main;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Presenter.MainVideoAdversePresenter;
import com.example.think.videodemo.ui.Adapter.AdvertiseBroadAdapter;
import com.example.think.videodemo.ui.activity.VideoInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdvertiseFragment extends BaseFragment implements MainVideoContract.AdvView,AdvertiseBroadAdapter.OnItemClickListener1{

    private Unbinder unbinder;


    private MainVideoAdversePresenter mainVideoAdversePresenter;

    private AdvertiseBroadAdapter advertiseBroadAdapter;

    private List<HotVideoBean.HotABean.itemBean> hotABeanList;

    int index = 0;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_advertise, null);

        unbinder = ButterKnife.bind(this,view);

        mainVideoAdversePresenter = new MainVideoAdversePresenter(this);

        mainVideoAdversePresenter.getData();

        Log.d("Boomerr---tets","advertiseFragment is live");

        return view;
    }

    @Override
    public void showData(List<HotVideoBean.HotABean.itemBean> hotABeanList) {


    }

    private void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //如果activity未销毁则一直执行
                while (true) {
                    //先休息5秒钟
                    SystemClock.sleep(5000);
                    //以下代码发送到主线程中执行
                    if(getActivity()!=null){
                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                //recyclerView.smoothScrollToPosition(index % advertiseBroadAdapter.getItemCount());
                                index++;
                            }
                        });
                    }else{
                        break;
                    }
                }
            }
        }).start();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mainVideoAdversePresenter = null;
    }

    //@Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), VideoInfoActivity.class);
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

    @Override
    public void onItemClick1(int position) {

    }
}
