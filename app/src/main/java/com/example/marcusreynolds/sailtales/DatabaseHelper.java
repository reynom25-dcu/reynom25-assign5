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

/**
 * The type Database helper.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * The constant TABLE_NAME.
     */
// Table Name defined
    public static final String TABLE_NAME = "SAILTALES";

    /**
     * The constant _ID.
     */
// Table columns defined
    public static final String _ID = "_id";
    /**
     * The constant SLOC.
     */
    public static final String SLOC = "startlocation";
    /**
     * The constant FLOC.
     */
    public static final String FLOC = "finishlocation";
    /**
     * The constant BOAT.
     */
    public static final String BOAT = "boat";
    /**
     * The constant WT.
     */
    public static final String WT = "weather";
    /**
     * The constant DSNM.
     */
    public static final String DSNM = "distance";
    /**
     * The constant DESC.
     */
    public static final String DESC = "description";
    /**
     * The constant MB.
     */
    public static final String MB = "milebuilder";
    /**
     * The constant DATEC.
     */
    public static final String DATEC = "date_created";
    /**
     * The constant STIME.
     */
    public static final String STIME = "starttime";
    /**
     * The constant SDATE.
     */
    public static final String SDATE = "startdate";
    /**
     * The constant TTIME.
     */
    public static final String TTIME = "triptime";

    /**
     * The constant DB_NAME.
     */
// Database Name defined
    static final String DB_NAME = "SAIL_TALES19.DB";

    /**
     * The constant DB_VERSION.
     */
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

    /**
     * Instantiates a new Database helper.
     *
     * @param context the context
     */
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
