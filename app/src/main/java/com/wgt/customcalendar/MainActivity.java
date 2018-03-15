package com.wgt.customcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Date startDate = null;
    Date currentDate = null;
    CalendarAdapter adapter;
    List<Date> allLeaveDates = new ArrayList<>();
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);

//dummy leaves
        try {
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("13/02/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("15/02/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("16/02/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("18/02/2018"));

            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("12/03/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("19/03/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("21/03/2018"));

            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2018"));
            allLeaveDates.add(new SimpleDateFormat("dd/MM/yyyy").parse("03/04/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //initial setup of calendar
        currentDate = Calendar.getInstance().getTime();
        startDate = getStartDate(currentDate);
        List<String> leaveDates = getLeaveDates(currentDate.getMonth(), currentDate.getYear());
        adapter = new CalendarAdapter(this, leaveDates, startDate);
        grid.setAdapter(adapter);
        txtDate.setText(DateFormat.format("MMM-yyyy", currentDate));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                cal.add(Calendar.MONTH, 1);

                currentDate = cal.getTime();
                startDate = getStartDate(currentDate);
                List<String> leaveDates = getLeaveDates(currentDate.getMonth(), currentDate.getYear());
                setDatesToCalendarView(startDate, leaveDates);

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
                List<String> leaveDates = getLeaveDates(currentDate.getMonth(), currentDate.getYear());
                setDatesToCalendarView(startDate, leaveDates);

                txtDate.setText(DateFormat.format("MMM-yyyy", currentDate));
            }
        });
    }


    private void setDatesToCalendarView(Date startDate, List<String> leaveDates) {
        adapter.setStartDate(startDate);
        adapter.setLeaveDates(leaveDates);
        grid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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

    private List<String> getLeaveDates(int month, int year) {
        List<String> leaveListOfMonth = new ArrayList<>();
        for (Date item :
                allLeaveDates) {
            if (item.getMonth() == month && item.getYear() == year)
                leaveListOfMonth.add(item.getDate() + "");
        }
        return leaveListOfMonth;
    }

}
