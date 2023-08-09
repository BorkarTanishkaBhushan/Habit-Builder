package com.example.hbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddHabit extends AppCompatActivity {

    EditText title , desc, startingTimeEdit, endTimeEdit;
    Button addHabit;
    private Button yesbutton;
    private Button nobutton, cancelbutton;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.EditTextDescription);
        startingTimeEdit = findViewById(R.id.startingTimeEdit);
        endTimeEdit = findViewById(R.id.endTimeEdit);
        addHabit = findViewById(R.id.AddHabit);
        addHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(desc.getText().toString()) || TextUtils.isEmpty(startingTimeEdit.getText().toString()) || TextUtils.isEmpty(endTimeEdit.getText().toString()))
                {
                    Toast.makeText(AddHabit.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Database db = new Database(AddHabit.this);
                    db.addHabit(title.getText().toString(), desc.getText().toString(), startingTimeEdit.getText().toString(), endTimeEdit.getText().toString());

                    Intent intent = new Intent(AddHabit.this, HomeScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

//        createNotificationChannel();

        yesbutton = findViewById(R.id.yesbutton);
        startingTimeEdit = findViewById(R.id.startingTimeEdit);
        endTimeEdit = findViewById(R.id.endTimeEdit);
        cancelbutton = findViewById(R.id.CancelButton);
        nobutton = findViewById(R.id.Nobutton);

        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddHabit.this, "No Notification",Toast.LENGTH_SHORT).show();
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddHabit.this, HomeScreen.class);
                startActivity(intent);

                Toast.makeText(AddHabit.this, "Habit CANCELLED!!!!!",Toast.LENGTH_SHORT).show();
            }
        });

        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNotificationChannel();

                String sh = startingTimeEdit.getText().toString();
                int time_hrs = Integer.parseInt(sh);
                String sm = endTimeEdit.getText().toString();
                int time_min = Integer.parseInt(sm);

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,time_hrs);
                calendar.set(Calendar.MINUTE,time_min);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

                setAlarm(calendar);
            }
        });

    }
    private void setAlarm(Calendar calendar) {


        Intent intent  = new Intent(this,AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


        Toast.makeText(this, "Notification Set Successfully", Toast.LENGTH_SHORT).show();

    }

    private  void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "HBApp";
            String description = "HBApp";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("HBApp",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }


}