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

    String foodID, Name;

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
                databaseReference = FirebaseDatabase.getInstance().getReference("food");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int count = (int) dataSnapshot.getChildrenCount();
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            int rand = new Random().nextInt(count);
                            for (int i = 0; i < rand; i++) {
                                Name = data.child("name").getValue().toString();
                                foodID = data.child("foodID").getValue().toString();
                                txt.setText(Name);
                            }
                    }
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
