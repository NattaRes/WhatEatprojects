package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Resturantaf;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class resturList extends AppCompatActivity {

    TextView textView;
    String getter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference pather;

    String GetfoodID = "";

    FirebaseRecyclerAdapter<Resturantaf, resturAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restur_list);

        database = FirebaseDatabase.getInstance();

        GetfoodID = getIntent().getStringExtra("foodID");

        pather = database.getReference("foodinres/" + GetfoodID);

        recyclerView = (RecyclerView) findViewById(R.id.resturlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadRes(GetfoodID);
    }

    private void loadRes(String getfoodID) {
        adapter = new FirebaseRecyclerAdapter<Resturantaf, resturAdapter>(Resturantaf.class, R.layout.resitem,
                resturAdapter.class,pather.orderByKey()) {
            @Override
            protected void populateViewHolder(resturAdapter resturAdapter, Resturantaf resturantaf, int i) {
                resturAdapter.restuname.setText(resturantaf.getName());
                resturAdapter.pricer.setText(resturantaf.getPrice());
                resturAdapter.locatis.setText(resturantaf.getLocatinos());

                Resturantaf click = resturantaf;
                resturAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(resturList.this, ""+click.getName(), Toast.LENGTH_SHORT).show();
                        Intent resIDintent = new Intent(resturList.this, listdetail.class);
                        resIDintent.putExtra("ResID", adapter.getRef(position).getKey());
                        startActivity(resIDintent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}