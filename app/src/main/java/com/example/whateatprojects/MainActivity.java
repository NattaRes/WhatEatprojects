package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void regis(View view) {
        startActivity(new Intent(MainActivity.this,Register.class));
    }

    public void log(View view) {
        startActivity(new Intent(MainActivity.this,Login.class));
    }

    public void ne(View view) {
        startActivity(new Intent(MainActivity.this,random.class));
    }
}