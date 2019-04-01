package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.FunnyImageBean;
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
                        Log.d("Boomerr---test", "+1");
                    }
                })
                .map(new Function<FunnyImageBean, List<FunnyImageBean.Result.FunnyImageItem>>() {
                    @Override
                    public List<FunnyImageBean.Result.FunnyImageItem> apply(FunnyImageBean funnyImageBean) throws Exception {
                        Log.d("Boomerr---test", "+2");
                        return funnyImageBean.getResult().getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FunnyImageBean.Result.FunnyImageItem>>() {
                    @Override
                    public void accept(List<FunnyImageBean.Result.FunnyImageItem> funnyImageItemList) throws Exception {
                        Log.d("Boomerr---test", "+3   " + funnyImageItemList.size());
                        Log.d("Boomerr---test", funnyImageItemList.get(1).getContent());
                        iView.dismissLoad();
                        iView.showData(funnyImageItemList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Boomerr---test", "+4");
                        iView.dismissLoad();
                    }
                });
    }

    public void disposeThis(){
        disposable.dispose();
    }

}
