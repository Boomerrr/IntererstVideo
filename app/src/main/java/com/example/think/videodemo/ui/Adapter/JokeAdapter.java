package com.example.think.videodemo.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.videodemo.Bean.JokeBean;
import com.example.think.videodemo.R;

import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<JokeBean.Result.JokeItem> jokeItemList;
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
        public ViewHolder(View view){
            super(view);
            updatetime = (TextView) view.findViewById(R.id.updatetime);
            content = (TextView) view.findViewById(R.id.content);
        }
    }

    public JokeAdapter(Context mContext, List<JokeBean.Result.JokeItem> jokeItemList){
        this.jokeItemList = jokeItemList;
    }

    @NonNull
    @Override
    public JokeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_joke,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JokeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.updatetime.setText(jokeItemList.get(i).getUpdatetime());
        viewHolder.content.setText(jokeItemList.get(i).getContent());
        viewHolder.itemView.setTag(i);

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return jokeItemList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}

