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
    private int Number = 0;
    public int finishing = 0;
    public boolean isDone = false;
    //
    private int sTabNumber;

    public String _id;        //購物單號　hash
    public String sUserEmail;         //消費者Email
    public ArrayList<ProductItem> sProductItem = new ArrayList<>();//單品Item (TODO + 購買數量、單品備註)
    public Date sDate;     //下訂日期

    public boolean sIsCoupon;   //是否使用優惠卷
    ArrayList<CouponItem> sCoupon = new ArrayList<>(); //優惠卷

    //DummyItem對應的 Fragment 列表 (新增左邊選項時一起新增右邊頁面)
    public static List<Fragment> fragments = new ArrayList<>();

    public ShoppingItem(int Number,String _id, String sUserEmail, int sTabNumber,ArrayList<ProductItem> sProductItem, Date sDate, boolean sIsCoupon, ArrayList<CouponItem> sCoupon) {
        this.Number = Number;
        this._id = _id;
        this.sUserEmail = sUserEmail;
        this.sTabNumber = sTabNumber;
        this.sProductItem = sProductItem;
        this.sDate = sDate;
        this.sIsCoupon = sIsCoupon;
        this.sCoupon = sCoupon;

        fragments.add(MyFragment.newInstance());

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getsUserEmail() {
        return sUserEmail;
    }

    public void setsUserEmail(String sUserEmail) {
        this.sUserEmail = sUserEmail;
    }

    public ArrayList<ProductItem> getsProductItem() {
        return sProductItem;
    }

    public void setsProductItem(ArrayList<ProductItem> sProductItem) {
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

    public ArrayList<CouponItem> getsCoupon() {
        return sCoupon;
    }

    public void setsCoupon(ArrayList<CouponItem> sCoupon) {
        this.sCoupon = sCoupon;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getTabNumber() {
        return sTabNumber;
    }

    public void setTabNumber(int tabNumber) {
        this.sTabNumber = tabNumber;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "_id='" + _id + '\'' +
                ", sUserEmail='" + sUserEmail + '\'' +
                ", sProductItem=" + sProductItem +
                ", sDate=" + sDate +
                ", sIsCoupon=" + sIsCoupon +
                ", sCoupon=" + sCoupon +
                '}';
    }
}
