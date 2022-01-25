package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class listdetail extends AppCompatActivity {

    TextView textView, textTer;

    String getresID = "";

    String gettris;

    FirebaseDatabase database;
    DatabaseReference restur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetail);

        //////////////////////// Tester /////////////////////////

        getresID = getIntent().getStringExtra("ResID");

        textView = (TextView) findViewById(R.id.nameres);

        textView.setText(getresID);

        ////////////////////////////////////////////////////////

        database = FirebaseDatabase.getInstance();
        restur = database.getReference("Resname/" + getresID);

        restur.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gettris = snapshot.child("Name").getValue().toString();
                textView.setText(gettris);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}