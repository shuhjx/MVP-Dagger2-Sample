package com.shuh.osplearning.retrofit.services;

import com.shuh.osplearning.model.impl.HomeModel;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pc-135 on 2017/6/7.
 */
public interface HomeService {

    String BASE_URL = "http://wangyi.butterfly.mopaasapp.com/";
    /**
     * 请求链接：http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page=1&limit=10
     * 新闻列表
     * type :新闻类型
     * page :当前页数
     * limit :请求条数
     * #	代码	    类型
     * 1	war	    军事
     * 2	sport	体育
     * 3	tech	科技
     * 4	edu	    教育
     * 5	ent	    娱乐
     * 6	money	财经
     * 7	gupiao	股票
     * 8	travel	旅游
     * 9	lady	女人
     */
    @GET("news/api")
    Observable<List<HomeModel>> list(@Query("type") String type,
                                     @Query("page") int page,
                                     @Query("limit") int limit);

    /**
     * 详情列表
     * 请求链接：http://wangyi.butterfly.mopaasapp.com/detail/api?simpleId=8
     * simpleId:新闻id
     */
    @GET("detail/api")
    Observable<HomeModel> detail(@Query("simpleId") String simpleId);
}
