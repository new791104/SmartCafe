package ccu.ant.countertable.Adapter;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ccu.ant.countertable.Activity.MainActivity;
import ccu.ant.countertable.Fragment.MyFragment;
import ccu.ant.countertable.Item.DummyItem;
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
            //點擊列表
            Log.e("CLick","click" + getLayoutPosition());
            //滑到對應的 Fragment
            ((ViewPager)((MainActivity)mContext).findViewById(R.id.main_FragmentPager)).setCurrentItem(getLayoutPosition());
        }
    }

    //View holder code

    private List<DummyItem> mDummyItem;
    private static Context mContext;

    public SampleAdapter(Context context,List<DummyItem> di){
        mDummyItem = di;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDummyItem.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_list_content, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        DummyItem dItem = mDummyItem.get(position);
        TextView nameTextView = viewHolder.nameTextView;
        TextView contentTextView = viewHolder.contentTextView;
        nameTextView.setText(dItem.getId());
        contentTextView.setText(dItem.getName());

    }



}