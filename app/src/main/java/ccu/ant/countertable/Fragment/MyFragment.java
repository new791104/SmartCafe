package ccu.ant.countertable.Fragment;


import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.os.UserManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Item.Car;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;
import okhttp3.Call;

public class MyFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "HOME_PageFragment";
    Context mContext;

    //物件
    private TextView mIndexText;
    private TextView sUserInfo;
    private TextView sOrderNumber;
    private ImageView mImageView;
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
    //Fragment裡面的List，用來顯示餐點項目
    private RecyclerView sList;

    //初始化時傳進來的參數
    public static final MyFragment newInstance(int num, int sid, String uname, String uemail, String upic, ProductItem[] items, Date sdate, int talprice, int tabnum, boolean iscoupon, String[] couponid){
        MyFragment f = new MyFragment();
        Bundle bd = new Bundle();
        int i;

        bd.putInt("Order_Number", num);
        bd.putInt("User_sid", sid);
        bd.putString("User_Name", uname);
        bd.putString("User_Email", uemail);
        bd.putString("User_Pic", upic);
        for(i = 0;i < items.length;i++)
            bd.putString("Order_Name" + i, items[i].getpName());
        bd.putInt("Order_size", i);
        bd.putString("Date", sdate.toString());
        bd.putInt("talPrice", talprice);
        bd.putInt("tabNumber", tabnum);
        bd.putBoolean("isCoupon", iscoupon);
        bd.putStringArray("CouponId", couponid);


        f.setArguments(bd);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    // This makes sure that the container activity has implemented
    // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            e.getMessage();
        }
    }

    //在 onCreate的時候把傳入的參數取出來
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i;
        frag_number = getArguments().getInt("Order_Number");
        frag_sid = getArguments().getInt("Usr_sid");
        frag_uname = getArguments().getString("User_Name");
        frag_uemail = getArguments().getString("User_Email");
        frag_upic = getArguments().getString("User_Pic");
        frag_items_size = getArguments().getInt("Order_size");

        frag_items_name = new String[frag_items_size];
        for(i = 0;i < frag_items_size;i++)
            frag_items_name[i] = getArguments().getString("Order_Name" + i);

        frag_talprice = getArguments().getInt("User_talPrice");
        frag_tabNumber = getArguments().getInt("tabNumber");
        frag_iscoupon = getArguments().getBoolean("isCoupon");
        frag_couponid = getArguments().getStringArray("CouponId");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }



    //這邊是View Created的時候，可以處理內部的物件了
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShoppingItem DItem = GV.sItem.get(GV.NOW_POS);
        String Place = DItem.getTabNumber() == 0 ? "外帶" : "內用" + DItem.getTabNumber();
        mContext = view.getContext();

        //---畫面物件初始化---
        sUserInfo = (TextView) view.findViewById(R.id.Fragment_User_Detail);
        sUserInfo.setText(DItem.getsUserName());
        sOrderNumber = (TextView) view.findViewById(R.id.Fragment_User_No);
        sOrderNumber.setText("Number: " + DItem.getNumber()
                             + "\nsid: " + DItem.getsID()
                             + "\nPlace: " + Place);

        //圖片初始化
        mImageView = (ImageView) view.findViewById(R.id.Fragment_User_pic);
        //Glide.with(getActivity()).load(DItem.getuPic_url()).asBitmap().centerCrop().placeholder(R.drawable.sample_footer_loading_progress).into(mImageView);
        //載入使用者圖片
        Glide.with(getActivity()).load(DItem.getuPic_url()).asBitmap().centerCrop().into(mImageView);

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

}
