package ccu.ant.countertable.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Network_core;
import ccu.ant.countertable.Objects.CouponItem;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;
import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by Charlie on 2016/7/17.
 */
public class ButAct{
    Network_core nCore;

    public void switchList(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_switchlist, null);
/*
        MaterialSpinner spinner_place = (MaterialSpinner) dialogView.findViewById(R.id.switch_spinner_place);
        spinner_place.setItems("所有", "外帶", "內用", "臨櫃");
        spinner_place.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner spinner_isOut = (MaterialSpinner) dialogView.findViewById(R.id.switch_spinner_isOut);
        spinner_isOut.setItems("出餐", "未出餐");
        spinner_isOut.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });*/

        Spinner spinner_place = (Spinner) dialogView.findViewById(R.id.switch_spinner_place);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter palce = new ArrayAdapter(context,android.R.layout.simple_spinner_item,new String[]{"所有","外帶","內用","臨櫃"});
        //設定下拉選單的樣式
        palce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_place.setAdapter(palce);
        //設定項目被選取之後的動作
        spinner_place.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                Toast.makeText(context, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(context, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });

        Spinner spinner_isOut = (Spinner) dialogView.findViewById(R.id.switch_spinner_isOut);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter isOut = new ArrayAdapter(context,android.R.layout.simple_spinner_item,new String[]{"已出餐","未出餐"});
        //設定下拉選單的樣式
        isOut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_isOut.setAdapter(isOut);
        //設定項目被選取之後的動作
        spinner_isOut.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                Toast.makeText(context, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(context, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dlg = new AlertDialog.Builder(context)
                .setTitle("排序方式")   //dialog title
                .setView(dialogView)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() { //dialog 按鈕
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
        //setDialogWindowAttr(dlg,context,600,600);

    }

    public void add(View v) {
        final Context context = v.getContext();
        Snackbar.make(v, "新增訂單", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        nCore = new Network_core(context);

        ArrayList<ProductItem> productItems = new ArrayList<ProductItem>();
        ArrayList<CouponItem> couponItems = new ArrayList<CouponItem>();

        //DB
        ShoppingItem temp_sItem = new ShoppingItem(GV.number, "id", "現場", 0, productItems, GV.date, false, couponItems);
        nCore.shop_Addnew(temp_sItem);
        //new ShoppingItem的時候多新增了fragments，故刪之
        ShoppingItem.fragments.remove(ShoppingItem.fragments.size() - 1);

        GV.mList.getAdapter().notifyDataSetChanged();
        GV.mPager.getAdapter().notifyDataSetChanged();
    }

    public void remove(int orderArrIndex, View v){
        Context context = v.getContext();

            //DB出餐
            Network_core nCore = new Network_core(context);
            nCore.shop_Out(GV.sItem.get(GV.NOW_POS).get_id());

            //刪除左邊List的選項
            if(orderArrIndex == GV.sItem.size()-1) GV.mPager.setCurrentItem(orderArrIndex-1);
            GV.sItem.remove(orderArrIndex);

            //刪除右邊fragments
            ShoppingItem.fragments.remove(orderArrIndex);

            //刪除finish計數器
            GV.now_finish.remove(GV.NOW_POS);

            GV.mPager.getAdapter().notifyDataSetChanged();
            GV.mList.getAdapter().notifyDataSetChanged();

    }

    public void addition_coffee(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_adjable, null);

        /*****在這邊對Dialog中的物件做設定******/
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_type);
        final TextView text = (TextView) dialogView.findViewById(R.id.dialog_textView_adjType);
        final QuantityView menu_quantityView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_quantityView);
        final QuantityView menu_conView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_conView);
        final QuantityView menu_sugarView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_sugarView);
        final QuantityView menu_milkView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_milkView);
        final QuantityView menu_iceView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_iceView);

        setSpinner(context,dialogView, 700, 400,spinner,"coffee",true, text,menu_quantityView,menu_conView,menu_sugarView,menu_milkView,menu_iceView);
    }

    public void addition_drink(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_adjable, null);

        /*****在這邊對Dialog中的物件做設定******/
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_type);
        final TextView text = (TextView) dialogView.findViewById(R.id.dialog_textView_adjType);
        final QuantityView menu_quantityView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_quantityView);
        final QuantityView menu_conView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_conView);
        final QuantityView menu_sugarView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_sugarView);
        final QuantityView menu_milkView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_milkView);
        final QuantityView menu_iceView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_iceView);

        setSpinner(context,dialogView, 700, 400,spinner,"drink",true,text,menu_quantityView,menu_conView,menu_sugarView,menu_milkView,menu_iceView);
    }

    public void addition_pastry(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_unadjable, null);

        /*****在這邊對Dialog中的物件做設定******/
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_type);
        final TextView text = (TextView) dialogView.findViewById(R.id.dialog_textView_unadjType);
        final QuantityView menu_quantityView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_quantityView);
        final QuantityView menu_conView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_conView);
        final QuantityView menu_sugarView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_sugarView);
        final QuantityView menu_milkView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_milkView);
        final QuantityView menu_iceView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_iceView);

        setSpinner(context,dialogView, 700, 250,spinner,"pastry",false,text,menu_quantityView,menu_conView,menu_sugarView,menu_milkView,menu_iceView);
    }

    public void addition_meal(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_unadjable, null);

        /*****在這邊對Dialog中的物件做設定******/
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_type);
        final TextView text = (TextView) dialogView.findViewById(R.id.dialog_textView_unadjType);
        final QuantityView menu_quantityView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_quantityView);
        final QuantityView menu_conView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_conView);
        final QuantityView menu_sugarView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_sugarView);
        final QuantityView menu_milkView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_milkView);
        final QuantityView menu_iceView = (QuantityView) dialogView.findViewById(R.id.dialog_menu_iceView);

        setSpinner(context,dialogView, 700, 250,spinner,"meal",false,text,menu_quantityView,menu_conView,menu_sugarView,menu_milkView,menu_iceView);
    }

    public void alter(View v){
        final Context context = v.getContext();
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_addition, null);
        ImageButton ImageBut_coffee = (ImageButton) dialogView.findViewById(R.id.dialog_imageBut_coffee);
        ImageButton ImageBut_drink = (ImageButton) dialogView.findViewById(R.id.dialog_imageBut_drink);
        ImageButton ImageBut_pastry = (ImageButton) dialogView.findViewById(R.id.dialog_imageBut_pastry);
        ImageButton ImageBut_meal = (ImageButton) dialogView.findViewById(R.id.dialog_imageBut_meal);

        ImageBut_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButAct butact = new ButAct();
                butact.addition_coffee(view);
            }
        });

        ImageBut_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButAct butact = new ButAct();
                butact.addition_drink(view);
            }
        });

        ImageBut_pastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButAct butact = new ButAct();
                butact.addition_pastry(view);
            }
        });

        ImageBut_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButAct butact = new ButAct();
                butact.addition_meal(view);
            }
        });
        AlertDialog dlg = new AlertDialog.Builder(context)
                .setTitle("現場點餐")   //dialog title
                .setView(dialogView)
                .show();
        setDialogWindowAttr(dlg,context,600,600);
    }

    public static void setDialogWindowAttr(Dialog dlg, Context ctx, int w, int h){
        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = w;
        lp.height = h;
        dlg.getWindow().setAttributes(lp);
    }

    private void setSpinner(final Context context, final View dialogView, final int w, final int h, final Spinner spinner, String type, final boolean isAdj,
                            final TextView menu_TextView,
                            final QuantityView menu_quantityView,
                            final QuantityView menu_conView,
                            final QuantityView menu_sugarView,
                            final QuantityView menu_milkView,
                            final QuantityView menu_iceView){
        nCore = new Network_core(context);
        menu_TextView.setText(type + ":");
        /******從 DB 拿 Menu *******/
        final String[] menu_item_name_selected = {""};
        final int[] item_price = {0};
        nCore.pro_GetList(type);
        nCore.setCallback(new Network_core.netCallback() {
                              @Override
                              public String response(String response) {
                                  //final String menu_item_name_selected = new String();

                                  Gson gson = new Gson();
                                  ArrayList<ProductItem> pMenu = gson.fromJson(response, new TypeToken<List<ProductItem>>(){}.getType());

                                  final ArrayList<ProductItem> Menu = pMenu;
                                  final String[] menu_item_name = new String[Menu.size() + 1];
                                  ArrayAdapter<String> menu_list_string;

                                  menu_item_name[0] = " ";
                                  for(int i = 0;i < Menu.size();i++)
                                      menu_item_name[i + 1] = Menu.get(i).getpName();

                                  menu_list_string = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_dropdown_item,menu_item_name);

                                  //spinner
                                  spinner.setAdapter(menu_list_string);
                                  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                      @Override
                                      public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
                                          //Toast.makeText(dialogView.getContext(), "你選的是"+menu_item_name[position], Toast.LENGTH_SHORT).show();
                                          menu_item_name_selected[0] = menu_item_name[position].toString();
                                          if(position == 0)
                                              item_price[0] = 0;
                                          else
                                            item_price[0] = Menu.get(position - 1).getpPrice();
                                      }
                                      @Override
                                      public void onNothingSelected(AdapterView<?> arg0) {
                                      }
                                  });

                                  AlertDialog dlg = new AlertDialog.Builder(context)
                                          .setTitle("現場點餐")   //dialog title
                                          .setView(dialogView)
                                          .setPositiveButton("確認", new DialogInterface.OnClickListener() { //dialog 按鈕
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  ArrayList<ProductItem> pItems_list = GV.sItem.get(GV.NOW_POS).getsProductItem();
                                                  int price = item_price[0];
                                                  int quantity = menu_quantityView.getQuantity();
                                                  String sTag = new String();
                                                  if(isAdj) {
                                                      sTag = "濃度:" + menu_conView.getQuantity() + "糖分:" + menu_sugarView.getQuantity() + "奶量:" + menu_milkView.getQuantity() + "冰量:" + menu_iceView.getQuantity();
                                                      pItems_list.add(new ProductItem(menu_item_name_selected[0], "", sTag, price, quantity, ""));
                                                  }
                                                  else{
                                                      sTag = "不可調整";
                                                      pItems_list.add(new ProductItem(menu_item_name_selected[0], "", sTag, price, quantity, ""));
                                                  }
                                                  GV.sItem.get(GV.NOW_POS).setsProductItem(pItems_list);

                                                  //DB
                                                  nCore.shop_Update(GV.sItem.get(GV.NOW_POS));

                                                  GV.sItem.get(GV.NOW_POS).setDone(false);
                                                  GV.mPager.getAdapter().notifyDataSetChanged();
                                              }
                                          })
                                          .setNegativeButton("取消", new DialogInterface.OnClickListener() { //dialog 按鈕
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                              }
                                          })
                                          .show();
                                  setDialogWindowAttr(dlg,context,w,h);
                                  return null;
                              }
                          }
        );
    }
}
