package com.example.marcusreynolds.sailtales;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.example.marcusreynolds.sailtales.RSSReader.SimpleRSSReaderActivity;

/**
 * This is the listview. It handles the fetchlist cursor and displays the results to the listview
 * Created by marcusreynolds on 06/03/16.<p></p>
 * It uses some code developed by Anupam Chugh found at the link below <p></p>
 * http://www.journaldev.com/9438/android-sqlite-database-example-tutorial
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 05/04/2016
 */
public class ListTrips extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    /**
     * The Columns used in the listview
     */
    //DB Columns that are use din the listview
    final String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SLOC, DatabaseHelper.DSNM,
            DatabaseHelper.SDATE, DatabaseHelper.STIME, DatabaseHelper.DESC, DatabaseHelper.FLOC, DatabaseHelper.TTIME};
    /**
     * The array of layout objects to assign to database fields
     */
    //Array of layout objects to assign to database fields
    final int[] to = new int[] { R.id.id, R.id.sloc, R.id.dsnm ,R.id.sdate, R.id.stimeView, R.id.descView, R.id.flocView, R.id.ttimeView };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_list);

        //Code for floating AddTrip Butotn
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddTrip.class);
                startActivity(i);
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchlist();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.fragment_viewtrip, cursor, columns, to, 0);
        adapter.notifyDataSetChanged();
        Log.i("Listtrip", "STIMEad = " + DatabaseHelper.STIME);

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {


                //Fields to display record data in listview
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView slocTextView = (TextView) view.findViewById(R.id.sloc);
                TextView dsnmTextView = (TextView) view.findViewById(R.id.dsnm);
                TextView sdateTextView = (TextView) view.findViewById(R.id.sdate);
                TextView stimeTextView = (TextView) view.findViewById(R.id.stimeView);
                TextView descTextView = (TextView) view.findViewById(R.id.descView);
                TextView flocTextView = (TextView) view.findViewById(R.id.flocView);
                TextView ttimeTextView = (TextView) view.findViewById(R.id.ttimeView);

                String id = idTextView.getText().toString();
                String sloc = slocTextView.getText().toString();
                String dsnm = dsnmTextView.getText().toString();
                String sdate = sdateTextView.getText().toString();
                String stime = stimeTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String floc = flocTextView.getText().toString();
                String ttime = ttimeTextView.getText().toString();
                Log.i("Listtrip", "SDate = " + sdate);
                Log.i("Listtrip", "STIME = " + R.id.stimeView);


                // Item click will start update fragment and pass data from pressed record
                Intent modify_intent = new Intent(getApplicationContext(), UpdateTrip.class);
                modify_intent.putExtra("sloc", sloc);
                modify_intent.putExtra("dsnm", dsnm);
                modify_intent.putExtra("sdate", sdate);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("stime", stime);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("floc", floc);
                modify_intent.putExtra("ttime", ttime);

                startActivity(modify_intent);
            }
        });
    }

    //Add menu to interface
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Code to handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.summary) {

            Intent Summary = new Intent(this, Summary.class);
            startActivity(Summary);

        }

        if (id == R.id.news) {

            Intent news = new Intent(this, SimpleRSSReaderActivity.class);
            startActivity(news);

        }

        if (id == R.id.compass) {

            Intent compass = new Intent(this, com.example.marcusreynolds.sailtales.Compass.compass.class);
            startActivity(compass);

        }

        return super.onOptionsItemSelected(item);
    }}


