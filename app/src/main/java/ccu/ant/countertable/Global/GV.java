package ccu.ant.countertable.Global;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ccu.ant.countertable.Item.DummyItem;
import ccu.ant.countertable.Objects.ShoppingItem;

/**
 * Created by Charlie on 2016/7/20.
 */
public class GV {
    //Dummy Data
    public static DummyItem dummy = new DummyItem();
    public static Date date = new Date();
    public static String[] couponid = {"A000001", "A000002"};
    public static String pic_url = "http://2.bp.blogspot.com/-M-BcI-ngzE4/U2uHvl00yQI/AAAAAAACFOU/Oc2In5z8Dfs/s1600/788.png";
    //

    public static RecyclerView mList;
    //Fragment
    public static ViewPager mPager;
    public static List<ShoppingItem> sItem = new ArrayList<ShoppingItem>();
    public static int NOW_POS;
    public static int number = 0;
    public static int focus_pos = 0;


}
