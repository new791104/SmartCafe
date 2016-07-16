package ccu.ant.countertable.Fragment;


import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.os.UserManagerCompat;
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
import java.util.List;

import ccu.ant.countertable.Activity.MainActivity;
import ccu.ant.countertable.Item.Car;
import ccu.ant.countertable.Item.DummyItem;
import ccu.ant.countertable.R;
import okhttp3.Call;

public class MyFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "HOME_PageFragment";
    Context mContext;

    //物件
    private TextView mIndexText;
    private ImageView mImageView;
    //傳遞過來的值
    private int Frag_Number;
    private String Frag_Name ;
    private String Frag_Orders ;
    private String Frag_Price ;
    private String Frag_Request ;
    private String Frag_Place ;
    private String Frag_Take_Number ;
    //與Activity溝通用
    OnHeadlineSelectedListener mCallback;


    //初始化時傳進來的參數
    public static final MyFragment newInstance(int num, String name, String orders, String price, String request, String place, String take_number){
        MyFragment f = new MyFragment();

        Bundle bd = new Bundle();

        bd.putInt("User_Number", num);
        bd.putString("User_Name", name);
        bd.putString("Orders", orders);
        bd.putString("Price", price);
        bd.putString("Request", request);
        bd.putString("Place", place);
        bd.putString("Take_Number", take_number);
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
        Frag_Number = getArguments().getInt("User_Number");
        Frag_Name = getArguments().getString("User_Name");
        Frag_Orders = getArguments().getString("Order");
        Frag_Price = getArguments().getString("Price");
        Frag_Request = getArguments().getString("Request");
        Frag_Place = getArguments().getString("Place");
        Frag_Take_Number = getArguments().getString("Take_Number");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }



    //這邊是View Created的時候，可以處理內部的物件了
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DummyItem DItem = MainActivity.sItem.get(MainActivity.NOW_POS);
        mContext = view.getContext();
        //---畫面物件初始化---
        mIndexText = (TextView) view.findViewById(R.id.Fragment_text);
        mIndexText.setText(
                DItem.getId()+"/"+ DItem.getPrice());

        //圖片初始化
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(getActivity()).load(DItem.getPic_url()).asBitmap().centerCrop().placeholder(R.drawable.sample_footer_loading_progress).into(mImageView);

        FloatingActionButton delbut = (FloatingActionButton) getView().findViewById(R.id.delbut);
        delbut.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e("test: ", "button success!");
                if(!DummyItem.fragments.isEmpty()) {
                    //Log.e("number: ", DummyItem.fragments.get(User_Number-1).toString());
                    mCallback.onRemoveSelected(MainActivity.NOW_POS);
                }
            }
        });
        FloatingActionButton master_add = (FloatingActionButton) getView().findViewById(R.id.master_add);
        master_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(!DummyItem.fragments.isEmpty()) {
                    //Log.e("number: ", DummyItem.fragments.get(User_Number-1).toString());
                    mCallback.onAddSelected();
                }
            }
        });

        /**************************/
        FloatingActionButton master_update = (FloatingActionButton) getView().findViewById(R.id.updatebut);
        master_update.setOnClickListener(this);

    }


    //點擊事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updatebut:

                OkHttpUtils
                        .post()
                        .url("http://www.taiwanbus.tw/app_api/SP_PredictionTime2.ashx")
                        .addParams("routeNo","7304")
                        .addParams("branch","0")
                        .addParams("goBack","1")
                        .addParams("Lang","")
                        .addParams("Source","m")
                        .build()
                        .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e) {

                                }

                                @Override
                                public void onResponse(String response) {

                                    String JSON = response;
                                    List<Car> mCar  =  new Gson().fromJson(JSON,new TypeToken<List<Car>>(){}.getType());

                                    //更新內容
                                    MainActivity.sItem.get(MainActivity.NOW_POS).setPrice(mCar.get(MainActivity.NOW_POS).getName());

                                    //mIndexText.setText(response);
                                    //載入圖片
                                    MainActivity.sItem.get(MainActivity.NOW_POS).setPic_url("http://imgur.com/gxqSAwJ.jpg");



                                    MainActivity.mPager.getAdapter().notifyDataSetChanged();

                                }
                        });
                break;

        }

    }

}
