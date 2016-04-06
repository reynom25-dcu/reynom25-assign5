package com.example.marcusreynolds.sailtales;

/**
 * Created by marcusreynolds on 06/03/16.
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 5/04/2016
 */
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


public class AddTrip extends Activity implements View.OnClickListener {

    private Button addButton;
    private Button add_time;
    private Button add_date;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;
    private EditText ttimeEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.add_trip));

        setContentView(R.layout.fragment_addtrip);

        //Declare layout elements
        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);
        dsnmEditText = (EditText) findViewById(R.id.dsnmadd_edittext);
        descEditText = (EditText) findViewById(R.id.descadd_edittext);
        ttimeEditText = (EditText) findViewById(R.id.ttimeadd_edittext);
        add_time = (Button) findViewById(R.id.add_time);
        add_date = (Button) findViewById(R.id.add_date);

        addButton = (Button) findViewById(R.id.add_trip);

        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_trip:

                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                final String dsnm = dsnmEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String stime = add_time.getText().toString();
                final String sdate = add_date.getText().toString();
                final String ttime = ttimeEditText.getText().toString();


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