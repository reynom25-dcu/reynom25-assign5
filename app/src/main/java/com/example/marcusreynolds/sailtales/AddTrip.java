package com.example.marcusreynolds.sailtales;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This code is for adding a new trip. It handles adding a new record to the database from user input.
 * <p></p>It uses date and time picker dialog fragments with code taken from
 * http://developer.android.com/guide/topics/ui/controls/pickers.html
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 05/04/2016
 *
 */

public class AddTrip extends Activity implements View.OnClickListener {

    private Button add_time;
    private Button add_date;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;
    private EditText ttimeEditText;

    private DBManager dbManager;


    //Start activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment title
        setTitle(getString(R.string.add_trip));
        //Set layout
        setContentView(R.layout.fragment_addtrip);
        //Declare layout elements
        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);
        dsnmEditText = (EditText) findViewById(R.id.dsnmadd_edittext);
        descEditText = (EditText) findViewById(R.id.descadd_edittext);
        ttimeEditText = (EditText) findViewById(R.id.ttimeadd_edittext);
        add_time = (Button) findViewById(R.id.add_time);
        add_date = (Button) findViewById(R.id.add_date);
        Button addButton = (Button) findViewById(R.id.add_trip);

        //Open database
        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
    }

    /**
     * Show date picker dialog.
     *
     * @param v Opens Date Picker dialog fragment
     *
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    /**
     * Show time picker dialog.
     *
     * @param v Time hour and minute selected by user.
     *
     */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    //Add button click handler
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_trip:

                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                String dsnm = dsnmEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String stime = add_time.getText().toString();
                final String sdate = add_date.getText().toString();
                final String ttime = ttimeEditText.getText().toString();
                Log.w("Addtrip", "dsnm = " + dsnm );
                //If distance input is null then save a 0 value instead
                if(dsnm.matches("")) {
                dsnm = "0";
                }else{
                    dsnm = dsnmEditText.getText().toString();
                }
                Log.w("Addtrip", "dsnm2 = " + dsnm );

                //Insert new record with user values
                dbManager.insert(sloc, floc, dsnm, desc, stime, sdate, ttime);

                Intent main = new Intent(AddTrip.this, ListTrips.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Log.w("Addtrip", "Desc = " + desc );
                Log.w("Addtrip", "stime = " + stime );
                Log.w("Addtrip", "floc = " + floc );
                startActivity(main);
                break;
        }
    }



}