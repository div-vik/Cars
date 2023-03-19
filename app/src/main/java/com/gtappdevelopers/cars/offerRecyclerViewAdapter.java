package com.gtappdevelopers.cars;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class offerRecyclerViewAdapter  extends RecyclerView.Adapter<offerRecyclerViewAdapter.MyViewHolder> {

    public offerRecyclerViewAdapter(Context context,  String[] name,Bitmap[] img) {
//        this.arrImage = arrImage;
        this.name=name;
        this.context=context;
        this.img=img;
    }
    Context context;
    Bitmap[] img;

    String []name;



    @NonNull
    @Override
    public offerRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offers,parent,false);
        offerRecyclerViewAdapter.MyViewHolder myViewHolder=new offerRecyclerViewAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull offerRecyclerViewAdapter.MyViewHolder holder, int position) {
//        holder.imageView.setImageResource(arrImage[position]);
        holder.imageView.setImageBitmap(img[position]);

    }

    @Override
    public int getItemCount() {
        return img.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.offerimg);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos=this.getAdapterPosition();
            Toast.makeText(context,name[pos],Toast.LENGTH_SHORT).show();
            Log.e("Response................",name[pos]);
        }
    }
}