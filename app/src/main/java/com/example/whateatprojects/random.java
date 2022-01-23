package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Category;
import com.example.whateatprojects.Model.Menufood;
import com.example.whateatprojects.Model.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.Random;

public class random extends AppCompatActivity {
    TextView txt;
    Button nxt, rn;

    FirebaseDatabase ran;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        txt = (TextView) findViewById(R.id.txtran);
        rn = (Button) findViewById(R.id.rnd);
        nxt = (Button)findViewById(R.id.next);
        ran = FirebaseDatabase.getInstance();
        databaseReference = ran.getReference();

        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String h[]={String.valueOf(dataSnapshot.child("food/name"))};
                        java.util.Random random=new java.util.Random();
                        int num = random.nextInt(h.length);
                        txt.setText(h[num]);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
