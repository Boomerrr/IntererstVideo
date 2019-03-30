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
import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainVideoAdapter extends RecyclerView.Adapter<MainVideoAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<MainVideoBean.DetailVideo> detailVideos;
    private OnItemClickListener mItemClickListener;
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView video_head;
        ImageView image_main_video;
        TextView video_name;
        TextView video_description;
        public ViewHolder(View view){

            super(view);
            image_main_video = (ImageView) view.findViewById(R.id.image_main_video);
            video_head = (CircleImageView) view.findViewById(R.id.video_head);
            video_name = (TextView) view.findViewById(R.id.video_name);
            video_description = (TextView) view.findViewById(R.id.video_description);
        }
    }

    public MainVideoAdapter(Context mContext, List<MainVideoBean.DetailVideo> detailVideos){
        this.detailVideos = detailVideos;
    }

    @NonNull
    @Override
    public MainVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_video,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainVideoAdapter.ViewHolder viewHolder, int i) {

        Glide.with(viewHolder.image_main_video.getContext())
                .load(detailVideos.get(i).getData().getCover().getFeed())
                .into(viewHolder.image_main_video);

        Glide.with(viewHolder.video_head.getContext())
                .load(detailVideos.get(i).getData().getAuthor().getIcon())
                .into(viewHolder.video_head);
        viewHolder.video_name.setText(detailVideos.get(i).getData().getTitle());
        viewHolder.video_description.setText(detailVideos.get(i).getData().getDescription());
        viewHolder.itemView.setTag(i);

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    @Override
    public int getItemCount() {
        return detailVideos.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}

