package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    private Context ctx;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageview;
        public TextView mTextview1;
        public TextView mTextview2;
        public TextView mTextview3;
        public ImageView options;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageview = itemView.findViewById(R.id.imageView);
            mTextview1 = itemView.findViewById(R.id.textView);
            mTextview2 = itemView.findViewById(R.id.textView2);
            mTextview3 = itemView.findViewById(R.id.textView3);
            options = itemView.findViewById(R.id.class_click_btn);

        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList, Context ctx) {
        mExampleList = exampleList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mImageview.setImageResource(currentItem.getImageResourse());
        holder.mTextview1.setText(currentItem.getmText1());
        holder.mTextview2.setText(currentItem.getmText2());
        holder.mTextview3.setText(currentItem.getmText3());
        final int pos = position;
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("shitthisfuck", "onClick: Position " + mExampleList.get(pos).getCode());
                Intent i = new Intent(ctx, ChangeClassSettings.class);
                i.putExtra("classCode", mExampleList.get(pos).getCode());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
