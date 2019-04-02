package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.Util.LogUtil;
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

    public static final String packageName = CategoryPresenter.class.getName();

    private CategoryActiContract.IView iView;

    private Disposable disposable;

    private String query;

    public CategoryActiPresenter(CategoryActiContract.IView iView,String query){
        this.iView = iView;
        this.query = query;
    }

    public void getData(){

        disposable = CategoryActiModel.getInstance().search_response(query)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "开始访问数据");
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
                        LogUtil.loging(packageName,1,packageName +" 获取数据成功");
                        iView.dismissLoad();
                        iView.showData(detailVideos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.loging(packageName,1,packageName +" 获取数据失败");
                        iView.dismissLoad();
                        iView.showError();
                    }
                });

    }

    public void disposeThis(){
        disposable.dispose();
    }

}
