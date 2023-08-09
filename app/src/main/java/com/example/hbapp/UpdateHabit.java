package com.example.hbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateHabit extends AppCompatActivity {

    EditText title , description, startingTimeEdit, endTimeEdit;
    Button updateHabit, deletebutton;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_habit);

        title = findViewById(R.id.editTextTitleupdate);
        description = findViewById(R.id.editTextDescriptionupdate);
        updateHabit = findViewById(R.id.UpdateHabit);
        startingTimeEdit = findViewById(R.id.startingTimeEdit);
        endTimeEdit = findViewById(R.id.endTimeEdit);
        deletebutton = findViewById(R.id.button);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        startingTimeEdit.setText(i.getStringExtra("hour"));
        endTimeEdit.setText(i.getStringExtra("min"));

        id = i.getStringExtra("id");

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(UpdateHabit.this, "Habit Deleted", Toast.LENGTH_SHORT).show();

                Database db = new Database(UpdateHabit.this);
                db.deleteHabits(title.getText().toString(), description.getText().toString(), id, startingTimeEdit.getText().toString(), endTimeEdit.getText().toString() );

                Intent intent = new Intent(UpdateHabit.this, HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        updateHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(description.getText().toString()) || TextUtils.isEmpty(startingTimeEdit.getText().toString()) || TextUtils.isEmpty(endTimeEdit.getText().toString()))
                {
                    Toast.makeText(UpdateHabit.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Database db = new Database(UpdateHabit.this);
                    db.updateHabit(title.getText().toString(), description.getText().toString(), id, startingTimeEdit.getText().toString(), endTimeEdit.getText().toString() );

                    Intent intent = new Intent(UpdateHabit.this, HomeScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}