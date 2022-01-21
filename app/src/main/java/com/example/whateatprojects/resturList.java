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

    TextView textView;
    String getter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reslist, pather;

    String getfoodID = "";

    FirebaseRecyclerAdapter<Resturantaf, resturAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restur_list);

        database = FirebaseDatabase.getInstance();
        reslist = database.getReference("Resname");

        pather = database.getReference("food").child("Reslist").getParent();

        getter = pather.getKey();

        // เรียก ID จาก Resname ที่เท่ากับ Reslist

        recyclerView = (RecyclerView) findViewById(R.id.resturlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        textView = (TextView) findViewById(R.id.tester);
        textView.setText(getter);

        if(getIntent() != null)
            getfoodID = getIntent().getStringExtra("foodID");
        if(!getfoodID.isEmpty() && getfoodID != null) {
            loadRes(getfoodID);
        }
    }

    private void loadRes(String getfoodID) {
        adapter = new FirebaseRecyclerAdapter<Resturantaf, resturAdapter>(Resturantaf.class, R.layout.resitem,
                resturAdapter.class,reslist.child("foodinres").orderByChild("foodID").equalTo(getfoodID)) {
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