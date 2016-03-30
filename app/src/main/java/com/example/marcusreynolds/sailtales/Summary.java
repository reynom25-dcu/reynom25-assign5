package com.example.marcusreynolds.sailtales;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by marcus.reynolds on 24/03/2016.
 */
public class Summary extends Activity {

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_summary);

        dbManager = new DBManager(this);
        dbManager.open();
        Log.i("Assign5", ": Team A name Saved");
    }
}
