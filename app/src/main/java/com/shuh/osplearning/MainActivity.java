package com.shuh.osplearning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shuh.osplearning.adapter.MainViewPagerAdapter;
import com.shuh.osplearning.base.BaseActivity;
import com.shuh.osplearning.fragment.Fragment1;
import com.shuh.osplearning.fragment.Fragment2;
import com.shuh.osplearning.fragment.Fragment3;
import com.shuh.osplearning.fragment.Fragment4;
import com.shuh.osplearning.utils.ViewUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewPagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        viewPager = ViewUtils.getInstance().findViewById(this, R.id.viewPager);
        tabLayout = ViewUtils.getInstance().findViewById(this, R.id.tabLayout);
        navigationView = ViewUtils.getInstance().findViewById(this, R.id.navigation);
        drawerLayout = ViewUtils.getInstance().findViewById(this, R.id.drawer_layout);
        toolbar = ViewUtils.getInstance().findViewById(this, R.id.toolbar);
        return null;
    }

    public void initViews(@Nullable Bundle savedInstanceState){
        setSupportActionBar(toolbar);
        initTablayout();
        toolbar.setNavigationIcon(R.mipmap.menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private long mExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - mExitTime > 2000){
                mExitTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                return true;
            }else{
                this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTablayout(){
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        adapter.setTitles(new String[]{"首页", "发现", "进货单","我的"});
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public void setListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开左边的菜单
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_edit:
//                        Log.d("Debug", "=========action_edit===========");
                        break;
                    case R.id.action_exit:
                        MainActivity.this.finish();
                        break;
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.id1:
//                        Log.d("Debug", "=========id1===========");
//                        break;
//                    case R.id.id2:
//                        Log.d("Debug", "=========id2===========");
//                        return false;
//                    case R.id.id3:
//                        Log.d("Debug", "=========id3===========");
//                        break;
//                    case R.id.id4:
//                        Log.d("Debug", "=========id4===========");
//                        break;
//                }
                //关闭菜单
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.d("Debug", "=========onDrawerSlide===========");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
//                Log.d("Debug", "=========onDrawerOpened===========");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                Log.d("Debug", "=========onDrawerClosed===========");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
//                Log.d("Debug", "=========onDrawerStateChanged===========");
            }
        });
    }
}
