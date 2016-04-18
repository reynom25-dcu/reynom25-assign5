package com.example.marcusreynolds.sailtales;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcusreynolds on 06/03/16.
 *
 * @author Marcus Reynolds <marcus.reynolds25@mail.dcu.ie>
 * @version 1.0, 2016
 * @since 5/04/2016
 */
public class Summary extends Activity {
    private TextView ttView;
    private TextView avgdsView;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int ttrips = 0;
        double NMavg = 0;
        int count = 0;

        setTitle("Statistics");
        setContentView(R.layout.fragment_summary);
        TextView tnmView = (TextView) findViewById(R.id.tnmView);
        TextView ttView = (TextView) findViewById(R.id.ttView);
        TextView avgView = (TextView) findViewById(R.id.avgdsView);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor Distance = dbManager.Distance();

        int TN0 = 0;
        int TN1 = 1;
        int TN2 = 2;
        int TN3 = 3;
        int TN4 = 4;

        String result = "";
        String ttrips1 = "";
        String avgDouble1= "";

        // get column value
        if (Distance.moveToNext())
            result = String.valueOf(Distance.getDouble(Distance.getColumnIndex("myTotal")));

        tnmView.setText(result);
        Cursor cursor1 =  dbManager.totaldistance();
        count = cursor1.getCount();
        Log.i("Graph", "Trip Count = " + count);

        List<String> distancearray = new ArrayList<String>();
        Cursor cursor =  dbManager.totaldistance();

        int numResults = cursor.getCount();
        if (numResults > 0){
            do{
                distancearray.add(cursor.getString(1));
            }while ((cursor.moveToNext()));
            ttrips = cursor.getCount();
            Log.i("Graph", "TTRIPS = " + ttrips);

        }


        // Be sure here to have at least the 5 desired elements into the list
        while(distancearray.size() < 5){
            distancearray.add("0");
        }



        String DNM0A = distancearray.get(0);
        String DNM1A = distancearray.get(1);
        String DNM2A = distancearray.get(2);
        String DNM3A = distancearray.get(3);
        String DNM4A = distancearray.get(4);
        //Test stored array values
        Log.i("Graph", "Array 0 = " + DNM0A);
        Log.i("Graph", "Array 1 = " + DNM1A);
        Log.i("Graph", "Array 2 = " + DNM2A);
        Log.i("Graph", "Array 3 = " + DNM3A);
        Log.i("Graph", "Array 4 = " + DNM4A);

        double resultnum = Double.parseDouble(result);
        NMavg = resultnum / ttrips;
        //avgDouble = Double.toString(NMavg);
        //Log.i("Graph", "avgDouble = " + avgDouble);
        Log.i("Graph", "ttrips = " + ttrips);
        Log.i("Graph", "resultnm = " + resultnum);
        //avgdsView.setText(avgDouble);
        //tnmView.setText(result);


        double DNM0 = Double.parseDouble(DNM0A);
        double DNM1 = Double.parseDouble(DNM1A);
        double DNM2 = Double.parseDouble(DNM2A);
        double DNM3 = Double.parseDouble(DNM3A);
        double DNM4 = Double.parseDouble(DNM4A);
        //double DNM0 = DNM0A;

        //Graphview plots the distance of the 5 most recent trips
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(TN0, DNM0),
                new DataPoint(TN1, DNM1),
                new DataPoint(TN2, DNM2),
                new DataPoint(TN3, DNM3),
                new DataPoint(TN4, DNM4)
        });
        graph.addSeries(series);
        //Second graph line to show average distance

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(TN0, NMavg),
                new DataPoint(TN1, NMavg),
                new DataPoint(TN2, NMavg),
                new DataPoint(TN3, NMavg),
                new DataPoint(TN4, NMavg)
        });
        series.setColor(Color.BLUE);
        series2.setColor(Color.RED);
        graph.addSeries(series2);

        // custom paint to make a dotted line
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series2.setCustomPaint(paint);

        series.setTitle("NM");
        series2.setTitle("AVG NM");

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        // custom label formatter to show currency "EUR"
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + "NM";
                }
            }
        });

        ttrips1 = String.valueOf(ttrips);

        ttView.setText(ttrips1);
        DecimalFormat df = new DecimalFormat("#.##");
        NMavg = Double.valueOf(df.format(NMavg));

        avgDouble1 = String.valueOf(NMavg);


        avgView.setText(avgDouble1);



    }



}



