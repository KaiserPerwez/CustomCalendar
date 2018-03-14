package com.wgt.customcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;
    Date startDate = null;
    String month = null;
    CalendarAdapter adapter;
    boolean firstClick = true;

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
        try {
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate_str);
            txtDate.setText(DateFormat.format("MMM-yyyy", startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate != null) {
            adapter = new CalendarAdapter(this, startDate);
            grid.setAdapter(adapter);
            /*btnNext.performClick();
            btnPrev.performClick();*/
            Toast.makeText(this, "Date: " + startDate, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Date couldn't be parsed", Toast.LENGTH_SHORT).show();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);

                if (firstClick) {
                    cal.add(Calendar.DATE, 39);
                    firstClick = false;
                } else
                    cal.add(Calendar.DATE, 42);

                adapter.nextMonth(cal.getTime());
                adapter.notifyDataSetChanged();
                startDate = cal.getTime();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);

                if (firstClick) {
                    cal.add(Calendar.DATE, -45);
                    firstClick = false;
                } else
                    cal.add(Calendar.DATE, -42);

                adapter.nextMonth(cal.getTime());
                adapter.notifyDataSetChanged();
                startDate = cal.getTime();
            }
        });
    }
}
