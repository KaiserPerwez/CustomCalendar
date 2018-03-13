package com.wgt.customcalendar;

import android.content.Context;
import android.util.Log;
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
    int cal_startDate = 1;
    int cal_startMax = 31;
    int cal_firstDayNumOfWeek;
    int num_of_days_in_month;

    public CalendarAdapter(Context context, Date currentDate) {
        this.context = context;
        this.currentDate = currentDate;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        cal_firstDayNumOfWeek = getFirstDayNumOfWeek();
        //num_of_days_in_month = getEndWeekDayNum();
    }

    private int getFirstDayNumOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

   /* private int getEndWeekDayNum() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int i = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return i;
    }*/

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
    StringBuilder sb;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        holder = new ViewHolder();
        sb = new StringBuilder();
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
            sb.append("i");
        else {
            if (cal_startDate <= cal_startMax)
                sb = sb.append(cal_startDate++);
            else if (cal_startDate > cal_startMax) {
                int diff = cal_startDate++ - cal_startMax;
                if (diff > num_of_days_in_month) {
                    sb.append((diff - num_of_days_in_month));
                } else
                    sb.append(diff);
            }
            Log.d("SB: ", sb.toString());
        }
        //holder.tv.setText(sb.append(" ").append(cal_startDate));

        holder.tv.setText(sb);
        return itemView;
    }

    class ViewHolder {
        TextView tv;
        LinearLayout ll;
    }
}
