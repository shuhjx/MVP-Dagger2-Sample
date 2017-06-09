package com.shuh.osplearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuh.osplearning.R;
import com.shuh.osplearning.adapter.holder.HomeViewHolder;
import com.shuh.osplearning.base.BaseFragment;
import com.shuh.osplearning.base.BaseRecyclerAdapter;
import com.shuh.osplearning.model.impl.HomeModel;
import com.shuh.osplearning.retrofit.services.HomeService;
import com.shuh.osplearning.retrofit.services.factory.HomeConverterFactory;
import com.shuh.osplearning.utils.ViewUtils;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc-135 on 2017/5/11.
 */
public class Fragment1 extends BaseFragment {

    private RecyclerView recyclerView;
    private BaseRecyclerAdapter<HomeViewHolder> adapter;
    final private List<HomeModel> list = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    @Override
    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, null);
        recyclerView = ViewUtils.getInstance().findViewById(view, R.id.recyclerView);

        return view;
    }

    @Override
    public void initViews(@Nullable Bundle savedInstanceState) {
        adapter = new BaseRecyclerAdapter<HomeViewHolder>(getActivity(), list){
            @Override
            public HomeViewHolder createHolder(ViewGroup parent, int viewType) {
                return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item, parent, false));
            }
        };

        mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        getDataFromHttp();
    }

    @Override
    public void setListener() {

    }

    private void getDataFromHttp(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(HomeConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HomeService.BASE_URL)
                .build();

        HomeService service = retrofit.create(HomeService.class);

        //subscribeOn()：指定subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
        //observeOn()：指定Subscriber 所运行在的线程。或者叫做事件消费的线程。
        //observeOn()可以多次使用，可以随意变换线程
        service.list("tech", 1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<List<HomeModel>, List<HomeModel>>() {
//                    @Override
//                    public List<HomeModel> apply(@NonNull List<HomeModel> homeModels) throws Exception {
//                        return null;
//                    }
//                })
                .subscribe(new Observer<List<HomeModel>>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<HomeModel> homeModels) {
                        list.addAll(homeModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
