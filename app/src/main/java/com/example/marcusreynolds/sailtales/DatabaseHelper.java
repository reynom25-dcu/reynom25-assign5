package com.example.marcusreynolds.sailtales;

/**
 *
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 5/04/2016
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name defined
    public static final String TABLE_NAME = "SAILTALES";

    // Table columns defined
    public static final String _ID = "_id";
    public static final String SLOC = "startlocation";
    public static final String FLOC = "finishlocation";
    public static final String BOAT = "boat";
    public static final String WT = "weather";
    public static final String DSNM = "distance";
    public static final String DESC = "description";
    public static final String MB = "milebuilder";
    public static final String DATEC = "date_created";
    public static final String STIME = "starttime";
    public static final String SDATE = "startdate";
    public static final String TTIME = "triptime";

    // Database Name defined
    static final String DB_NAME = "SAIL_TALES11.DB";

    // database version defined
    static final int DB_VERSION = 1;

    // Creating DB table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SLOC + " TEXT NOT NULL, "
            + DSNM + " REAL, "
            + DESC + " TEXT, "
            + FLOC + " TEXT, "
            + BOAT + " TEXT, "
            + WT + " TEXT, "
            + STIME + " TEXT, "
            + SDATE + " TEXT, "
            + TTIME + " TEXT, "
            + DATEC + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + MB + " TEXT);";

    //Context of DB and verison
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Oncreate execute SQL make DB and table
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
