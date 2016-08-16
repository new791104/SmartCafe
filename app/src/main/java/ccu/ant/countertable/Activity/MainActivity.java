package ccu.ant.countertable.Activity;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ccu.ant.countertable.Adapter.FrgPagerAdapter;
import ccu.ant.countertable.Adapter.SampleAdapter;
import ccu.ant.countertable.Fragment.NonSwipeableViewPager;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Network_core;
import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;
import me.kaelaela.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Network_core nCore;
    FrgPagerAdapter mPagerAdapter;
    String LOG_TAG = "DEBUG";
    private Handler handler=new Handler();
    private String old_response = new String();
    private Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub

            //更新訂單
            //GV.refreshShop(nCore);
            final Gson gson = new Gson();
            final Refresh refresh = new Refresh();

            nCore.shop_FindAll();
            nCore.setCallback(new Network_core.netCallback() {
                @Override
                public String response(String response) {
                    ArrayList<ShoppingItem> shoppingItems = gson.fromJson(response, new TypeToken<List<ShoppingItem>>(){}.getType());
                    int i;
                    int sItem_size = GV.sItem.size(), shop_size = shoppingItems.size();

                    if(GV.sItem.size() == 0) {
                        old_response = response;
                        for(i = 0;i < shop_size;i++){
                            if(shop_size != shoppingItems.size()){
                                i = 0;
                                shop_size = shoppingItems.size();
                                continue;
                            }
                            refresh.add(shoppingItems.get(i).get_id(), shoppingItems.get(i).getsUserEmail(),
                                    shoppingItems.get(i).getTabNumber(), shoppingItems.get(i).getsProductItem(), shoppingItems.get(i).getsDate(),
                                    shoppingItems.get(i).issIsCoupon(),shoppingItems.get(i).getsCoupon());
                        }
                    }
                    else {
                        if(response.equals(old_response)){
                            for(i = 0; i < shop_size; i++) {
                                if(shop_size != shoppingItems.size()){
                                    i = 0;
                                    shop_size = shoppingItems.size();
                                    continue;
                                }
                                if (GV.sItem.get(i).getsProductItem().size() != shoppingItems.get(i).getsProductItem().size()) {
                                    GV.sItem.get(i).setsProductItem(shoppingItems.get(i).getsProductItem());
                                }
                            }
                        }
                        else{
                            if(old_response.length() < response.length()) {

                                for (i = 0; i < shop_size; i++) {
                                    if(shop_size != shoppingItems.size()){
                                        i = 0;
                                        shop_size = shoppingItems.size();
                                        continue;
                                    }
                                    //ShoppingItem DItem = GV.sItem.get(i);
                                    if (GV.sItem.toString().indexOf(shoppingItems.get(i).get_id().toString()) < 0) {
                                        refresh.add(shoppingItems.get(i).get_id(), shoppingItems.get(i).getsUserEmail(),
                                                shoppingItems.get(i).getTabNumber(), shoppingItems.get(i).getsProductItem(), shoppingItems.get(i).getsDate(),
                                                shoppingItems.get(i).issIsCoupon(),shoppingItems.get(i).getsCoupon());
                                    }

                                }
                            }
                            else if(old_response.length() > response.length()){
                                for (i = 0; i < GV.sItem.size(); i++) {
                                    if (shoppingItems.toString().indexOf(GV.sItem.get(i).get_id().toString()) < 0) {
                                        refresh.remove(i, GV.sItem.size());
                                    }
                                }
                            }
                            else{
                                for(i = 0;i < shop_size;i++){
                                    if(shop_size != shoppingItems.size()){
                                        i = 0;
                                        continue;
                                    }
                                    if(!GV.sItem.get(i).get_id().toString().equals(shoppingItems.get(i).get_id().toString())){
                                        refresh.remove(i, GV.sItem.size());
                                        refresh.add(shoppingItems.get(i).get_id(), shoppingItems.get(i).getsUserEmail(),
                                                shoppingItems.get(i).getTabNumber(), shoppingItems.get(i).getsProductItem(), shoppingItems.get(i).getsDate(),
                                                shoppingItems.get(i).issIsCoupon(),shoppingItems.get(i).getsCoupon());
                                    }
                                }
                            }
                            old_response = response;
                        }
                    }

                    GV.mList.getAdapter().notifyDataSetChanged();
                    GV.mPager.getAdapter().notifyDataSetChanged();
                    return null;
                }
            });

            //GV.sItem.add(new ShoppingItem(""+GV.number, "charliebot01@gmail.com", 1, GV.dummy.pitem,GV.date, false, GV.dummy.couponItem));
            //把上面的List放進左邊RecycleView列表

            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GV.mList = (RecyclerView) findViewById(R.id.main_RecyclerView);
        //GV.mPager = (VerticalViewPager) findViewById(R.id.main_FragmentPager);
        GV.mPager = (NonSwipeableViewPager) findViewById(R.id.main_FragmentPager);

        GV.mList.setAdapter(new SampleAdapter(MainActivity.this,GV.sItem));     //設定適配器
        GV.mList.setLayoutManager(new LinearLayoutManager(MainActivity.this));  //設定RecyclerView的布局形式
        nCore = new Network_core(this);

        //監聽時間變化
        handler.postDelayed(runnable, 1000);

        //初始化右邊滑動的Fragment
        initViewPager();

        /********Delete Brtton*******/
        /*FloatingActionButton delbut = (FloatingActionButton) findViewById(R.id.delbut);
        delbut.setOnClickListener(this);*/

        /*******Add Button********/
        FloatingActionButton master_add_but = (FloatingActionButton) findViewById(R.id.master_add_but);
        master_add_but.setOnClickListener(this);

        /*******Alter Button********/
        FloatingActionButton alter_but = (FloatingActionButton) findViewById(R.id.alter_but);
        alter_but.setOnClickListener(this);

        /********Update Button********/
        /*FloatingActionButton master_update = (FloatingActionButton) findViewById(R.id.updatebut);
        master_update.setOnClickListener(this);*/
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "+ ON RESUME +");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOG_TAG, "++ ON START ++");
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.e(LOG_TAG, "++ onPause ++");
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.e(LOG_TAG, "++ onRestart ++");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.e(LOG_TAG, "++ onStop ++");
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e(LOG_TAG, "++ onDestroy ++");
    }

    //=======================================================
    //TODO: 滑動Fragments布局
    private void initViewPager(){
        final List<Fragment> fragments = ShoppingItem.fragments;
        mPagerAdapter = new FrgPagerAdapter(getSupportFragmentManager(), fragments);

        GV.mPager.setAdapter(mPagerAdapter);
        GV.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position < GV.sItem.size()) {
                    GV.NOW_POS = position;
                    GV.mPager.getAdapter().notifyDataSetChanged();
                    GV.mList.getAdapter().notifyDataSetChanged();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    //點擊事件
    @Override
    public void onClick(View v) {
        ButAct butact = new ButAct();
        switch (v.getId()){
            /*case R.id.updatebut:
                butact.update();
                break;
            case R.id.delbut:
                butact.remove(GV.NOW_POS);
                break;*/
            case R.id.master_add_but:
                butact.add(v);
                break;
            case R.id.alter_but:
                butact.alter(v);
                break;
        }
    }

    //===================================================
}
