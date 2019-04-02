package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Model.MainVideoAdverseModel;
import com.example.think.videodemo.mvp.Model.MainVideoModel;

import java.util.List;
import java.util.Observable;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainVideoPresenter extends BasePresenter<MainVideoContract.IView> {

    public static final String packageName = MainVideoPresenter.class.getCanonicalName();

    private MainVideoContract.IView iView;

    private Disposable disposable;

    public MainVideoPresenter(MainVideoContract.IView iView){
        this.iView = iView;
    }

    public void getMainData(){


        disposable = MainVideoModel.getInstance().main_response().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                    }
                })
                .map(new Function<MainVideoBean, List<MainVideoBean.DetailVideo>>() {
                    @Override
                    public List<MainVideoBean.DetailVideo> apply(MainVideoBean mainVideoBean) throws Exception {
                        return mainVideoBean.getItemList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MainVideoBean.DetailVideo>>() {
                    @Override
                    public void accept(List<MainVideoBean.DetailVideo> detailVideos) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                        iView.dismissLoad();
                        iView.showMainData(detailVideos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据失败");
                        iView.dismissLoad();
                    }
                });


    }

    public void disposeThis(){
        disposable.dispose();
    }
}
