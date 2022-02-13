package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.whateatprojects.Inteface.Callback;

public class Home extends AppCompatActivity implements Callback {

//    Button recylerviewbtn,ran;

    BottomNavigationView btnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.Fcontainer, new RandomFragment()).commit();
        }

        btnav = findViewById(R.id.bottomnavigationbar);

        btnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ranmenu:
                        openFragment(RandomFragment.newInstance("", ""));
                        return true;
                    case R.id.catemenu:
                        openFragment(CategoryFragment.newInstance("", ""));
                        return true;
                }
                return false;
            }
        });
        openFragment(RandomFragment.newInstance("", ""));

//        recylerviewbtn = findViewById(R.id.recyclerviewbtn);
//        ran = (Button)findViewById(R.id.rn) ;
//        recylerviewbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home.this, foodlist.class);
//                startActivity(i);
//                finish();
//            }
//        });
//        ran.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(Home.this,random.class);
//                startActivity(a);
//            }
//        });
    }

    public void openFragment(Fragment fragment) {
        String aoneid = fragment.getArguments().getString("foodID", "");
        String btwoid = fragment.getArguments().getString("CateID", "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Fcontainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}