package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Menufood;
import com.example.whateatprojects.Model.ResturantList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resturantList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference resList;

    String FoodID="";

    FirebaseRecyclerAdapter<ResturantList, ResturantAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_list);

        database = FirebaseDatabase.getInstance();
        resList = database.getReference("Resname");

        recyclerView = (RecyclerView) findViewById(R.id.restuList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            FoodID = getIntent().getStringExtra("FoodID");
        if(!FoodID.isEmpty() && FoodID != null) {
            loadRestur(FoodID);
        }
    }

    private void loadRestur(String foodID) {
        adapter = new FirebaseRecyclerAdapter<ResturantList, ResturantAdapter>(ResturantList.class, R.layout.restuitem,
                ResturantAdapter.class,resList.orderByChild("resfoodlist").equalTo(FoodID)) {
            @Override
            protected void populateViewHolder(ResturantAdapter resturantAdapter, ResturantList resturantList, int i) {
                resturantAdapter.restuname.setText(resturantList.getName());

                final ResturantList local = resturantList;
                resturantAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(resturantList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}