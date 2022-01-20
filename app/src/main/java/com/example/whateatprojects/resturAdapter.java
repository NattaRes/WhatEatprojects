package com.example.whateatprojects;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateatprojects.Inteface.ItemClickListener;

public class resturAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView restuname;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public resturAdapter(@NonNull View itemView) {
        super(itemView);

        restuname = (TextView) itemView.findViewById(R.id.resname);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
