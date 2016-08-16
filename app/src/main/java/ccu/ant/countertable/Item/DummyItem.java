package ccu.ant.countertable.Item;



import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Fragment.MyFragment;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;

/**
 * Created by Egoist on 2016/7/5.
 * Update by Charlie on 2016/7/21.
 * TODO Dummy Item 為一個虛構的資料集合
 */

public class DummyItem {
    public ProductItem[] pitem = new ProductItem[3];
    public ProductItem[] pitem2 = new ProductItem[8];
    public CouponItem[] couponItem = new CouponItem[2];
    public ProductItem[] menus = new ProductItem[3];
    //public static ShoppingItem dummy = ShoppingItem(GV.number, 20160721, "陳宗仁", "charliebot01@gmail.com", , "take out", "3");

    public DummyItem(){
        Log.e("flag","2");

        couponItem[0]  = new CouponItem("meal", "title", "off", 0.5, GV.date);
        couponItem[1]  = new CouponItem("meal", "title", "point", 10, GV.date);

        pitem[0] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem[1] = new ProductItem("卡布奇諾", "Kabuqinuo", "濃度:3糖量:3奶量:3冰量:3", 75, 1,"");
        pitem[2] = new ProductItem("貓咪拿鐵", "Cat'latte", "濃度:3糖量:3奶量:3冰量:3", 100, 2,"");

        pitem2[0] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[1] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[2] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[3] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[4] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[5] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[6] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");
        pitem2[7] = new ProductItem("拿鐵", "latte", "濃度:3糖量:3奶量:3冰量:3", 50, 1,"");

        //menu
        menus[0] = new ProductItem("拿鐵","latte","", 50, 0, "加奶的黑咖啡");
        menus[1] = new ProductItem("卡布奇諾","latte","", 75, 0, "有奶泡的咖啡");
        menus[2] = new ProductItem("貓咪拿鐵","latte","", 100, 0, "加貓咪的拿鐵");

    }

}
