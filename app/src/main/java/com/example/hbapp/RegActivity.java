package com.example.hbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity{
    EditText username, password, repassword;
    Button signup;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.btnsignup);
        db=new DBHelper(this);

        //create listeners

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name=username.getText().toString();
                String user_password=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user_name.equals("")||user_password.equals("")||repass.equals(""))
                    Toast.makeText(RegActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    if(user_password.equals(repass)){
                        boolean checkUser=db.checkUsername(user_name);
                        if(!checkUser){
                            boolean insert=db.AddData(user_name,user_password); //insert data if not already present
                            if(insert){
                                Toast.makeText(RegActivity.this, "Welcome to Habit Builder ;) Login to continue.", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(RegActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}