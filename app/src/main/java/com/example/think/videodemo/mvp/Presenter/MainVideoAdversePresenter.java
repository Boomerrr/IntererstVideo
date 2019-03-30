package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Contract.WelcomContract;
import com.example.think.videodemo.mvp.Model.MainVideoAdverseModel;
import com.example.think.videodemo.mvp.Model.MainVideoModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainVideoAdversePresenter extends BasePresenter<MainVideoContract.AdvView>{

    private MainVideoContract.AdvView iView;

    public MainVideoAdversePresenter(MainVideoContract.AdvView iView){
        this.iView = iView;
    }

    public void getData(){

                MainVideoAdverseModel.getInstance().hot_response()
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iView.showLoad();
                                Log.d("Boomerr---test","welcom 1");
                            }
                        })
                        .map(new Function<HotVideoBean, List<HotVideoBean.HotABean>>() {
                            @Override
                            public List<HotVideoBean.HotABean> apply(HotVideoBean hotBean) throws Exception {
                                Log.d("Boomerr---test","2");
                                return hotBean.getIssueList();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<HotVideoBean.HotABean>>() {
                            @Override
                            public void accept(List<HotVideoBean.HotABean> storiesBeans) throws Exception {
                                iView.dismissLoad();
                                iView.showData(storiesBeans.get(0).getItemList());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Boomerr---test","4");
                                iView.dismissLoad();
                            }
                        });


    }


}
