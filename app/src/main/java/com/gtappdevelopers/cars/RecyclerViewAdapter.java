package com.gtappdevelopers.cars;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    String []arrName;
    Context context;
    Bitmap [] arrimg;

    public RecyclerViewAdapter(Context context,String[] arrName,Bitmap [] arrimg) {
        this.arrName=arrName;
        this.context=context;
        this.arrimg=arrimg;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.social_media_single_view,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.imageView.setImageBitmap(arrimg[position]);
    holder.textView.setText(arrName[position]);
    }

    @Override
    public int getItemCount() {
        return arrimg.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.socialMediaImage);
            textView = itemView.findViewById(R.id.socialMediaName);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,webview.class);
            context.startActivities(new Intent[]{intent});
        }
    }
}
