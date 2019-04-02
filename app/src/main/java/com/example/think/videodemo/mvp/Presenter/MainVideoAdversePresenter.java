package com.example.think.videodemo.mvp.Presenter;

import android.util.Log;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.Util.LogUtil;
import com.example.think.videodemo.base.BasePresenter;
import com.example.think.videodemo.mvp.Contract.MainVideoContract;
import com.example.think.videodemo.mvp.Contract.WelcomContract;
import com.example.think.videodemo.mvp.Model.MainVideoAdverseModel;
import com.example.think.videodemo.mvp.Model.MainVideoModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainVideoAdversePresenter extends BasePresenter<MainVideoContract.AdvView>{

    public static final String packageName = MainVideoPresenter.class.getName();

    private MainVideoContract.AdvView iView;

    private Disposable disposable;

    public MainVideoAdversePresenter(MainVideoContract.AdvView iView){
        this.iView = iView;
    }

    public void getData(){

                disposable = MainVideoAdverseModel.getInstance().hot_response()
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iView.showLoad();
                                LogUtil.loging(packageName,1,packageName + "  开始访问数据");
                            }
                        })
                        .map(new Function<HotVideoBean, List<HotVideoBean.HotABean>>() {
                            @Override
                            public List<HotVideoBean.HotABean> apply(HotVideoBean hotBean) throws Exception {
                                return hotBean.getIssueList();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<HotVideoBean.HotABean>>() {
                            @Override
                            public void accept(List<HotVideoBean.HotABean> storiesBeans) throws Exception {
                                LogUtil.loging(packageName,1,packageName + "  获取数据成功");
                                iView.dismissLoad();
                                iView.showData(storiesBeans.get(0).getItemList());
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
