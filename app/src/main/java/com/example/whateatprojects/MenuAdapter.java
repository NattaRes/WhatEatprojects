package com.example.whateatprojects;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateatprojects.Inteface.ItemClickListener;

public class MenuAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView foodname;
    public ImageView foodimage;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MenuAdapter(@NonNull View itemView) {
        super(itemView);

        foodimage = (ImageView) itemView.findViewById(R.id.foodimage);
        foodname = (TextView)itemView.findViewById(R.id.foodname);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
