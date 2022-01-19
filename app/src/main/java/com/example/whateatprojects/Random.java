package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Category;
import com.example.whateatprojects.Model.Menufood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Random extends AppCompatActivity {

    TextView txtrd;
    FirebaseDatabase database;
    DatabaseReference menufood;
    Button random, nextgoto;
    FirebaseRecyclerAdapter<Category,MyAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random2);
        txtrd = (TextView) findViewById(R.id.txtran);
        random = (Button) findViewById(R.id.rnd);
        nextgoto = (Button) findViewById(R.id.next);
        database = FirebaseDatabase.getInstance();




        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}