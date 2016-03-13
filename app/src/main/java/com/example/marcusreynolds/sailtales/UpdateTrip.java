package com.example.marcusreynolds.sailtales;

/**
 * Created by marcusreynolds on 07/03/16.
 */
/**
 * Created by Marcus Reynolds
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

public class UpdateTrip extends Activity implements View.OnClickListener {

    private Button addButton;
    private EditText slocEditText;
    private EditText flocEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.fragment_addtrip);

        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);

        addButton = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                final String dsnm = slocEditText.getText().toString();
                final String desc = flocEditText.getText().toString();


                dbManager.insert(sloc, floc, dsnm, desc);

                Intent main = new Intent(UpdateTrip.this, ListTrips.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }}

