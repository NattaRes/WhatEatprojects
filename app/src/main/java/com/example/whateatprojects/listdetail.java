package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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

    TextView textView, textView2;

    ImageView imageView;

    String getresID = "";

    String gettris, forurl;

    FirebaseDatabase database;
    DatabaseReference restur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetail);

        //////////////////////// Tester /////////////////////////

        getresID = getIntent().getStringExtra("ResID");

        textView = (TextView) findViewById(R.id.nameres);
        textView2 = (TextView) findViewById(R.id.time);

        imageView = (ImageView) findViewById(R.id.imgres);

        ////////////////////////////////////////////////////////

        database = FirebaseDatabase.getInstance();
        restur = database.getReference("Resname/" + getresID);

//        restur.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                gettris = snapshot.child("Name").getValue(String.class);
//                textView.setText(gettris);
//
//                Picasso.get().load(forurl).into(imageView);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        restur.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                gettris = snapshot.child("Name").getValue(String.class);
                textView.setText(gettris);

                forurl = snapshot.child("Image").getValue(String.class);

                Picasso.get().load(snapshot.child("Image").getValue(String.class)).into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}