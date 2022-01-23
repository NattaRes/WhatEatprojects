package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class listdetail extends AppCompatActivity {

    TextView textView;

    String getresID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetail);

        getresID = getIntent().getStringExtra("ResID");

        textView = (TextView) findViewById(R.id.nameres);

        textView.setText(getresID);
    }
}