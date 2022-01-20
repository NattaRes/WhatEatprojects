package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button recylerviewbtn,ran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        recylerviewbtn = findViewById(R.id.recyclerviewbtn);
        ran = (Button)findViewById(R.id.rn) ;
        recylerviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Home.this, foodlist.class);
                startActivity(i);
                finish();


            }
        });
        ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Home.this,random.class);
                startActivity(a);
            }
        });

    }
}