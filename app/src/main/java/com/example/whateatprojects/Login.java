package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whateatprojects.Model.User;
import com.example.whateatprojects.common.common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button log;
    EditText edtphone,edtpss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtphone = (EditText) findViewById(R.id.phone);
        edtpss = (EditText) findViewById(R.id.pss);
        log = (Button)findViewById(R.id.log);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("โปรดรอ. . .");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.child(edtphone.getText().toString()).getValue(User.class);
                        //check if user not exits database
                        if (snapshot.child(edtphone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            //get user information

                            if (user.getPassword().equals(edtpss.getText().toString())) {
                                Intent i = new Intent(Login.this,Home.class);
                                common.currentUser = user;
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "รหัสผ่านผิดพลาด!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(Login.this, "ไม่พบข้อมูลผู้ใช้ในฐานข้อมูล", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}