package com.example.marcusreynolds.sailtales;

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

public class AddTrip extends Activity implements View.OnClickListener {

    private Button addButton;
    private EditText slocEditText;
    private EditText flocEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.add_trip));

        setContentView(R.layout.fragment_addtrip);

        slocEditText = (EditText) findViewById(R.id.slocadd_edittext);
        flocEditText = (EditText) findViewById(R.id.flocadd_edittext);

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
                final String boat = flocEditText.getText().toString();
                final String wt = flocEditText.getText().toString();
                final String dsnm = flocEditText.getText().toString();
                final String mb = flocEditText.getText().toString();

                dbManager.insert(sloc, floc, boat, wt, dsnm, mb);

                Intent main = new Intent(AddTrip.this, ListTrips.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}