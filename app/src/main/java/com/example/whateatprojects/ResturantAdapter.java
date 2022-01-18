package com.example.whateatprojects;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateatprojects.Inteface.ItemClickListener;

public class ResturantAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView restuname, foodprice;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ResturantAdapter(@NonNull View itemView) {
        super(itemView);

        restuname = (TextView) itemView.findViewById(R.id.foodres);
        foodprice = (TextView) itemView.findViewById(R.id.resfoodprice);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
