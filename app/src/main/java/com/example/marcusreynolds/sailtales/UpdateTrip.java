package com.example.marcusreynolds.sailtales;



import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *This code manages the update or deletion of records in the database. It works in a similar way to
 * the Addtrip class using the same fields but also has a delete function.
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 05/04/2016
 *
 */
public class UpdateTrip extends Activity implements View.OnClickListener {

    private Button modButton;
    private Button rmButton;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;
    private EditText ttimeEditText;
    private Button add_time;
    private Button add_date;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment Title
        setTitle(getString(R.string.modtripdetails));

        setContentView(R.layout.fragment_update);
        dbManager = new DBManager(this);
        dbManager.open();

        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);
        dsnmEditText = (EditText) findViewById(R.id.dsnmadd_edittext);
        descEditText = (EditText) findViewById(R.id.descadd_edittext);
        ttimeEditText = (EditText) findViewById(R.id.ttimeadd_edittext);
        add_time = (Button) findViewById(R.id.add_time);
        add_date = (Button) findViewById(R.id.add_date);

        modButton = (Button) findViewById(R.id.mod_button);
        rmButton = (Button) findViewById(R.id.rm_button);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String sloc = intent.getStringExtra("sloc");
        String floc = intent.getStringExtra("floc");
        String desc = intent.getStringExtra("desc");
        String dsnm = intent.getStringExtra("dsnm");
        String stime = intent.getStringExtra("stime");
        String sdate = intent.getStringExtra("sdate");
        String ttime = intent.getStringExtra("ttime");

        _id = Long.parseLong(id);
        slocEditText.setText(sloc);
        flocEditText.setText(floc);
        dsnmEditText.setText(dsnm);
        descEditText.setText(desc);
        ttimeEditText.setText(ttime);
        add_time.setText(stime);
        add_date.setText(sdate);
        modButton.setOnClickListener(this);
        rmButton.setOnClickListener(this);
    }

    /**
     * Show date picker dialog.
     *
     * @param v the v
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    /**
     * Show time picker dialog.
     *
     * @param v the v
     */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    //Onclick handler to update records in the database
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mod_button:
                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                String dsnm = dsnmEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String ttime = ttimeEditText.getText().toString();
                final String stime = add_time.getText().toString();
                final String sdate = add_date.getText().toString();
                //If the user enters a null value for distance it is replaced with a 0
                if(dsnm.matches("")) {
                    dsnm = "0";
                }else{
                    dsnm = dsnmEditText.getText().toString();
                }
            //Update record based on user input
                dbManager.update(_id, sloc, floc, dsnm, desc, stime, sdate, ttime);
                this.returnHome();
                break;
            //The delete button removes the selected record from the database
            case R.id.rm_button:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    /**
     * Return back to the listview.
     */
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListTrips.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    }

