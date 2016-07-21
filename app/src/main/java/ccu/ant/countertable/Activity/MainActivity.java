package ccu.ant.countertable.Activity;


import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import ccu.ant.countertable.Adapter.FrgPagerAdapter;
import ccu.ant.countertable.Adapter.SampleAdapter;
import ccu.ant.countertable.Fragment.ButAct;
import ccu.ant.countertable.Fragment.OnHeadlineSelectedListener;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    FrgPagerAdapter mPagerAdapter;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //
            GV.number++;
            GV.sItem.add(new ShoppingItem(GV.number, 20160721, "陳宗仁", "charliebot01@gmail.com", GV.pic_url, GV.dummy.pitem, GV.date, 150, 0, true, GV.couponid));
            //把上面的List放進左邊RecycleView列表

            GV.mList.getAdapter().notifyDataSetChanged();
            GV.mPager.getAdapter().notifyDataSetChanged();

            //initViewPager();
            Log.e("handler","ok!");
            handler.postDelayed(this, 10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("flag","0");
        GV.mList = (RecyclerView) findViewById(R.id.main_RecyclerView);
        Log.e("flag","00");
        GV.mPager = (ViewPager) findViewById(R.id.main_FragmentPager) ;
    Log.e("flag","1");
        //監聽時間變化
        GV.mList.setAdapter(new SampleAdapter(MainActivity.this,GV.sItem));
        GV.mList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        handler.postDelayed(runnable, 2000);

        //初始化右邊滑動的Fragment
        initViewPager();

        /********Delete Brtton*******/
        FloatingActionButton delbut = (FloatingActionButton) findViewById(R.id.delbut);
        delbut.setOnClickListener(this);

        /*******Add Button********/
        FloatingActionButton master_add = (FloatingActionButton) findViewById(R.id.master_add);
        master_add.setOnClickListener(this);

        /********Update Button********/
        FloatingActionButton master_update = (FloatingActionButton) findViewById(R.id.updatebut);
        master_update.setOnClickListener(this);
    }

    //=======================================================
    //TODO: 滑動Fragments布局
    private void initViewPager(){
        List<Fragment> fragments = ShoppingItem.fragments;
        mPagerAdapter = new FrgPagerAdapter(getSupportFragmentManager(), fragments);
        GV.mPager.setAdapter(mPagerAdapter);
        GV.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                GV.NOW_POS = position;
                GV.mPager.getAdapter().notifyDataSetChanged();
                GV.mList.getAdapter().notifyDataSetChanged();
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
            case R.id.updatebut:
                butact.update();
                break;
            case R.id.delbut:
                butact.remove(GV.NOW_POS);
                break;
            case R.id.master_add:
                butact.add(v);
                break;
        }
    }

    //===================================================
}
