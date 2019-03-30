package com.example.think.videodemo.ui.Adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener{


    private List<CategoryItem> categoryBeanList;
    private OnItemClickListener mItemClickListener;
    @Override
    public void onClick(View v) {
        if (mItemClickListener != null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView container;
        TextView textView;
        public ViewHolder(View view){
            super(view);
           container = (ImageView) view.findViewById(R.id.container);
           textView = (TextView) view.findViewById(R.id.textview);
        }
    }
    public CategoryAdapter(List<CategoryItem> list){
        this.categoryBeanList = list;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
        public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int i) {
            Glide.with(viewHolder.container.getContext())
                    .load(categoryBeanList.get(i).getBgPicture())
                    .into(viewHolder.container);
            viewHolder.textView.setText("#" + categoryBeanList.get(i).getName());

            viewHolder.itemView.setTag(i);

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return categoryBeanList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
