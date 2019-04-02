package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.CategoryContract;
import com.example.think.videodemo.mvp.Model.CategoryModel;
import com.example.think.videodemo.mvp.Model.DetailVideoModel;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



public class CategoryPresenter extends BasePresenter<CategoryContract.IView> {

    public static final String packageName = CategoryPresenter.class.getName();

    private CategoryContract.IView iView;

    private Disposable disposable;

    public CategoryPresenter( CategoryContract.IView iView){
        this.iView = iView;
    }

    public void getData(){

        disposable = CategoryModel.getInstance().category_response()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoad();
                        LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                    }
                })
                .map(new Function<List<CategoryItem>, List<CategoryItem>>() {
                    @Override
                    public List<CategoryItem> apply(List<CategoryItem> categoryBean) throws Exception {
                        return categoryBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CategoryItem>>() {
                    @Override
                    public void accept(List<CategoryItem> categories) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                        iView.dismissLoad();
                        iView.showData(categories);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.loging(packageName,1,packageName + "  获取数据失败");
                        iView.dismissLoad();
                        iView.showError();
                    }
                });
    }

    public void disposeThis(){
        disposable.dispose();
    }

}
