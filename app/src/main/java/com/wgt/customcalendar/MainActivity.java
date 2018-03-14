package com.wgt.customcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;
    Date startDate = null;
    Date currentDate = null;
    CalendarAdapter adapter;
    List<String> leaveDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);


        //dummy leaves
        leaveDates.add("13");leaveDates.add("15");
        leaveDates.add("14");leaveDates.add("17");



        //initial setup of calendar
        currentDate = Calendar.getInstance().getTime();
        startDate = getStartDate(currentDate);
        adapter = new CalendarAdapter(this, leaveDates);
        adapter.setStartDate(startDate);
        setDatesToCalendarView(startDate);
        txtDate.setText(DateFormat.format("MMM-yyyy", currentDate));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                cal.add(Calendar.MONTH, 1);

                currentDate = cal.getTime();
                startDate = getStartDate(currentDate);
                setDatesToCalendarView(startDate);
                adapter.notifyDataSetChanged();

                txtDate.setText(DateFormat.format("MMM-yyyy", currentDate));
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                cal.add(Calendar.MONTH, -1);

                currentDate = cal.getTime();
                startDate = getStartDate(currentDate);
                setDatesToCalendarView(startDate);
                adapter.notifyDataSetChanged();

                txtDate.setText(DateFormat.format("MMM-yyyy", currentDate));
            }
        });
    }

    private void setDatesToCalendarView(Date startDate) {
        adapter.setStartDate(startDate);
        grid.setAdapter(adapter);
    }

    private Date getStartDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.DATE, 1);
        int i = cal.get(Calendar.DAY_OF_WEEK);

        if (--i > 0)
            cal.add(Calendar.DATE, -i);

        return cal.getTime();
    }
}
