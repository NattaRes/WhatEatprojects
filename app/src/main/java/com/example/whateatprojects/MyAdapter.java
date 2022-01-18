package com.example.whateatprojects;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateatprojects.Inteface.ItemClickListener;

public class MyAdapter extends RecyclerView.ViewHolder implements  View.OnClickListener {

    public TextView Name;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }

    public MyAdapter(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.ca);
        Name = (TextView)itemView.findViewById(R.id.tfood);
         itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

}
