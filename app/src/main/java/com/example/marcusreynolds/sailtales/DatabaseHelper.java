package com.example.marcusreynolds.sailtales;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *This is code that manages the database schema. It defines the database structure. It also
 * creates the database on first install. In future it can be used to upgrade the DB.
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 05/04/2016
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * The constant TABLE_NAME. This is the DB table name.
     */
    // Table Name defined
    public static final String TABLE_NAME = "SAILTALES";

    /**
     * The constant _ID. This field is used for the trip ID and primary key. Auto inc.
     */
    // Table columns defined
    public static final String _ID = "_id";
    /**
     * The constant SLOC. This field is used to store the start location
     */
    public static final String SLOC = "startlocation";
    /**
     * The constant FLOC. This field is used to store the finish location
     */
    public static final String FLOC = "finishlocation";
    /**
     * The constant BOAT. This field is used to store the boat name in version 2
     */
    public static final String BOAT = "boat";
    /**
     * The constant WT. This field is used to store the weather conditions in version 2
     */
    public static final String WT = "weather";
    /**
     * The constant DSNM. This field is used to store the distance of the trip in NM.
     */
    public static final String DSNM = "distance";
    /**
     * The constant DESC. This field is used to store the trip description.
     */
    public static final String DESC = "description";
    /**
     * The constant MB. This field is used to store the milebuilder flag option in version 2
     */
    public static final String MB = "milebuilder";
    /**
     * The constant DATEC. This field is used to store the datatime stamp of the record created
     */
    public static final String DATEC = "date_created";
    /**
     * The constant STIME. This field is used to store the start time.
     */
    public static final String STIME = "starttime";
    /**
     * The constant SDATE. This field is used to store the start date.
     */
    public static final String SDATE = "startdate";
    /**
     * The constant TTIME. This field is used to store the total time.
     */
    public static final String TTIME = "triptime";

    /**
     * The constant DB_NAME stores the Database name.
     */
    static final String DB_NAME = "SAIL_TALES.DB";

    /**
     * The constant DB_VERSION is the current DB version number.
     */
    static final int DB_VERSION = 1;

    // Creating DB table query with columns and data type. There are some unused fields which will be
    //* used in version 2.
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
     * Check for existing install of database name and database version
     *
     * @param context the context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Oncreate execute SQL code and xreate DB and table and columns.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    //Upgrade DB old version with new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
