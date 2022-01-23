package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class resturList extends AppCompatActivity {

    TextView textView;
    String getter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reslist, pather;

    String GetfoodID = "";

    FirebaseRecyclerAdapter<Resturantaf, resturAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restur_list);

        database = FirebaseDatabase.getInstance();
        reslist = database.getReference("Resname");

        // เรียก ID จาก Resname ที่เท่ากับ Reslist

        recyclerView = (RecyclerView) findViewById(R.id.resturlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        GetfoodID = getIntent().getStringExtra("foodID");

        loadRes(GetfoodID);
    }

    private void loadRes(String getfoodID) {
        adapter = new FirebaseRecyclerAdapter<Resturantaf, resturAdapter>(Resturantaf.class, R.layout.resitem,
                resturAdapter.class,reslist.orderByChild("foodID").equalTo(GetfoodID)) {
            @Override
            protected void populateViewHolder(resturAdapter resturAdapter, Resturantaf resturantaf, int i) {
                resturAdapter.restuname.setText(resturantaf.getName());

                Resturantaf click = resturantaf;
                resturAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(resturList.this, ""+click.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}