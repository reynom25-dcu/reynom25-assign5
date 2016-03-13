package com.example.marcusreynolds.sailtales;

/**
 * Created by Marcus Reynolds
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTrip extends Activity implements View.OnClickListener {

    private Button addButton;
    private EditText slocEditText;
    private EditText flocEditText;
    private EditText dsnmEditText;
    private EditText descEditText;

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

        addButton = (Button) findViewById(R.id.add_trip);

        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_trip:

                final String sloc = slocEditText.getText().toString();
                final String floc = flocEditText.getText().toString();
                final String dsnm = dsnmEditText.getText().toString();
                final String desc = descEditText.getText().toString();


                dbManager.insert(sloc, floc, dsnm, desc);

                Intent main = new Intent(AddTrip.this, ListTrips.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}