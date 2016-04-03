package com.example.marcusreynolds.sailtales;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by marcus.reynolds on 24/03/2016.
 */
public class Summary extends Activity {

    private DBManager dbManager;

    private Integer totaldistance = 0;
    String totaltime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DBManager(this);
        dbManager.open();
       // Log.i("Assign5", ": Team A name Saved");
        setContentView(R.layout.fragment_summary);
        //Cursor cursor = dbManager.totaldistance();

        }


    }

