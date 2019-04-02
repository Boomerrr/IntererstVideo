package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.FunnyImageBean;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.FunnyImageContract;
import com.example.think.videodemo.mvp.Model.FunnyImageModel;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class FunnyImagePresenter extends BasePresenter<FunnyImageContract.IView> {

    public static final String packageName = FunnyImagePresenter.class.getName();

    private FunnyImageContract.IView iView;

    private Disposable disposable;

    public FunnyImagePresenter(FunnyImageContract.IView iView){
        this.iView = iView;
    }

    public void getData() {

        disposable = FunnyImageModel.getInstance().interest_image_response()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                    }
                })
                .map(new Function<FunnyImageBean, List<FunnyImageBean.Result.FunnyImageItem>>() {
                    @Override
                    public List<FunnyImageBean.Result.FunnyImageItem> apply(FunnyImageBean funnyImageBean) throws Exception {
                        return funnyImageBean.getResult().getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FunnyImageBean.Result.FunnyImageItem>>() {
                    @Override
                    public void accept(List<FunnyImageBean.Result.FunnyImageItem> funnyImageItemList) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                        iView.dismissLoad();
                        iView.showData(funnyImageItemList);
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
