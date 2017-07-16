package com.yang.designsupportdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/3 下午4:05
 */

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater layoutInflater;
    public MyAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=layoutInflater.inflate(R.layout.adapter_list,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(item);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
