package com.example.marcusreynolds.sailtales;

/**
 * Created by Marcus Reynolds 27/02/2016
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //Creating DB record
    public void insert(String sloc, String floc, String dsnm, String desc, String stime, String sdate, String ttime) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SLOC, sloc);
        contentValue.put(DatabaseHelper.FLOC, floc);
        contentValue.put(DatabaseHelper.DSNM, dsnm);
        contentValue.put(DatabaseHelper.DESC, desc);
        contentValue.put(DatabaseHelper.STIME, stime);
        contentValue.put(DatabaseHelper.SDATE, sdate);
        contentValue.put(DatabaseHelper.TTIME, ttime);
        //contentValue.put(DatabaseHelper.MB, mb);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch1() {

        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SLOC, DatabaseHelper.FLOC, DatabaseHelper.DSNM, DatabaseHelper.SDATE, DatabaseHelper.STIME, DatabaseHelper.DESC, DatabaseHelper.TTIME };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper._ID+" DESC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    //Updating DB record
    public int update(long _id, String sloc, String floc, String dsnm, String desc, String stime, String sdate, String ttime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SLOC, sloc);
        contentValues.put(DatabaseHelper.FLOC, floc);
        contentValues.put(DatabaseHelper.DSNM, dsnm);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.STIME, stime);
        contentValues.put(DatabaseHelper.SDATE, sdate);
        contentValues.put(DatabaseHelper.TTIME, ttime);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
    //Deleting DB record
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
