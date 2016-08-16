package ccu.ant.countertable.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ccu.ant.countertable.Activity.ButAct;
import ccu.ant.countertable.Activity.MainActivity;
import ccu.ant.countertable.Fragment.MyFragment;
import ccu.ant.countertable.Global.GV;
import ccu.ant.countertable.Network_core;
import ccu.ant.countertable.Objects.ProductItem;
import ccu.ant.countertable.Objects.ShoppingItem;
import ccu.ant.countertable.R;

/**
 * Created by Charlie on 2016/7/22.
 */
public class orderListAdapter extends RecyclerView.Adapter<orderListAdapter.orderViewHolder> {

    private ProductItem[] productItems;
    private ArrayList<ProductItem> pItems;
    private String pName;
    private int pPrice;
    private int sPCs;
    private String sTag;
    public int finishing;
    private ShoppingItem sDItem;


    public int getFinishing() {
        return finishing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }

    public class orderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView order_name_TextView;
        public TextView order_tag_TextView;
        public TextView order_count_TextView;
        public TextView order_price_TextView;

        public orderViewHolder(View itemView){
            super(itemView);

            order_name_TextView = (TextView) itemView.findViewById(R.id.order_name);
            order_tag_TextView = (TextView) itemView.findViewById(R.id.order_tag);
            order_count_TextView = (TextView) itemView.findViewById(R.id.order_count);
            order_price_TextView = (TextView) itemView.findViewById(R.id.order_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();

            Log.e("pItems.size()",""+pItems.size());
            if(pItems.get(position).isFinish) {
                pItems.get(position).isFinish = false;
                /*sDItem.finishing--;
                Log.e("fin--",""+ sDItem.finishing);*/
                GV.now_finish.set(GV.NOW_POS,GV.now_finish.get(GV.NOW_POS)-1);
                Log.e("fin--",""+ GV.now_finish.get(GV.NOW_POS));
            }
            else{
                pItems.get(position).isFinish = true;
                /*sDItem.finishing++;
                Log.e("fin++",""+sDItem.finishing);*/
                GV.now_finish.set(GV.NOW_POS,GV.now_finish.get(GV.NOW_POS)+1);
                Log.e("fin++",""+ GV.now_finish.get(GV.NOW_POS));
            }
            //GV.sList.get(GV.NOW_POS).getAdapter().notifyDataSetChanged();
            if(GV.now_finish.get(GV.NOW_POS) >= pItems.size()) {
                sDItem.isDone = true;
                //MyFragment.donebut.setBackgroundResource(R.drawable.done);
                Log.e("fin: ",""+sDItem.finishing);
            }
            else {
                sDItem.isDone = false;
                //MyFragment.donebut.setBackgroundResource(R.drawable.undone);
                Log.e("unfin: ",""+sDItem.finishing);
            }
            //MyFragment.sList.getAdapter().notifyDataSetChanged();
            GV.mPager.getAdapter().notifyDataSetChanged();
        }
    }

    public orderListAdapter(ShoppingItem DItem){
        sDItem = DItem;
        //sDItem.finishing = 0;
        pItems = DItem.getsProductItem();
        GV.now_finish.add(0);
        //
        //finishing = DItem.getFinishing();
    }

    @Override
    public orderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.order_list_content, parent, false);

        orderViewHolder viewHolder = new orderViewHolder(contactView);

        return viewHolder;*/

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.order_list_content, parent, false);
        orderViewHolder holder = new orderViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final orderViewHolder viewHolder, int position) {
        ProductItem pItem = pItems.get(position);

        //設置List Text
        viewHolder.order_name_TextView.setText(pItem.getpName());
        viewHolder.order_tag_TextView.setText(pItem.getsTag());
        viewHolder.order_count_TextView.setText(String.valueOf(pItem.getsPCS()));
        viewHolder.order_price_TextView.setText(String.valueOf(pItem.getpPrice()));

        /***長按刪除item***/
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                                                       @Override
                                                       public boolean onLongClick(final View v) {
                                                           final Context context = v.getContext();
                                                           final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_check_delete_sitem, null);

                                                           AlertDialog dlg = new AlertDialog.Builder(context)
                                                                   .setTitle("確認刪除")   //dialog title
                                                                   .setView(dialogView)
                                                                   .setPositiveButton("確認", new DialogInterface.OnClickListener() { //dialog 按鈕
                                                                       @Override
                                                                       public void onClick(DialogInterface dialog, int which) {
                                                                           int pos = viewHolder.getLayoutPosition();
                                                                           Network_core nCore = new Network_core(v.getContext());

                                                                           removeData(pos);
                                                                           nCore.shop_Update(GV.sItem.get(GV.NOW_POS));

                                                                       }
                                                                   })
                                                                   .setNegativeButton("取消", null)
                                                                   .show();

                                                           return false;
                                                       }
                                                   }
        );
        /*****************/

        if(pItem.isFinish){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#8B4513"));
        }
        else{
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#F5DEB3"));
        }

    }

    public void removeData(int position)
    {
        pItems.remove(position);
        GV.sItem.get(GV.NOW_POS).setsProductItem(pItems);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return pItems.size();
    }
}
