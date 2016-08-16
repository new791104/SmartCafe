package ccu.ant.countertable.Fragment;


import android.content.Context;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ccu.ant.countertable.Activity.ButAct;
import ccu.ant.countertable.Adapter.orderListAdapter;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Network_core;
import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;

public class MyFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "HOME_PageFragment";
    Context mContext;

    //物件
    private TextView mIndexText;
    private TextView sUserInfo;
    private TextView sOrderPrice;
    private TextView sOrderNumber;
    private TextView sOrderCoupon;
    private TextView sTalPrice;
    private ImageView usr_ImageView;
    private ImageButton donebut;
    //傳遞過來的值
    private int frag_number;
    private int frag_sid;
    private String frag_uname;
    private String frag_uemail;
    private String frag_upic;
    private int frag_items_size;
    private String[] frag_items_name;
    private String frag_sdate;
    private int frag_talprice;
    private int frag_tabNumber;
    private boolean frag_iscoupon;
    private String[] frag_couponid;

    //與Activity溝通用
    OnHeadlineSelectedListener mCallback;
    public static int finishing = 0;
    //
    public static RecyclerView sList;

    //初始化時傳進來的參數
    public static final MyFragment newInstance(){
        MyFragment f = new MyFragment();
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    // This makes sure that the container activity has implemented
    // the callback interface. If not, it throws an exception.
        /*try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            e.getMessage();
        }*/
    }

    //在 onCreate的時候把傳入的參數取出來
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container,
                false);

        //sList.setAdapter(new orderListAdapter(frag_items_name));

        return rootView;
        //return inflater.inflate(R.layout.fragment_my, container, false);
    }

    //這邊是View Created的時候，可以處理內部的物件了
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Network_core nCore = new Network_core(view.getContext());
        int i,j;
        ShoppingItem DItem = GV.sItem.get(GV.NOW_POS);
        String Place;
        if(DItem.getTabNumber() == -1)
            Place = "外帶";
        else if(DItem.getTabNumber() == 0)
            Place = "臨櫃點餐";
        else
            Place = "內用, " + "桌號:" + DItem.getTabNumber();

        mContext = view.getContext();

        //---畫面物件初始化---
        //項目列表
        sList = (RecyclerView) view.findViewById(R.id.Orders_RecyclerView);
        sList.setLayoutManager(new LinearLayoutManager(getActivity()));
        sList.setAdapter(new orderListAdapter(DItem));

        //使用者資訊
        //sUserInfo = (TextView) view.findViewById(R.id.Fragment_User_Detail);
        /*******以會員Email查詢他的個人資料*******/
        //sUserInfo.setText(DItem.getsUserName());
        //nCore.mem_Login();
        /**************/

        //訂單資訊
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        String date_string = formatter.format(DItem.getsDate());
        sOrderNumber = (TextView) view.findViewById(R.id.Fragment_User_No);
        sOrderNumber.setText("Number: " + DItem.getNumber()
                             + "\nsid: " + DItem.get_id().substring(0,5)
                             + "\n" + DItem.get_id().substring(6)
                             + "\nPlace: " + Place
                             + "\nDate: " + date_string);

        //訂單優惠卷
        String coupons_string = new String();
        String coupon_value_string = new String();
        String order_price_string = new String();
        int talPrice = 0,tmpPrice;

        if(DItem.issIsCoupon()) {
            for (CouponItem coupon : DItem.getsCoupon()) {
                coupons_string += coupon._id + " " + coupon.cType + " " + coupon.cContent + " " + coupon.cValue + "\n";
                if (coupon.cType == "point") {
                    coupon_value_string += "-" + coupon.cValue;
                    talPrice -= coupon.cValue;
                } else {
                    coupon_value_string += "*" + coupon.cValue;
                    talPrice *= coupon.cValue;
                }
            }
        }
        sOrderCoupon = (TextView) view.findViewById(R.id.Fragment_User_Coupon);
        sOrderCoupon.setText(coupons_string);

        //訂單價錢
        ArrayList<ProductItem> products = DItem.getsProductItem();
        for(i = 0;i < products.size();i++){
            tmpPrice = products.get(i).getpPrice() * products.get(i).getsPCS();
            talPrice += tmpPrice;
            if(i == 0)
                order_price_string += tmpPrice;
            else
                order_price_string += "+" + tmpPrice;
        }

        sOrderPrice = (TextView) view.findViewById(R.id.Fragment_User_Prcie);
        sOrderPrice.setText(order_price_string + "\n" + coupon_value_string + "\n");

        sTalPrice = (TextView) view.findViewById(R.id.Fragment_User_talPrcie);
        sTalPrice.setText("=" + talPrice);

        //圖片初始化
        //usr_ImageView = (ImageView) view.findViewById(R.id.Fragment_User_pic);
        //Glide.with(getActivity()).load(DItem.getuPic_url()).asBitmap().centerCrop().placeholder(R.drawable.sample_footer_loading_progress).into(mImageView);
        //載入使用者圖片
        //Glide.with(getActivity()).load(DItem.getuPic_url()).asBitmap().centerCrop().into(usr_ImageView);

        //Done Button
        donebut = (ImageButton) view.findViewById(R.id.DoneBut);
        donebut.setOnClickListener(this);
        if(DItem.isDone())
            donebut.setImageResource(R.drawable.check_mark_on_cloud_white);
        else
            donebut.setImageResource(R.drawable.check_mark_on_cloud_black);
    }


    //點擊事件
    @Override
    public void onClick(View v) {
        ButAct butact = new ButAct();
        switch (v.getId()){
            case R.id.DoneBut:
                if(GV.sItem.get(GV.NOW_POS).isDone()) {
                    butact.remove(GV.NOW_POS, v);
                }
                break;
        }
    }



}
