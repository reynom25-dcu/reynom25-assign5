package com.example.marcusreynolds.sailtales;

/**
 * Created by marcusreynolds on 23/03/16.
 */import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
// Do something with the date chosen by the user
        Log.w("DatePicker", "Date = " + year);
        ((TextView) getActivity().findViewById(R.id.sdateadd_viewtext)).setText(day + "/" + month + "/" + year);
        ((Button) getActivity().findViewById(R.id.add_date)).setText(day + "/" + month + "/" + year);
    }
}

