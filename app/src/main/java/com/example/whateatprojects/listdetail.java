package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class listdetail extends AppCompatActivity {

    TextView nares, chron, addr;

    ImageView imageView;

    Button gomap, backto;

    String getresID = "";

    String gettris, forurl, chroniser, locanos;

    FirebaseDatabase database;
    DatabaseReference restur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetail);

        //////////////////////// Tester /////////////////////////

        getresID = getIntent().getStringExtra("ResID");

        nares = (TextView) findViewById(R.id.nameres);
        chron = (TextView) findViewById(R.id.time);
        addr = (TextView) findViewById(R.id.add1);

        imageView = (ImageView) findViewById(R.id.imgres);

        gomap = (Button)findViewById(R.id.gomap);

        backto = (Button)findViewById(R.id.backhome2);
        ////////////////////////////////////////////////////////

        database = FirebaseDatabase.getInstance();
        restur = database.getReference("Resname/" + getresID);

        gomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(listdetail.this,MapsActivity.class);
                a.putExtra("sendresID", getresID);
                startActivity(a);
            }
        });

        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bcth = new Intent(listdetail.this,Home.class);
                startActivity(bcth);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        restur.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                gettris = snapshot.child("Name").getValue(String.class);
                nares.setText(gettris);

                chroniser = snapshot.child("OnCtime").getValue(String.class);
                chron.setText(chroniser);

                locanos = snapshot.child("locatinos").getValue(String.class);
                addr.setText(locanos);

                forurl = snapshot.child("Image").getValue(String.class);

                Picasso.get().load(snapshot.child("Image").getValue(String.class)).into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}