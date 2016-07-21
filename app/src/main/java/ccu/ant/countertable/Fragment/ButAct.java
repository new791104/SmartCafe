package ccu.ant.countertable.Fragment;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Item.Car;
import ccu.ant.countertable.Objects.ShoppingItem;
import okhttp3.Call;

/**
 * Created by Charlie on 2016/7/17.
 */
public class ButAct{

    public void update(){
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
                        List<Car> mCar  =  new Gson().fromJson(JSON,new TypeToken<List<Car>>(){}.getType());    //Gson是一個用來處理JSON格式的第三方套件

                        //更新內容
                        GV.sItem.get(GV.NOW_POS).setsUserName(mCar.get(GV.NOW_POS).getName());

                        //mIndexText.setText(response);
                        //載入圖片
                        GV.sItem.get(GV.NOW_POS).setuPic_url("http://imgur.com/gxqSAwJ.jpg");

                        GV.mPager.getAdapter().notifyDataSetChanged();
                    }
                });
    }

    public void add(View v) {
        Snackbar.make(v, "Loading...", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        //Log.e("number: ", ShoppingItem.fragments.get(User_Number-1).toString());
        GV.number++;

        GV.sItem.add(new ShoppingItem(GV.number, 20160721, "熊本熊", "uccu@gmail.com", GV.pic_url, GV.dummy.pitem, GV.date, 150, 5, true, GV.couponid));
        GV.mList.getAdapter().notifyDataSetChanged();
        GV.mPager.getAdapter().notifyDataSetChanged();
    }

    public void remove(int orderArrIndex){
        if(GV.sItem.size() > 1) {
            /*if (ShoppingItem.fragments.listIterator(data_int).next() != null)
                mPager.setCurrentItem(data_int + 1);*/
            //if(sItem.get(sItem.size()) != null) {
            //刪除左邊List的選項
            if(orderArrIndex == GV.sItem.size()-1) GV.mPager.setCurrentItem(orderArrIndex-1);
            GV.sItem.remove(orderArrIndex);
            //mPager.setCurrentItem(data_int + 1);
            //刪除右邊fragments
            ShoppingItem.fragments.remove(orderArrIndex);
            GV.mPager.getAdapter().notifyDataSetChanged();
            Log.e("flags:","2");

            //mList.getAdapter().notifyItemRemoved(orderArrIndex);
            GV.mList.getAdapter().notifyDataSetChanged();

            Log.e("flags:","3");

            Log.e("Array:",GV.sItem.toArray().toString());
            Log.e("fragments.size", "" + ShoppingItem.fragments.size());
            //Log.e("fragments_ID",""+ShoppingItem.fragments.);
            Log.e("debug:", ShoppingItem.fragments.toString());
        }
    }
}
