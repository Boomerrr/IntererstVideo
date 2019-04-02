package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.Util.LogUtil;
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

    public static final String packageName = HistoricalRankPresenter.class.getName();

    private RankContract.HistIView iView;

    private Disposable disposable;

    public HistoricalRankPresenter(RankContract.HistIView iView){
        this.iView = iView;
    }

    public void getData(){

        disposable = HistorcialRankVideoModel.getInstance().rank_historical_response()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                    }
                })
                .map(new Function<RankBean, List<RankBean.DetailRank>>() {
                    @Override
                    public List<RankBean.DetailRank> apply(RankBean rankBean) throws Exception {
                        return rankBean.getItemList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RankBean.DetailRank>>() {
                    @Override
                    public void accept(List<RankBean.DetailRank> detailVideos) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                        iView.dismissLoad();
                        iView.showData(detailVideos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  数取数据失败");
                        iView.dismissLoad();
                    }
                });
    }

    public void disposeThis(){
        disposable.dispose();
    }

}
