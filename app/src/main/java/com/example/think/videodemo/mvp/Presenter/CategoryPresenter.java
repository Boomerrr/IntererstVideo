package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.Bean.TestBean;
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
                        Log.d("Boomerr---test","1");
                    }
                })
                .map(new Function<List<CategoryItem>, List<CategoryItem>>() {
                    @Override
                    public List<CategoryItem> apply(List<CategoryItem> categoryBean) throws Exception {
                        Log.d("Boomerr---test","2");
                        return categoryBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CategoryItem>>() {
                    @Override
                    public void accept(List<CategoryItem> categories) throws Exception {
                        Log.d("Boomerr---test","3 " + categories.get(1).getName());
                        iView.dismissLoad();
                        iView.showData(categories);
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
