package ccu.ant.countertable.Item;



import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Fragment.MyFragment;

/**
 * Created by Egoist on 2016/7/5.
 * TODO Dummy Item 你自訂義的物件，在這邊要同時初始化Fragment並放進一個List中
 */
public class DummyItem {
    public int number;
    private String id;
    private String orders;
    private String price;
    private String place;
    private String take_number;
    private String request;
    private String name;
    private String pic_url;

    //DummyItem對應的 Fragment 列表 (新增左邊選項時一起新增右邊頁面)
    public static List<Fragment> fragments = new ArrayList<>();


    public DummyItem(int num, String name,  String orders, String price, String request, String place,String take_number) {
        this.number = num;
        this.id = Integer.toString(num);
        this.name = name;
        this.orders = orders;
        this.price = price;
        this.request = request;
        this.place = place;
        this.take_number = take_number;


        //與 MyFragment 的接口，newInstance 會實體化並回傳一個 Fragment。
        //而 fragment 陣列被宣告為static為全域變數
        fragments.add(MyFragment.newInstance(num,name,orders,price,request,place,take_number));
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTake_number() {
        return take_number;
    }

    public void setTake_number(String take_number) {
        this.take_number = take_number;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Fragment> getFragments() {
        return fragments;
    }

    public static void setFragments(List<Fragment> fragments) {
        DummyItem.fragments = fragments;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
