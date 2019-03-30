package com.example.think.videodemo.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.FunnyImageBean;
import com.example.think.videodemo.R;

import java.util.List;

public class FunnyImageAdapter extends RecyclerView.Adapter<FunnyImageAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<FunnyImageBean.Result.FunnyImageItem> funnyImageItemList;
    private OnItemClickListener mItemClickListener;
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView updatetime;
        TextView content;
        ImageView image_url;
        public ViewHolder(View view){
            super(view);
            updatetime = (TextView) view.findViewById(R.id.updatetime);
            content = (TextView) view.findViewById(R.id.content);
            image_url = (ImageView) view.findViewById(R.id.image_url);
        }
    }

    public FunnyImageAdapter(Context mContext, List<FunnyImageBean.Result.FunnyImageItem> funnyImageItemList){
        this.funnyImageItemList = funnyImageItemList;
    }

    @NonNull
    @Override
    public FunnyImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_funnyimage,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunnyImageAdapter.ViewHolder viewHolder, int i) {

        viewHolder.updatetime.setText(funnyImageItemList.get(i).getUpdatetime());
        viewHolder.content.setText(funnyImageItemList.get(i).getContent());
        Glide.with(viewHolder.image_url.getContext())
                .load(funnyImageItemList.get(i).getUrl())
                .into(viewHolder.image_url);

        viewHolder.itemView.setTag(i);

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return funnyImageItemList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}