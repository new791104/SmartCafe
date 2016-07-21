package ccu.ant.countertable.Item;



import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Fragment.MyFragment;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;

/**
 * Created by Egoist on 2016/7/5.
 * At 2016/07/21, update by Charlie.
 * TODO Dummy Item 為一個虛構的資料集合
 */
public class DummyItem {
    public ProductItem[] pitem = new ProductItem[3];
    //public static ShoppingItem dummy = ShoppingItem(GV.number, 20160721, "陳宗仁", "charliebot01@gmail.com", , "take out", "3");

    public DummyItem(){
        Log.e("flag","2");
        pitem[0] = new ProductItem(1, "拿鐵", "latte", "有奶的咖啡。是的，非常奶", 50, 100, "NEWS", false, "pImageUrl"
                , 721, false, false, false, false, false, false);
        pitem[1] = new ProductItem(2, "卡布奇諾", "Kabuqinuo", "打到發泡的 奶", 50, 100, "NEWS", false, "pImageUrl"
                , 721, false, false, false, false, false, false);
        pitem[2] = new ProductItem(3, "貓咪拿鐵", "Cat'latte", "貓貓粗奶玩!", 50, 100, "NEWS", false, "pImageUrl"
                , 721, false, false, false, false, false, false);
    }

    /*
    public int number;
    private String id;
    private String orders;
    private String price;
    private String place;
    private String take_number;
    private String request;
    private String name;
    private String pic_url;
    private String color_background;

    //DummyItem對應的 Fragment 列表 (新增左邊選項時一起新增右邊頁面)
    public static List<Fragment> fragments = new ArrayList<>();


    public DummyItem(int num, int sid, String uname, ProductItem[] items, int talprice, String place, boolean iscoupon, String[] couponid) {
        setNumber(num);
        setsID(sid);
        setsUserName(uname);
        setsProductItem(items);
        setTalPrice(talprice);
        setsPlace(place);


        //與 MyFragment 的接口，newInstance 會實體化並回傳一個 Fragment。
        //而 fragment 陣列被宣告為static為全域變數
        fragments.add(MyFragment.newInstance(num,name,orders,price,request,place,take_number));
    }*/

}
