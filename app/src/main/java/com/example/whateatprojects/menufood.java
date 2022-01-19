package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Category;
import com.example.whateatprojects.Model.Menufood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class menufood extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference menuList;

    String CataID="";

    FirebaseRecyclerAdapter<Menufood,MenuAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufood);

        database = FirebaseDatabase.getInstance();
        menuList = database.getReference("food");

        recyclerView = (RecyclerView) findViewById(R.id.menulist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            CataID = getIntent().getStringExtra("CataID");
        if(!CataID.isEmpty() && CataID != null) {
            loadMenu(CataID);
        }
    }

    private void loadMenu(String cataID) {
        adapter = new FirebaseRecyclerAdapter<Menufood, MenuAdapter>(Menufood.class, R.layout.menuitem,
                MenuAdapter.class,menuList.orderByChild("CataID").equalTo(CataID)) {
            @Override
            protected void populateViewHolder(MenuAdapter menuAdapter, Menufood food, int i) {
                menuAdapter.foodname.setText(food.getName());
                Picasso.with(getBaseContext()).load(food.getImage()).into(menuAdapter.foodimage);

                Menufood click = food;
                menuAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(menufood.this, ""+click.getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(menufood.this, foodlist.class);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}