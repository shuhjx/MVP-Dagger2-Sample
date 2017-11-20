package com.shuh.osplearning;

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.Toast
import com.shuh.osplearning.adapter.MainViewPagerAdapter
import com.shuh.osplearning.base.BaseActivity
import com.shuh.osplearning.fragment.Fragment1
import com.shuh.osplearning.fragment.Fragment2
import com.shuh.osplearning.fragment.Fragment3
import com.shuh.osplearning.fragment.Fragment4
import com.shuh.osplearning.utils.ViewUtils
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var adapter: MainViewPagerAdapter
    lateinit var fragments: ArrayList<Fragment>
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar

    override fun findViews(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        setContentView(R.layout.activity_main)
        viewPager = ViewUtils.getInstance().findViewById(this, R.id.viewPager)
        tabLayout = ViewUtils.getInstance().findViewById(this, R.id.tabLayout)
        navigationView = ViewUtils.getInstance().findViewById(this, R.id.navigation)
        drawerLayout = ViewUtils.getInstance().findViewById(this, R.id.drawer_layout)
        toolbar = ViewUtils.getInstance().findViewById(this, R.id.toolbar)
        return View(this)
    }

    override fun initViews(savedInstanceState: Bundle?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        setSupportActionBar(toolbar)
        initTablayout()
        toolbar.setNavigationIcon(R.mipmap.menu)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    var mExitTime: Long=0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - mExitTime > 2000){
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                return true
            }else{
                this.finish()
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    fun initTablayout(){
        fragments = ArrayList()
        fragments.add(Fragment1())
        fragments.add(Fragment2())
        fragments.add(Fragment3())
        fragments.add(Fragment4())
        adapter = MainViewPagerAdapter(getSupportFragmentManager(), fragments)
        adapter.setTitles(arrayOf("首页", "发现", "进货单","我的"))
        viewPager.setAdapter(adapter)
        viewPager.setCurrentItem(0)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabsFromPagerAdapter(adapter)
    }
    override fun setListener() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        toolbar.setNavigationOnClickListener { view: View? -> drawerLayout.openDrawer(Gravity.LEFT) }

//        toolbar.setOnMenuItemClickListener { menuItem: MenuItem? ->
//            when (menuItem?.itemId) {
////                R.id.action_edit -> Log.d("Debug", "=========action_edit===========")
//                R.id.action_exit -> finish()
//            }
//            return false
//        }

        navigationView.setNavigationItemSelectedListener { item: MenuItem? -> {
                when (item?.itemId) {
                    R.id.id1 -> Log.d("Debug", "=========id1===========")
                    R.id.id2 -> Log.d("Debug", "=========id2===========")
                }
                //关闭菜单
                drawerLayout.closeDrawers()
//            return false
            }
        }

    }
/*
    public void setListener(){

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
    }*/
}
