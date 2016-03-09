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
    static final String DB_NAME = "SAIL_TALES2.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating DB table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SLOC + " TEXT NOT NULL, "
            + DSNM + " TEXT, "
            + FLOC + " TEXT NOT NULL, "
            + BOAT + " TEXT, "
            + WT + " TEXT, "
            + MB + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Oncreate make DB and table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //Upgrade DB Check
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
