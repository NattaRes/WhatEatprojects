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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class foodlist extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference category;
    LinearLayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category,MyAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        recyclerView = (RecyclerView) findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        load();
    }
    private void load(){
        adapter = new FirebaseRecyclerAdapter<Category, MyAdapter>(Category.class,R.layout.item,
                MyAdapter.class,category) {
            @Override
            protected void populateViewHolder(MyAdapter myAdapter, Category category, int i) {
                myAdapter.Name.setText(category.getName());
//                Picasso.with(getBaseContext()).load(category.getImage()).into(myAdapter.imageView);
                Picasso.get().load(category.getImage()).into(myAdapter.imageView);
            Category click = category;
            myAdapter.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Toast.makeText(foodlist.this, ""+click.getName(), Toast.LENGTH_SHORT).show();
                    Intent CataIntent = new Intent(foodlist.this,menufood.class);
                    CataIntent.putExtra("CataID", adapter.getRef(position).getKey());
                    startActivity(CataIntent);
                }
            });
            }

        };
        recyclerView.setAdapter(adapter);
    }
}