package com.wgt.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 09-03-2018.
 */

public class CalendarAdapter extends BaseAdapter {
    Date startDate;
    ViewHolder holder;
    LayoutInflater inflater;
    Context context;
    List<String> leaveDates = new ArrayList<>();

    public CalendarAdapter(Context context, List<String> leaveDates) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.leaveDates = leaveDates;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        i = 0;//reset counter
    }

    @Override
    public int getCount() {
        return (6 * 7);//6 weeks
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    int i = 0;
    Calendar cal = Calendar.getInstance();
    String dt;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        View itemView = inflater.inflate(R.layout.grid_item, null);
        holder.ll = itemView.findViewById(R.id.grid_item);
        holder.tv = itemView.findViewById(R.id.txt_date);

        if (i++ < 3)
            dt = "No Date";
        else {
            dt = (String) DateFormat.format("dd", startDate);
            cal.setTime(startDate);
            cal.add(Calendar.DATE, 1);
            startDate = cal.getTime();

            // setColorToDate(dt);
        }
        holder.tv.setText(dt);
        return itemView;
    }

    private void setColorToDate(String dt) {
        if (leaveDates.contains(dt)) {
            holder.ll.setBackgroundColor(Color.RED);
        } else if (i % 7 == 0 || ((i + 1) % 7 == 0))//saturday sunday
            holder.ll.setBackgroundColor(Color.GRAY);
        else
            holder.ll.setBackgroundColor(Color.GREEN);
    }

    class ViewHolder {
        TextView tv;
        LinearLayout ll;
    }
}
