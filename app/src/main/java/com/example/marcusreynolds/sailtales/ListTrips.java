package com.example.marcusreynolds.sailtales;

/**
 * Created by marcusreynolds on 06/03/16.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListTrips extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SLOC, DatabaseHelper.FLOC, DatabaseHelper.DSNM, DatabaseHelper.DESC };

    final int[] to = new int[] { R.id.id, R.id.sloc, R.id.floc, R.id.dsnm ,R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.fragment_viewtrip, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView slocTextView = (TextView) view.findViewById(R.id.sloc);
                TextView flocTextView = (TextView) view.findViewById(R.id.floc);
                TextView dsnmTextView = (TextView) view.findViewById(R.id.dsnm);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String sloc = slocTextView.getText().toString();
                String floc = flocTextView.getText().toString();
                String dsnm = dsnmTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UpdateTrip.class);
                modify_intent.putExtra("sloc", sloc);
                modify_intent.putExtra("floc", floc);
                modify_intent.putExtra("dsnm", dsnm);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_trip = new Intent(this, AddTrip.class);
            startActivity(add_trip);

        }
        return super.onOptionsItemSelected(item);
    }}


