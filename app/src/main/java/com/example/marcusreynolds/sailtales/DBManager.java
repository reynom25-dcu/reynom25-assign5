package com.example.marcusreynolds.sailtales;

/**
 *
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 5/04/2016
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * The type Db manager.
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    /**
     * Instantiates a new Db manager.
     *
     * @param c the c
     */
    public DBManager(Context c) {
        context = c;
    }

    /**
     * Open db manager.
     *
     * @return the db manager
     * @throws SQLException the sql exception
     */
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Insert.
     *
     * @param sloc  the sloc
     * @param floc  the floc
     * @param dsnm  the dsnm
     * @param desc  the desc
     * @param stime the stime
     * @param sdate the sdate
     * @param ttime the ttime
     */
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

    /**
     * Totaldistance cursor.
     *+ " DESC limit 5"
     * @return the cursor
     */
    public Cursor totaldistance(){
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.DSNM, DatabaseHelper.SDATE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper._ID + " DESC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Totaltrips cursor.
     *
     * @return the cursor
     */
    public Cursor totaltrips(){
        Cursor cursor = database.rawQuery("SELECT * AS TTotal FROM " + DatabaseHelper.TABLE_NAME, null);
        return cursor;
    }

    /**
     * Distance cursor.
     *
     * @return the cursor
     */
    public Cursor Distance() {
        Cursor Distance = database.rawQuery("SELECT Sum(" + DatabaseHelper.DSNM + ") AS myTotal FROM " + DatabaseHelper.TABLE_NAME, null);
        return Distance;
    }


    /**
     * Fetch 1 cursor.
     *
     * @return the cursor
     */
    public Cursor fetch1() {

        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SLOC, DatabaseHelper.FLOC, DatabaseHelper.DSNM, DatabaseHelper.SDATE, DatabaseHelper.STIME, DatabaseHelper.DESC, DatabaseHelper.TTIME };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper._ID+" DESC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Update int.
     *
     * @param _id   the id
     * @param sloc  the sloc
     * @param floc  the floc
     * @param dsnm  the dsnm
     * @param desc  the desc
     * @param stime the stime
     * @param sdate the sdate
     * @param ttime the ttime
     * @return the int
     */
//Updating DB record content
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

    /**
     * Delete.
     *
     * @param _id the id
     */
//Deleting DB record
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }



}
