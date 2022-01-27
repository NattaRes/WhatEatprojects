package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whateatprojects.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    Button regis;
    EditText edtphone,edtpss,edtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtphone = (EditText)findViewById(R.id.phone);
        edtpss =(EditText)findViewById(R.id.pssre);
        edtname = (EditText)findViewById(R.id.use);
        regis = (Button)findViewById(R.id.regis);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("โปรดรอ. . .");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //check if alrady user phone
                        if(snapshot.child(edtphone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "มีผู้ใช้หมายเลขโทรศัพท์นี้แล้ว", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else {
                            mDialog.dismiss();
                            User user = new User(edtname.getText().toString(),edtpss.getText().toString());
                            table_user.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(Register.this, "การลงทะเบียนเสร็จสมบูรณ์!", Toast.LENGTH_SHORT).show();
                            finish();
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