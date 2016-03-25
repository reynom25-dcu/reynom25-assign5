package com.example.marcusreynolds.sailtales;

/**
 * Created by Marcus Reynolds
 */
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTrip extends Activity implements View.OnClickListener {

    private Button addButton;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;
    private TextView stimeViewText;
    private TextView sdateViewText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.add_trip));

        setContentView(R.layout.fragment_addtrip);

        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);
        dsnmEditText = (EditText) findViewById(R.id.dsnmadd_edittext);
        descEditText = (EditText) findViewById(R.id.descadd_edittext);
        stimeViewText = (TextView) findViewById(R.id.stimeadd_viewtext);
        sdateViewText = (TextView) findViewById(R.id.sdateadd_viewtext);

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
                final String stime = stimeViewText.getText().toString();
                final String sdate = sdateViewText.getText().toString();


                dbManager.insert(sloc, floc, dsnm, desc, stime, sdate);

                Intent main = new Intent(AddTrip.this, ListTrips.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }



}