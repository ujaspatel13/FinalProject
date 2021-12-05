package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper {
    public static final String DATABASE_NAME = "GROUPDB";
    public static final String CarbonDioxide_TABLE_NAME = "CarbonDioxideDetails";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);}

public void onCreate(SQLiteDatabase sqLiteDatabase){
    sqLiteDatabase.execSQL(
            "create table "+ CarbonDioxide_TABLE_NAME +"(id integer primary key, Title text,PubDate text,Thumbnail text,URL text,guid text)"
    );
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CarbonDioxide_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertCarbonDioxide(String Title,String PubDate,String Thumbnail,String URL,String guid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", Title);
        contentValues.put("PubDate", PubDate);
        contentValues.put("Thumbnail", Thumbnail);
        contentValues.put("URL", URL);
        contentValues.put("guid", guid);
        db.insert(CarbonDioxide_TABLE_NAME, null, contentValues);
        return true;
    }
    public ArrayList<CarbonDioxideModel> getSavedCarbonDioxide()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CarbonDioxideModel> itemList= new ArrayList<>();
        String query = "SELECT * FROM "+ CarbonDioxide_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String PubDate = cursor.getString(cursor.getColumnIndex("PubDate"));
                String Thumbnail = cursor.getString(cursor.getColumnIndex("Thumbnail"));
                String URL = cursor.getString(cursor.getColumnIndex("URL"));
                String guid = cursor.getString(cursor.getColumnIndex("guid"));
                itemList.add(new CarbonDioxideModel(title,PubDate, Thumbnail, URL, guid));
                cursor.moveToNext();
            }
        }

        return  itemList;
    }
}

