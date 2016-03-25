package com.example.marcusreynolds.sailtales;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by marcus.reynolds on 24/03/2016.
 */
public class Summary extends AppCompatActivity {

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_summary);

        dbManager = new DBManager(this);
        dbManager.open();
    }
}
