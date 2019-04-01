package com.example.think.videodemo.mvp.Presenter;


import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.mvp.Model.DetailVideoModel;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.DetailVideoContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DetailVideoPresenter extends BasePresenter<DetailVideoContract.IView> {

    private DetailVideoContract.IView iView;

    private Disposable disposable;

    public DetailVideoPresenter(DetailVideoContract.IView iView){
        this.iView = iView;
    }

    public void getData(){

        disposable = DetailVideoModel.getInstance().test()
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iView.showLoad();
                                Log.d("Boomerr---test","1");
                            }
                        })
                        .map(new Function<TestBean, List<TestBean.StoriesBean>>() {
                            @Override
                            public List<TestBean.StoriesBean> apply(TestBean hotBean) throws Exception {
                                Log.d("Boomerr---test","2");
                                return hotBean.getStories();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<TestBean.StoriesBean>>() {
                            @Override
                            public void accept(List<TestBean.StoriesBean> storiesBeans) throws Exception {
                                Log.d("Boomerr---test","3 " + storiesBeans.size());
                                iView.dismissLoad();
                                iView.showData(storiesBeans);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Boomerr---test","4");
                                iView.dismissLoad();
                            }
                        });
            }

    public void disposeThis(){
        disposable.dispose();
    }
}
