package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.RankContract;
import com.example.think.videodemo.mvp.Model.HistorcialRankVideoModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HistoricalRankPresenter extends BasePresenter<RankContract.HistIView> {

    private RankContract.HistIView iView;

    public HistoricalRankPresenter(RankContract.HistIView iView){
        this.iView = iView;
    }

    public void getData(){

        HistorcialRankVideoModel.getInstance().rank_historical_response()
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
