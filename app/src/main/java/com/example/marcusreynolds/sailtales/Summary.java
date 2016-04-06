package com.example.marcusreynolds.sailtales;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by marcusreynolds on 06/03/16.
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 5/04/2016
 */
public class Summary extends Activity {
    private TextView tnmView;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Total Distance");
        setContentView(R.layout.fragment_summary);
        tnmView = (TextView) findViewById(R.id.tnmView);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor Distance = dbManager.Distance();

        String result = "";

        // get column value
        if (Distance.moveToNext())
            result = String.valueOf(Distance.getDouble(Distance.getColumnIndex("myTotal")));

        tnmView.setText(result);

    }
}



