package com.example.think.videodemo.mvp.Presenter;


import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.Util.LogUtil;
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

    public static final String packageName = DetailVideoPresenter.class.getName();

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
                                LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                            }
                        })
                        .map(new Function<TestBean, List<TestBean.StoriesBean>>() {
                            @Override
                            public List<TestBean.StoriesBean> apply(TestBean hotBean) throws Exception {
                                return hotBean.getStories();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<TestBean.StoriesBean>>() {
                            @Override
                            public void accept(List<TestBean.StoriesBean> storiesBeans) throws Exception {
                                LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                                iView.dismissLoad();
                                iView.showData(storiesBeans);
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
