package ccu.ant.countertable.Global;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ccu.ant.countertable.Item.DummyItem;
import ccu.ant.countertable.Network_core;
import ccu.ant.countertable.Objects.ShoppingItem;
import me.kaelaela.verticalviewpager.VerticalViewPager;

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

    public static String old_response;
    public static RecyclerView mList;
    //Fragment
    //public static VerticalViewPager mPager;
    public static ViewPager mPager;
    public static List<ShoppingItem> sItem = new ArrayList<ShoppingItem>();
    public static int NOW_POS;
    public static int number = 0;
    public static List<Integer> now_finish = new ArrayList<Integer>();
    //Fragment裡面的List，用來顯示餐點項目
    public static List<RecyclerView> sList = new ArrayList<RecyclerView>();
/*
    public static void refreshShop(Network_core nCore){
        final String[] response_local = {new String("")};

        final Gson gson = new Gson();
        nCore.shop_FindAll();
        nCore.setCallback(new Network_core.netCallback() {
            @Override
            public String response(String response) {
                response_local[0] = response;
                ArrayList<ShoppingItem> shoppingItems = gson.fromJson(response_local[0], new TypeToken<List<ShoppingItem>>(){}.getType());

                if(GV.sItem.size() == 0) {
                    for(int i = 0;i < shoppingItems.size();i++)
                        GV.sItem.add(shoppingItems.get(i));
                    Log.e("test12","in");
                }
                else {
                    for (int i = 0; i < GV.sItem.size(); i++) {
                        if (shoppingItems.indexOf(GV.sItem.get(i).get_id()) < 0) {
                            GV.sItem.add(shoppingItems.get(i));
                        }
                    }
                }

                GV.mList.getAdapter().notifyDataSetChanged();
                GV.mPager.getAdapter().notifyDataSetChanged();
                return null;
            }
        });

    }
    */
}
