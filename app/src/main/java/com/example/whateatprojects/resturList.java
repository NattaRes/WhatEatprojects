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

public class resturList extends AppCompatActivity {

    TextView textView, textView2;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reslist;

    String resgetID = "";

    FirebaseRecyclerAdapter<Resturantaf, resturAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restur_list);

        // เข้า Database

        // table : food

        // เข้า Key ที่ได้รับจาก Intent

        // รับค่า Key ภายใน ResLIDP

        // เรียกรายการจาก Key จาก table : R

        reslist = FirebaseDatabase.getInstance().getReference();



//        database = FirebaseDatabase.getInstance();
//        reslist = database.getReference("Resname");
//
//        recyclerView = (RecyclerView) findViewById(R.id.returlist);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        resgetID = getIntent().getStringExtra("foodID");
    }
}