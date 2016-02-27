package com.example.marcusreynolds.sailtales;

/**
 * Created by Marcus Reynolds 27/02/2016
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "SAILTALES";

    // Table columns
    public static final String _ID = "_id";
    public static final String SLOC = "startlocation";
    public static final String FLOC = "finishlocation";
    public static final String BOAT = "boat";
    public static final String WT = "weather";
    public static final String DSNM = "distance";
    public static final String MB = "milebuilder";

    // Database Information
    static final String DB_NAME = "SAIL_TALES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SLOC + " TEXT NOT NULL, "
            + FLOC + " TEXT NOT NULL, "
            + BOAT + " TEXT, "
            + WT + " TEXT, "
            + MB + " INTEGER, "
            + DSNM + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
