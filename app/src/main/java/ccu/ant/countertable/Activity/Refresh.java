package ccu.ant.countertable.Activity;

import java.util.ArrayList;
import java.util.Date;

import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;

/**
 * Created by Charlie on 2016/8/5.
 */
public class Refresh {
    public void remove(int i, int sItem_size){
        //刪除左邊List的選項
        if (i == sItem_size - 1) GV.mPager.setCurrentItem(i - 1);
        GV.sItem.remove(i);

        //刪除右邊fragments
        ShoppingItem.fragments.remove(i);

        //刪除finish計數器
        GV.now_finish.remove(i);

        GV.mList.getAdapter().notifyDataSetChanged();
        GV.mPager.getAdapter().notifyDataSetChanged();
    }

    public void add(String _id, String Email, int TabNumber, ArrayList<ProductItem> productItems, Date date, boolean isCoupon, ArrayList<CouponItem> couponItems){
        GV.number++;
        GV.sItem.add(new ShoppingItem(GV.number, _id, Email,
                TabNumber, productItems, date,
                isCoupon, couponItems));

        GV.mList.getAdapter().notifyDataSetChanged();
        GV.mPager.getAdapter().notifyDataSetChanged();
    }


}
