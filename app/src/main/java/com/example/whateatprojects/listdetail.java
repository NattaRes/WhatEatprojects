package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class listdetail extends AppCompatActivity {

    TextView textView, textTer;

    String getresID = "", getname = "";

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

        getname = restur.child("Name").getKey();

        textTer = (TextView) findViewById(R.id.time);

        textTer.setText(getname);

        getData();
    }

    private void getData() {
    }

    public void getData(DataSnapshot dataSnapshot) {
        DataSnapshot getter = (DataSnapshot) dataSnapshot.child(getresID).child("Name").getValue();
        textView.setText((CharSequence) getter);
    }
}