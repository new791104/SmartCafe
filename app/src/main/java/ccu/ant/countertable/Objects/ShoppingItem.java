package ccu.ant.countertable.Objects;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ccu.ant.countertable.Fragment.MyFragment;

/**
 * Created by Egoist on 2016/7/9.
 * TODO 購物車(訂單) object
 */
public class ShoppingItem {
    //櫃台APP自訂的參數
    private int Number;
    private int talPrice;
    private String uPic_url;
    private int tabNumber;
    //

    private int sID;        //購物單號 (日期混時間，作為該次購物的單號，確認前的購物項目皆用同一組)
    private String sUserName;
    private String sUserEmail;         //消費者Email
    private ProductItem[] sProductItem;//單品Item (TODO + 購買數量、單品備註)
    private Date sDate;     //下訂日期

    private boolean sIsCoupon;   //是否使用優惠卷
    private String[]  sCouponID; //優惠卷 ID

    //DummyItem對應的 Fragment 列表 (新增左邊選項時一起新增右邊頁面)
    public static List<Fragment> fragments = new ArrayList<>();


    public ShoppingItem(int num, int sid, String uname, String uemail, String upic, ProductItem[] items, Date sdate, int talprice, int tabnum, boolean iscoupon, String[] couponid) {
        setNumber(num);
        setsID(sid);
        setsUserName(uname);
        setsUserEmail(uemail);
        setuPic_url(upic);
        setsProductItem(items);
        setsDate(sdate);
        setTalPrice(talprice);
        setTabNumber(tabnum);
        setsIsCoupon(iscoupon);
        setsCouponID(couponid);

        //與 MyFragment 的接口，newInstance 會實體化並回傳一個 Fragment。
        //而 fragment 陣列被宣告為static為全域變數
        fragments.add(MyFragment.newInstance(num, sid, uname, uemail, upic, items, sdate, talprice, tabnum, iscoupon, couponid));
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsUserEmail() {
        return sUserEmail;
    }

    public void setsUserEmail(String sUserEmail) {
        this.sUserEmail = sUserEmail;
    }

    public ProductItem[] getsProductItem() {
        return sProductItem;
    }

    public void setsProductItem(ProductItem[] sProductItem) {
        this.sProductItem = sProductItem;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public boolean issIsCoupon() {
        return sIsCoupon;
    }

    public void setsIsCoupon(boolean sIsCoupon) {
        this.sIsCoupon = sIsCoupon;
    }

    public String[] getsCouponID() {
        return sCouponID;
    }

    public void setsCouponID(String[] sCouponID) {
        this.sCouponID = sCouponID;
    }

    public int getTalPrice() {
        return talPrice;
    }

    public void setTalPrice(int talPrice) {
        this.talPrice = talPrice;
    }

    public String getuPic_url() {
        return uPic_url;
    }

    public void setuPic_url(String uPic_url) {
        this.uPic_url = uPic_url;
    }

    public int getTabNumber() {
        return tabNumber;
    }

    public void setTabNumber(int tabNumber) {
        this.tabNumber = tabNumber;
    }
}
