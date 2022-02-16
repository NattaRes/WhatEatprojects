package com.example.whateatprojects;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateatprojects.Inteface.ItemClickListener;

public class resturAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView restuname, pricer, locatis;
    private ItemClickListener itemClickListener;
    public ImageView reslogo;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public resturAdapter(@NonNull View itemView) {
        super(itemView);

        reslogo = (ImageView) itemView.findViewById(R.id.resturlogo);
        restuname = (TextView) itemView.findViewById(R.id.resname);
        pricer = (TextView) itemView.findViewById(R.id.cost);
        locatis = (TextView) itemView.findViewById(R.id.address);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
