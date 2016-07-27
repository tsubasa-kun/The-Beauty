package com.love_cookies.cookie_library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiekun on 2016/06/29.
 *
 * RecyclerView的通用适配器
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {
    private Context mContext;
    private int mLayoutId;
    private List<T> mDatas;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public void setOnItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public CommonRecyclerAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonRecyclerViewHolder holder = CommonRecyclerViewHolder.NewInstance(mContext, null, parent, mLayoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    protected void setListener(final ViewGroup parent, final CommonRecyclerViewHolder holder, int viewType) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerItemClickListener != null) {
                    int position = getPosition(holder);
                    mOnRecyclerItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnRecyclerItemClickListener != null) {
                    int position = getPosition(holder);
                    return mOnRecyclerItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    private int getPosition(CommonRecyclerViewHolder holder) {
        return holder.getAdapterPosition();
    }

    @Override
    public void onBindViewHolder(CommonRecyclerViewHolder holder, int position) {
        holder.setPosition(position);
        setData(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void setData(CommonRecyclerViewHolder holder, T t);
}
