package com.example.think.videodemo.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.R;

import java.util.List;

/**
 *
 *  广告牌Adapter
 * 已弃用
 *
 */



public class AdvertiseBroadAdapter extends RecyclerView.Adapter<AdvertiseBroadAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<HotVideoBean.HotABean.itemBean> hotVideoBeanList;
    private OnItemClickListener1 mItemClickListener;
    @Override
    public void onClick(View v) {
        if (mItemClickListener != null){
            mItemClickListener.onItemClick1((Integer) v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_advertisse;
        TextView text_advertise;
        public ViewHolder(View view){
            super(view);
            image_advertisse = (ImageView) view.findViewById(R.id.image_advertise);
            text_advertise = (TextView) view.findViewById(R.id.adv_Description);
        }
    }
    public AdvertiseBroadAdapter(Context mContext, List<HotVideoBean.HotABean.itemBean> hotABeanList){
        this.hotVideoBeanList = hotABeanList;
        hotVideoBeanList.remove(0);
    }

    @NonNull
    @Override
    public AdvertiseBroadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_advertise,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertiseBroadAdapter.ViewHolder viewHolder, int i) {

            Glide.with(viewHolder.image_advertisse.getContext())
                .load(hotVideoBeanList.get(i).getData().getCover().getFeed())
                .into(viewHolder.image_advertisse);
            viewHolder.text_advertise.setText(hotVideoBeanList.get(i).getData().getTitle());
            viewHolder.itemView.setTag(i);

    }
    public interface OnItemClickListener1{
        void onItemClick1(int position);
    }
    @Override
    public int getItemCount() {
        return hotVideoBeanList.size();
    }

    public void setItemClickListener(OnItemClickListener1 itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
