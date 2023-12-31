package com.example.hbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    Context context;
    private static final String DatabaseName = "User Habits";
    private static final int DatabaseVersion = 1;

    private static final String TableName = "myHabits";
    private static final String ColumnId = "id";
    private static final String ColumnTitle = "title";
    private static final String ColumnDescription = "description";
    private static final String ColumnHour = "hour";
    private static final String ColumnMin = "min";

    public Database(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TableName + " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnTitle + " TEXT ," + ColumnDescription + " TEXT ," + ColumnHour + " TEXT ," + ColumnMin + " TEXT);" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db .execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }

    void addHabit(String title, String description, String hour, String min)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        cv.put(ColumnHour, hour);
        cv.put(ColumnMin, min);

        long resultValue = db.insert(TableName, null, cv);

        if(resultValue == -1)
        {
            Toast.makeText(context, "Data Not Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Data Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
        }
        return  cursor;
    }

    public void deleteHabits(String title, String description, String id, String hour, String min) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        cv.put(ColumnHour, hour);
        cv.put(ColumnMin, min);
        long resultValue = database.delete(TableName, "id=?", new String[]{id});
        // String query = "DELETE FROM " + TableName;
        // database.execSQL(query);
    }

        public void updateHabit(String title, String description, String id,  String hour, String min) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        cv.put(ColumnHour, hour);
        cv.put(ColumnMin, min);

        long resultValue = database.update(TableName, cv, "id=?", new String[]{id});
        if(resultValue == -1)
        {
            Toast.makeText(context, "Data Not Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
