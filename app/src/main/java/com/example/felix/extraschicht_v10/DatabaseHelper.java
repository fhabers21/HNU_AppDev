package com.example.felix.extraschicht_v10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.felix.extraschicht_v10.EventContract.*;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Events.db";
    public static final int DATABSE_VERSION = 1;



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABSE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " +
                EventEntry.DB_TABLE + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventEntry.TITEL + " TEXT NOT NULL, " +
                EventEntry.FIRMA + " TEXT NOT NULL, "+
                EventEntry.IMG + " TEXT NOT NULL "+ ")";


        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ EventEntry.DB_TABLE);

        onCreate(db);
    }
}
