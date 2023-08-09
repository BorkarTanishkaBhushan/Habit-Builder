package com.example.hbapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
//import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    Adapter adapter;
    List<Habit> habitList;
    Database database;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);

        date = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd  MMM  yyyy, EEE");
        String datetime = dateFormat.format(calendar.getTime());
        date.setText(datetime);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, AddHabit.class);
                startActivity(intent);
            }
        });

        habitList = new ArrayList<>();

        database = new Database(this);
        fetchAllHabitsFromDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, HomeScreen.this, habitList);
        recyclerView.setAdapter(adapter);

    }

    private void fetchAllHabitsFromDatabase() {
        Cursor cursor = database.readAllData();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No habits to display.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
                habitList.add(new Habit(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.searchBar);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setQueryHint("Search Habit Reminders here!");
//
//        SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        };
//
//        searchView.setOnQueryTextListener(listener);
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId() == R.id.delete_all_habits)
//        {
//            deleteAllHabits();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void deleteAllHabits() {
//
//        Database db = new Database(HomeScreen.this);
//        db.deleteAllHabits();
//        recreate();
//    }
}