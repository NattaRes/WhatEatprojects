package com.example.whateatprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class random extends AppCompatActivity {
    TextView txt;
    Button nxt, rn;
    String ran[] = {"ไก่ทอดกระเทียม","ไก่ทอด"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        txt = (TextView) findViewById(R.id.txtran);
        rn = (Button) findViewById(R.id.rnd);
        nxt = (Button)findViewById(R.id.next);


        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Random random = new java.util.Random();
                int num = random.nextInt(ran.length);
                txt.setText(ran[num]);
            }
        });
    }
}