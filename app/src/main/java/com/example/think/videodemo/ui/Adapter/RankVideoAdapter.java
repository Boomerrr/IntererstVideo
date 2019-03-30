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
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.R;

import java.util.List;

public class RankVideoAdapter extends RecyclerView.Adapter<RankVideoAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<RankBean.DetailRank> detailVideos;
    private OnItemClickListener mItemClickListener;
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_rank_video;
        TextView title_rank_video;
        TextView category_rank_video;
        TextView num_rank;

        public ViewHolder(View view){

            super(view);
            image_rank_video = (ImageView) view.findViewById(R.id.image_rank_video);
            title_rank_video = (TextView) view.findViewById(R.id.title_rank_video);
            category_rank_video = (TextView) view.findViewById(R.id.category_rank_video);
            num_rank = (TextView) view.findViewById(R.id.rank_num);
        }
    }

    public RankVideoAdapter(Context mContext, List<RankBean.DetailRank> detailVideos){
        this.detailVideos = detailVideos;
    }

    @NonNull
    @Override
    public RankVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rank_video,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankVideoAdapter.ViewHolder viewHolder, int i) {

        String rankNum = String.valueOf(i+1);

        Glide.with(viewHolder.image_rank_video.getContext())
                .load(detailVideos.get(i).getDate().getCover().getFeed())
                .into(viewHolder.image_rank_video);

        viewHolder.title_rank_video.setText(detailVideos.get(i).getDate().getTitle());

        viewHolder.category_rank_video.setText("#" + detailVideos.get(i).getDate().getCategory());

        viewHolder.num_rank.setText(rankNum);

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