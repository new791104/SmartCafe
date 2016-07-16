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
    public static ViewPager mPager;
    FrgPagerAdapter mPagerAdapter;
    public static List<DummyItem> sItem = new ArrayList<DummyItem>();
    public static int NOW_POS;
    private int number = 0;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //
            number++;
            sItem.add(new DummyItem(number,"陳\n先生","拿鐵咖啡","100","去冰半糖","take out","2"));
            //把上面的List放進左邊RecycleView列表

            mList.getAdapter().notifyDataSetChanged();
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
        mList.setAdapter(new SampleAdapter(MainActivity.this,sItem));
        mList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                NOW_POS = position;
                mPager.getAdapter().notifyDataSetChanged();
                mList.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    //===================================================

    //實作從Fragment回傳的動作
    @Override
    public void onRemoveSelected(int orderArrIndex) {
        //
        if(sItem.size() > 1) {
            /*if (DummyItem.fragments.listIterator(data_int).next() != null)
                mPager.setCurrentItem(data_int + 1);*/
            //if(sItem.get(sItem.size()) != null) {
                //刪除左邊List的選項
                if(orderArrIndex == sItem.size()-1) mPager.setCurrentItem(orderArrIndex-1);
                sItem.remove(orderArrIndex);
                //mPager.setCurrentItem(data_int + 1);
                //刪除右邊fragments
                DummyItem.fragments.remove(orderArrIndex);
                mPager.getAdapter().notifyDataSetChanged();
                Log.e("flags:","2");

                //mList.getAdapter().notifyItemRemoved(orderArrIndex);
                mList.getAdapter().notifyDataSetChanged();

                Log.e("flags:","3");

                Log.e("Array:",sItem.toArray().toString());
                Log.e("fragments.size", "" + DummyItem.fragments.size());
                //Log.e("fragments_ID",""+DummyItem.fragments.);
                Log.e("debug:", DummyItem.fragments.toString());
        }
    }

    @Override
    public void onAddSelected(){
        number++;
        sItem.add(new DummyItem(number,"柴\n先生","拿鐵咖啡,麵包","100","去冰半糖","take out","3"));
        mList.getAdapter().notifyDataSetChanged();
        mPager.getAdapter().notifyDataSetChanged();
    }
}
