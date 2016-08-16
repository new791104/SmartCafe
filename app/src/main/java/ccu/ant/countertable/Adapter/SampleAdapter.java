package ccu.ant.countertable.Adapter;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Created by Egoist on 2016/7/5.
 * TODO 這是左邊 RecycleView 的 Adapter
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public TextView contentTextView;
        public ViewHolder(View itemView){
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.id);
            contentTextView = (TextView) itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            GV.NOW_POS = position;
            //點擊列表
            Log.e("CLick","click" + getLayoutPosition());

            //點擊改變顏色
            GV.mList.getAdapter().notifyDataSetChanged();

            //滑到對應的 Fragment
            ((ViewPager)((MainActivity)mContext).findViewById(R.id.main_FragmentPager)).setCurrentItem(position);

        }
    }

    //View holder code

    private List<ShoppingItem> mShoppingItem;
    private static Context mContext;

    public SampleAdapter(Context context,List<ShoppingItem> di){
        mShoppingItem = di;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mShoppingItem.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_list_content, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    //BindViewHolder設定顯示在List上的資料
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ShoppingItem DItem = mShoppingItem.get(position);
        TextView nameTextView = viewHolder.nameTextView;
        TextView contentTextView = viewHolder.contentTextView;
        //設置List Text
        nameTextView.setText(String.valueOf(DItem.getNumber()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String dts=sdf.format(DItem.getsDate());

        String Place;
        if(DItem.getTabNumber() == -1)
            Place = "外帶";
        else if(DItem.getTabNumber() == 0)
            Place = "臨櫃";
        else
            Place = "內用" + DItem.getTabNumber();
        contentTextView.setText(dts + " " + Place);

        if(position == GV.NOW_POS){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#8B4513"));
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
                                                                               ButAct butact = new ButAct();
                                                                               butact.remove(position,v);
                                                                           }
                                                                       })
                                                                       .setNegativeButton("取消", null)
                                                                       .show();

                                                               return false;
                                                           }
                                                       }
            );
            /*****************/
        }
        else{
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#F5DEB3"));
        }

    }



}