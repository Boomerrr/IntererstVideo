package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.CategoryActiContract;
import com.example.think.videodemo.mvp.Model.CategoryActiModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CategoryActiPresenter extends BasePresenter<CategoryActiContract.IView> {

    private CategoryActiContract.IView iView;

    private String query;

    public CategoryActiPresenter(CategoryActiContract.IView iView,String query){
        this.iView = iView;
        this.query = query;
    }

    public void getData(){

        CategoryActiModel.getInstance().search_response(query)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        Log.d("Boomerr---test","+1");
                    }
                })
                .map(new Function<RankBean, List<RankBean.DetailRank>>() {
                    @Override
                    public List<RankBean.DetailRank> apply(RankBean rankBean) throws Exception {
                        Log.d("Boomerr---test","+2");
                        return rankBean.getItemList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RankBean.DetailRank>>() {
                    @Override
                    public void accept(List<RankBean.DetailRank> detailVideos) throws Exception {
                        Log.d("Boomerr---test","+3   " + detailVideos.size() );
                        iView.dismissLoad();
                        iView.showData(detailVideos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Boomerr---test","+4");
                        iView.dismissLoad();
                    }
                });

    }

}
