package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.JokeBean;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.JokeContract;
import com.example.think.videodemo.mvp.Model.JokeModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class JokePresenter extends BasePresenter<JokeContract.IView> {

    public static final String packageName = JokePresenter.class.getName();

    private JokeContract.IView iView;

    private Disposable disposable;

    public JokePresenter(JokeContract.IView iView){
        this.iView = iView;
    }

    public void getData(){

        disposable = JokeModel.getInstance().interest_store_response()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                    }
                })
                .map(new Function<JokeBean, List<JokeBean.Result.JokeItem>>() {
                    @Override
                    public List<JokeBean.Result.JokeItem> apply(JokeBean jokeBean) throws Exception {
                        return jokeBean.getResult().getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<JokeBean.Result.JokeItem>>() {
                    @Override
                    public void accept(List<JokeBean.Result.JokeItem> jokeItems) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                        iView.dismissLoad();
                        iView.showData(jokeItems);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据失败");
                        iView.dismissLoad();
                    }
                });


    }

}
