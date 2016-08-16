package ccu.ant.countertable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.Objects.UserItem;
import okhttp3.Call;
import okhttp3.MediaType;

public class Network_core{

    private static Context context;
    private OkHttpUtils mokHttpClient;
    private RequestCall mcall;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String SERVER_HOST = "http://140.123.92.85:8080";

    //!!注意 使用前需要實例化 Network_core()
    public Network_core(Context context)
    {
        this.context = context;
    }

    //取消目前的Call請求
    public void Cancel_network(){
        if(mcall!=null)
            mcall.cancel();
    }

    /*
    * TODO 會員系統操作_API ##############################
    * */
    /**
     * 註冊會員
     * @param mUI 傳入整個 UserItem
     * @return    void
     */
    public void mem_Register(UserItem mUI){
        String url = SERVER_HOST + "/mem_add";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mUI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Mem Add:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 會員資料更新
     * @param mUI 傳入整個 UserItem
     * @return    void
     */
    public void mem_Update(UserItem mUI){
        String url = SERVER_HOST + "/mem_update";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mUI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Mem Update:", response);
            }
        });
    }

    /**
     * 查詢會員詳細資訊 (登入)
     * @param uEmail 會員帳號
     * @param uPassword 會員密碼
     * @return    void
     */
    public void mem_Login(final String uEmail, String uPassword){
        String url = SERVER_HOST + "/mem_get";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("uEmail",uEmail)
                .addParams("uPassword",uPassword)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Mem Login:", response);
                mCallback.response(response);
                //伺服器回傳 Json to App Object 範例
                /*Gson gson = new Gson();
                UserItem userItem = gson.fromJson(response,UserItem.class);
                Log.e("UserData ",userItem.getuImgurl());*/
            }
        });
    }
    /**
     * 列舉所有用戶
     * @return    void
     */
    public void mem_FindAll(){
        String url = SERVER_HOST + "/mem_findAll";
        mcall = mokHttpClient
                .post()
                .url(url)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Mem Login:", response);
                mCallback.response(response);
                //TODO 伺服器回傳 Json to App Object 範例
                /*Gson gson = new Gson();
                ArrayList<UserItem> userItem = gson.fromJson(response, new TypeToken<List<UserItem>>(){}.getType());
                mMemberAdapter.setNewData(userItem);*/
            }
        });
    }
    /**
     * 刪除會員 (需管理員權限)
     * @param uEmail 會員帳號
     * @param ADMIN_PASSWORD 管理員密碼
     * @return    void
     */
    public void mem_Remove(String uEmail,String ADMIN_PASSWORD){
        String url = SERVER_HOST + "/mem_remove";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("uEmail",uEmail)
                .addParams("ADMIN_PASSWORD",ADMIN_PASSWORD)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Mem Remove:", response);
                mCallback.response(response);
            }
        });
    }

     /*
    * TODO 產品管理操作_API ##############################
    * */
    /**
     * 新增產品
     * @param mPI 傳入整個 ProductItem
     * @return    void
     */
     public void pro_Addnew(ProductItem mPI){
         String url = SERVER_HOST + "/product_add";
         mcall = mokHttpClient
                 .postString()
                 .url(url)
                 .content(new Gson().toJson(mPI)).mediaType(JSON)
                 .build();
         mcall.execute(new StringCallback() {
             @Override
             public void onError(Call call, Exception e) {
             }
             @Override
             public void onResponse(String response) {
                 Log.e("Product Add:", response);
                 mCallback.response(response);
             }
         });
     }

    /**
     * 枚舉產品列表
     * @param pType 產品分類 ("coffee", "drink", "pastry","meal","all")
     * @return    void
     */
    public void pro_GetList(String pType){
        String url = SERVER_HOST + "/product_getlist";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("pType",pType)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Product GetList:", response);
                mCallback.response(response);
            }
        });
    }

    /**
     * 查詢單一產品
     * @param pName 產品名稱 (ex."起司里肌可頌")
     * @return    void
     */
    public void pro_Find(String pName){
        String url = SERVER_HOST + "/product_find";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("pName",pName)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Product FindOne:", response);
                mCallback.response(response);
            }
        });
    }

    /**
     * 刪除產品 (需管理員權限)
     * @param pName 產品名稱 (ex."起司里肌可頌")
     * @param ADMIN_PASSWORD 管理員密碼
     * @return    void
     */
    public void pro_Remove(String pName,String ADMIN_PASSWORD){
        String url = SERVER_HOST + "/product_remove";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("pName",pName)
                .addParams("ADMIN_PASSWORD",ADMIN_PASSWORD)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Product Remove:", response);
            }
        });
    }

    /*
    * TODO 訂單操作_API ##############################
    * */
    /**
     * 新增訂單
     * @param mSI 傳入整個 ShoppingItem
     * @return    void
     */
    public void shop_Addnew(ShoppingItem mSI){
        String url = SERVER_HOST + "/shopping_add";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mSI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping Add:", response);
            }
        });
    }


    /**
     * 枚舉指定用戶<未出餐>的目前訂單
     * @param sUserEmail 下單者Email
     * @return    void
     */
    public void shop_GetList(String sUserEmail){
        String url = SERVER_HOST + "/shopping_getList";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("sUserEmail",sUserEmail)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping ListGet:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉指定用戶<已出餐>的歷史訂單
     * @param sUserEmail 下單者Email
     * @return    void
     */
    public void shop_GetList_Out(String sUserEmail){
        String url = SERVER_HOST + "/shopping_getList_Out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("sUserEmail",sUserEmail)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping_Out ListGet:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉所有 目前訂單(未出餐)
     * @return    void
     */
    public void shop_FindAll(){
        String url = SERVER_HOST + "/shopping_find";
        mcall = mokHttpClient
                .post()
                .url(url)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping List:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉所有 歷史訂單(已出餐)
     * @return    void
     */
    public void shop_FindAll_out(){
        String url = SERVER_HOST + "/shopping_find_out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping_Out List:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 更新訂單
     * @param mSI 傳入整個 ShoppingItem
     * @return    void
     */
    public void shop_Update(ShoppingItem mSI){
        String url = SERVER_HOST + "/shopping_update";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mSI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping Update:", response);
            }
        });
    }
    /**
     * 移除訂單 (_id,sUserEmail) (需確認是否為下定者，直接刪掉 不再紀錄)
     * @param _id 訂單ID
     * @param sUserEmail 下定者Email
     * @return    void
     */
    public void shop_Remove(String _id,String sUserEmail){
        String url = SERVER_HOST + "/shopping_remove";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("_id",_id)
                .addParams("sUserEmail",sUserEmail)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping Remove:", response);
            }
        });
    }
    /**
     * 出餐 (_id) (該訂單會從現存訂單中刪除，移到歷史訂單中(垃圾桶))
     * @param _id 訂單ID
     * @return    void
     */
    public void shop_Out(String _id){
        String url = SERVER_HOST + "/shopping_out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("_id",_id)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Shopping Out:", response);
            }
        });
    }


    /*
    * TODO 優惠券_API ##############################
    * */
    /**
     * 新增優惠券
     * @param mCI 傳入整個 CouponItem
     * @return    void
     */
    public void coup_Addnew(CouponItem mCI){
        String url = SERVER_HOST + "/coupon_add";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mCI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon Add:", response);
                mCallback.response(response);
            }
        });
    }


    /**
     * 枚舉指定用戶<未使用>的優惠券
     * @param cOwner 擁有者Email
     * @return    void
     */
    public void coup_GetList(String cOwner){
        String url = SERVER_HOST + "/coupon_getList";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("cOwner",cOwner)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon ListGet:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉指定用戶<已使用or過期>的優惠券
     * @param cOwner 擁有者Email
     * @return    void
     */
    public void coup_GetList_Out(String cOwner){
        String url = SERVER_HOST + "/coupon_getList_Out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("cOwner",cOwner)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon_Out ListGet:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉所有 <未使用>的優惠券
     * @return    void
     */
    public void coup_FindAll(){
        String url = SERVER_HOST + "/coupon_find";
        mcall = mokHttpClient
                .post()
                .url(url)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon List:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 枚舉所有 <已使用or過期>的優惠券
     * @return    void
     */
    public void coup_FindAll_out(){
        String url = SERVER_HOST + "/coupon_find_out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon_Out List:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 更新優惠券
     * @param mCI 傳入整個 CouponItem
     * @return    void
     */
    public void coup_Update(CouponItem mCI){
        String url = SERVER_HOST + "/coupon_update";
        mcall = mokHttpClient
                .postString()
                .url(url)
                .content(new Gson().toJson(mCI)).mediaType(JSON)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon Update:", response);
            }
        });
    }
    /**
     * 移除優惠券 (_id) (直接刪掉 不再紀錄)
     * @param _id 優惠券ID
     * @return    void
     */
    public void coup_Remove(String _id){
        String url = SERVER_HOST + "/coupon_remove";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("_id",_id)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon Remove:", response);
                mCallback.response(response);
            }
        });
    }
    /**
     * 使用優惠券 (_id) <會搬移到過期優惠券DB>
     * @param _id 訂單ID
     * @return    void
     */
    public void coup_Out(String _id){
        String url = SERVER_HOST + "/coupon_out";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("_id",_id)
                .build();
        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Log.e("Coupon Out:", response);
                mCallback.response(response);
            }
        });
    }


    /*
    * CallBack
    * */
    private netCallback mCallback;

    public interface netCallback{
        public abstract String response(String response);
    }

    public void setCallback(netCallback callback){
        this.mCallback =callback;
    }




}
