package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class random extends AppCompatActivity {
    TextView txt;
    Button nxt, rn;

    ListView lister;

    String foodID, Name;

    FirebaseDatabase ran;
    DatabaseReference databaseReference, referver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        txt = (TextView) findViewById(R.id.txtran);
        rn = (Button) findViewById(R.id.rnd);
        nxt = (Button)findViewById(R.id.next);
        lister = (ListView) findViewById(R.id.lister);
//        ran = FirebaseDatabase.getInstance();
//        databaseReference = ran.getReference();

        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("food");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    // Event listenere to update.
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> productIdsList = new ArrayList<>();
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            String productId = data.getKey();
                            productIdsList.add(productId);
                        }
                        Collections.shuffle(productIdsList, new Random());
                        int counter = 0;

                        foodID = productIdsList.get(counter);

                        Name = dataSnapshot.child(foodID).child("name").getValue(String.class);

                        txt.setText(Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(random.this, resturList.class);
                intent.putExtra("foodID", foodID);
                startActivity(intent);
            }
        });

    }
}
