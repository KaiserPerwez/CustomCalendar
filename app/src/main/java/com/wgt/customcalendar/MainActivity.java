package com.wgt.customcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (LinearLayout) findViewById(R.id.calendar_header);
        btnPrev = (ImageView) findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView) findViewById(R.id.calendar_next_button);
        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);

        String startDate_str = "26/02/2018";
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate != null) {
            CalendarAdapter adapter = new CalendarAdapter(this, startDate);
            grid.setAdapter(adapter);
            Toast.makeText(this, "Date: "+startDate, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Date couldn't be parsed", Toast.LENGTH_SHORT).show();
    }
}
