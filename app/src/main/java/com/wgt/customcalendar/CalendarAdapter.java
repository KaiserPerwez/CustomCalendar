package com.wgt.customcalendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 09-03-2018.
 */

public class CalendarAdapter extends BaseAdapter {
    Date currentDate;
    ViewHolder holder;
    LayoutInflater inflater;
    Context context;

    public CalendarAdapter(Context context, Date currentDate) {
        this.context = context;
        this.currentDate = currentDate;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void nextMonth(Date date){
        this.currentDate=date;
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
        // date = new Date(currentDate.getTime());
        holder = new ViewHolder();
        View itemView = inflater.inflate(R.layout.grid_item, null);
        holder.ll = itemView.findViewById(R.id.grid_item);
       /* if (i<cal_firstDayNumOfWeek || i>=num_of_days_in_month){
            holder.ll.setBackgroundColor(Color.GRAY);
        }
*//*        else if()
            holder.ll.setBackgroundColor(Color.BLACK);*//*
        else
            holder.ll.setBackgroundColor(Color.RED);*/


        holder.tv = itemView.findViewById(R.id.txt_date);
        if (i++ < 6)
            dt="i";
        else {
            dt = (String) DateFormat.format("dd", currentDate);
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, 1);
            currentDate = cal.getTime();
        }
        //holder.tv.setText(sb.append(" ").append(cal_startDate));

        holder.tv.setText(dt);
        return itemView;
    }

    class ViewHolder {
        TextView tv;
        LinearLayout ll;
    }
}
