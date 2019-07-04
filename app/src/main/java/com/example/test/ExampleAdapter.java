package com.example.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageview;
        public TextView mTextview1;
        public  TextView mTextview2;
        public  TextView mTextview3;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageview=itemView.findViewById(R.id.imageView);
            mTextview1=itemView.findViewById(R.id.textView);
            mTextview2=itemView.findViewById(R.id.textView2);
            mTextview3=itemView.findViewById(R.id.textView3);

        }
    }
    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList=exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
       ExampleViewHolder evh=new ExampleViewHolder(v);
       return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem=mExampleList.get(position);
        holder.mImageview.setImageResource(currentItem.getImageResourse());
        holder.mTextview1.setText(currentItem.getmText1());
        holder.mTextview2.setText(currentItem.getmText2());
        holder.mTextview3.setText(currentItem.getmText3());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
