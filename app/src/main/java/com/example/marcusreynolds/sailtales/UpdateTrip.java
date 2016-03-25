package com.example.marcusreynolds.sailtales;

/**
 * Created by marcusreynolds on 07/03/16.
 */

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class UpdateTrip extends Activity implements View.OnClickListener {

    private Button modButton;
    private Button rmButton;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;
    private TextView stimeViewText;
    private TextView sdateViewText;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Trip Details");

        setContentView(R.layout.fragment_update);
        dbManager = new DBManager(this);
        dbManager.open();

        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);
        dsnmEditText = (EditText) findViewById(R.id.dsnmadd_edittext);
        descEditText = (EditText) findViewById(R.id.descadd_edittext);
        stimeViewText = (TextView) findViewById(R.id.stimeadd_viewtext);
        sdateViewText = (TextView) findViewById(R.id.sdateadd_viewtext);

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

        _id = Long.parseLong(id);

        slocEditText.setText(sloc);
        flocEditText.setText(floc);
        dsnmEditText.setText(dsnm);
        descEditText.setText(desc);
        stimeViewText.setText(stime);
        sdateViewText.setText(sdate);

        modButton.setOnClickListener(this);
        rmButton.setOnClickListener(this);
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
            case R.id.mod_button:
                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                final String dsnm = dsnmEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String stime = stimeViewText.getText().toString();
                final String sdate = sdateViewText.getText().toString();

                dbManager.update(_id, sloc, floc, dsnm, desc, stime, sdate);
                this.returnHome();
                break;

            case R.id.rm_button:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListTrips.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    }

