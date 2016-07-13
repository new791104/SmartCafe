package ccu.ant.countertable.Activity;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ccu.ant.countertable.Adapter.FrgPagerAdapter;
import ccu.ant.countertable.Adapter.SampleAdapter;
import ccu.ant.countertable.Fragment.MyFragment;
import ccu.ant.countertable.Fragment.OnHeadlineSelectedListener;
import ccu.ant.countertable.Item.DummyItem;
import ccu.ant.countertable.R;

public class MainActivity extends AppCompatActivity implements OnHeadlineSelectedListener {

    RecyclerView mList ;
    //Fragment
    ViewPager mPager;
    FrgPagerAdapter mPagerAdapter;
    private List<DummyItem> sItem = new ArrayList<DummyItem>();
    private int number = 1;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //到時候在這裡檢查資料庫是否有更新資料
            sItem.add(new DummyItem(number,"陳先生","拿鐵咖啡","100","去冰半糖","take out","2"));
            number++;
            //把上面的List放進左邊RecycleView列表
            mList.setAdapter(new SampleAdapter(MainActivity.this,sItem));
            mList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mPager.getAdapter().notifyDataSetChanged();
            //initViewPager();
            Log.e("handler","ok!");
            handler.postDelayed(this, 10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (RecyclerView) findViewById(R.id.main_RecyclerView);
        mPager = (ViewPager) findViewById(R.id.main_FragmentPager);

        //監聽時間變化
        handler.postDelayed(runnable, 2000);

        //初始化右邊滑動的Fragment
        initViewPager();
    }

    //=======================================================
    //TODO: 滑動Fragments布局
    private void initViewPager(){
        List<Fragment> fragments = DummyItem.fragments;
        mPagerAdapter = new FrgPagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
    }
    //===================================================

    //實作從Fragment回傳的動作
    @Override
    public void onaitemSelected(int data_int) {
        Log.e("test:", "success! " + data_int);
        int orderArrIndex = data_int;
        //search
        for(int i = 0;i < sItem.size();i++){
            if(sItem.get(i).number-1 == data_int){
                orderArrIndex = i;
                Log.e("orderArrIndex:",""+orderArrIndex);
                break;
            }
        }
        Log.e("flags:","1");
        //Log.e("get:",""+orderArr.get(data_int));
        //
        if(sItem.size() > 1) {
            /*if (DummyItem.fragments.listIterator(data_int).next() != null)
                mPager.setCurrentItem(data_int + 1);*/
            if(sItem.listIterator(orderArrIndex).next() != null) {

                //mPager.setCurrentItem(data_int + 1);
                //刪除右邊fragments
                DummyItem.fragments.remove(orderArrIndex);
                mPagerAdapter.notifyDataSetChanged();
                Log.e("flags:","2");
                //刪除左邊List的選項
                sItem.remove(orderArrIndex);
                mList.getAdapter().notifyItemRemoved(orderArrIndex);

                Log.e("flags:","3");

                Log.e("Array:",sItem.toArray().toString());
                Log.e("fragments.size", "" + DummyItem.fragments.size());
                //Log.e("fragments_ID",""+DummyItem.fragments.);
                Log.e("debug:", DummyItem.fragments.toString());
            }
        }

    }
}
