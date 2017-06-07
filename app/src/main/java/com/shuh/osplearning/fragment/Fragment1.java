package com.shuh.osplearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuh.osplearning.R;
import com.shuh.osplearning.adapter.holder.HomeViewHolder;
import com.shuh.osplearning.base.BaseFragment;
import com.shuh.osplearning.base.BaseRecyclerAdapter;
import com.shuh.osplearning.model.impl.HomeModel;
import com.shuh.osplearning.retrofit.services.HomeService;
import com.shuh.osplearning.utils.ViewUtils;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.reactivex.Scheduler;
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
    private ArrayList<HomeModel> list;
    private GridLayoutManager mLayoutManager;

    @Override
    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, null);
        recyclerView = ViewUtils.getInstance().findViewById(view, R.id.recyclerView);

        return view;
    }

    @Override
    public void initViews(@Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .baseUrl(HomeService.BASE_URL)
               .build();

        HomeService service = retrofit.create(HomeService.class);

        //subscribeOn()：指定subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
        //observeOn()：指定Subscriber 所运行在的线程。或者叫做事件消费的线程。
        //observeOn()可以多次使用，可以随意变换线程
        service.list("tech", 1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());





        adapter = new BaseRecyclerAdapter<HomeViewHolder>(getActivity(), list){
            @Override
            public HomeViewHolder createHolder(ViewGroup parent, int viewType) {
                return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item, parent, false));
            }
        };

        mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setListener() {

    }
}
