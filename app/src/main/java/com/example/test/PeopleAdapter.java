package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PeopleAdapter extends RecyclerView.Adapter<com.example.test.PeopleAdapter.PeopleViewHolder> {
    private ArrayList<PeopleItem> PeopleList;
    private Context ctx;

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic;
        public TextView name;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.people_pic);
            name = itemView.findViewById(R.id.people_name);

        }
    }

    public PeopleAdapter(ArrayList<PeopleItem> exampleList, Context ctx) {
        PeopleList = exampleList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_people_items, parent, false);
        PeopleAdapter.PeopleViewHolder pvh = new PeopleAdapter.PeopleViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PeopleAdapter.PeopleViewHolder holder, int position) {
        PeopleItem currentItem = PeopleList.get(position);
        holder.pic.setImageResource(currentItem.getPeoplePic());
        holder.name.setText(currentItem.getPeopleName());
        try {
            holder.pic.setImageBitmap(BitmapFactory.decodeFile(new ImageReader(currentItem.getPeopleName(), ctx).execute().get().getPath()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final int pos = position;
    }

    @Override
    public int getItemCount() {
        return PeopleList.size();
    }
}
