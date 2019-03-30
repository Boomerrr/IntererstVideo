package com.example.think.videodemo.Api;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.Bean.FunnyImageBean;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.JokeBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.Bean.TestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * "http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";主页广告牌
 *
 *  "http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
 *  val STRATEGY = arrayOf("weekly", "monthly", "historical")//周排行 日排行 月排行   排行榜
 *
 * http://baobab.kaiyanapp.com/api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83 主页视频
 *
 *
 *  //搜索关键字
 * http://baobab.kaiyanapp.com/api/v1/search?num=10&query=xxx&start=10
 * 段子
 * public static final String NEWS_JOCK_URL = "http://japi.juhe.cn/joke/content/text.from?key=aea3530fe10ee7947528d76711ee7796&page=1&pagesize=20";
 * 趣图
 * public static final String NEWS_IMAGE_URL = "http://japi.juhe.cn/joke/img/text.from?key=aea3530fe10ee7947528d76711ee7796&page=1&pagesize=20";
 *
 * */
public interface ApiService {

    String TEST_URL = "https://news-at.zhihu.com/api/4/";

    String HOT_VIDEO_URL = "http://baobab.kaiyanapp.com/api/v2/";

    String LOCATION_URL = "http://api.map.baidu.com/location/";

    String MAIN_VIDEO_URL = "http://baobab.kaiyanapp.com/api/v3/";

    String RANK_URL = "http://baobab.wandoujia.com/api/v3/";

    String CATEGORY_URL = "http://baobab.kaiyanapp.com/api/v4/";

    String INTEREST_JOKE_URL = "http://japi.juhe.cn/joke/content/";

    String INTEREST_IMAGE_URL = "http://japi.juhe.cn/joke/img/";

    String SEARCH_URL = "http://baobab.kaiyanapp.com/api/v1/";

    /**
     * 测试接口
     *
     * @return
     */
    @GET("news/latest")
    Observable<TestBean> test();

    @GET("feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<HotVideoBean> hot_response();

    @GET("ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<MainVideoBean> main_response();

    @GET("ranklist?num=10&strategy=weekly&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<RankBean> rank_weekly_response();

    @GET("ranklist?num=10&strategy=monthly&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<RankBean> rank_monthly_response();

    @GET("ranklist?num=10&strategy=historical&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<RankBean> rank_historical_response();

    @GET("categories/")
    Observable<List<CategoryItem>> category_response();

    @GET("text.from?key=aea3530fe10ee7947528d76711ee7796&page=1&pagesize=20")
    Observable<JokeBean> interest_store_response();

    @GET("text.from?key=aea3530fe10ee7947528d76711ee7796&page=1&pagesize=20")
    Observable<FunnyImageBean> interest_image_response();

    @GET("search?num=10&&start=10")
    Observable<RankBean> search_response(@Query("query") String query);
}
