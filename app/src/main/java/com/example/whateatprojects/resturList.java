package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.whateatprojects.Model.Menufood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resturList extends AppCompatActivity {

    TextView textView, textView2;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reslist;

    String resgetID;

    FirebaseRecyclerAdapter<Menufood,MenuAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restur_list);

        database = FirebaseDatabase.getInstance();
        reslist = database.getReference("Resname");

        recyclerView = (RecyclerView) findViewById(R.id.returlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String getFID = getIntent().getStringExtra("foodID");

        reslist.child(getFID).child("ResLIDP").child("01").setValue(resgetID);

        textView = (TextView) findViewById(R.id.tester);
        textView.setText(getFID);

        textView2 = (TextView) findViewById(R.id.keycalltest);
        textView2.setText(resgetID);
    }
}