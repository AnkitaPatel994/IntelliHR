package com.ankita.intellihr;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kalpe on 12/9/2017.
 */

public class DayGridAdapter extends BaseAdapter {

    private Context context;
    private final String[] days;
    private final int month;
    private final int year;
    List<String> sunday, absentList,paidLeaveList,helfLeaveList, holidayList, casualLeaveList, sickLeaveList;

    // global var to keep track of whether a day-cell belongs to the current month
    Boolean in = false;

    public DayGridAdapter(Context context, String[] days, List<String> sunday, int month, int year, List<String> absentList, List<String> paidLeaveList, List<String> helfLeaveList, List<String> holidayList, List<String> casualLeaveList, List<String> sickLeaveList) {
        this.context = context;
        this.days = days;
        this.month = month;
        this.year = year;
        this.sunday = sunday;
        this.absentList = absentList;
        this.paidLeaveList = paidLeaveList;
        this.helfLeaveList = helfLeaveList;
        this.holidayList = holidayList;
        this.casualLeaveList = casualLeaveList;
        this.sickLeaveList = sickLeaveList;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.day_gridcell, null);

            // coming from the previous month and encounter (for the first time) the cell "1"
            if(days[position].equals("1") && in == false){
                in = true;
            }
            // coming from the current month and encounter (for the second time) the cell "1"
            else if (days[position].equals("1") && in == true){
                in = false;
            }

            LinearLayout llDay = (LinearLayout) gridView.findViewById(R.id.llDay);
            llDay.setPadding(20,20,20,30);

            ImageView ivEventColor = (ImageView) gridView.findViewById(R.id.ivEventColor);

            TextView button = (TextView) gridView.findViewById(R.id.day_grid_item);
            button.setText(days[position]);

            /*llDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String date = days[position] +"/"+month+"/"+year;
                    Toast.makeText(context,""+date,Toast.LENGTH_SHORT).show();

                }
            });*/

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
            String Today = sdf.format(c.getTime());

            String Currentdate = days[position] +"/"+month+"/"+year;

            if(in)
            {
                //white background color
                if(Currentdate.equals(Today))
                {
                    llDay.setBackgroundResource(R.drawable.box_bg);
                    button.setTypeface(null, Typeface.BOLD);
                    button.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }

                for (int i = 0; i < sunday.size(); i++)
                {
                    if(sunday.get(i).equals(Currentdate))
                    {
                        button.setTextColor(context.getResources().getColor(R.color.colorRed));
                    }
                }

                for (int i = 0; i < absentList.size(); i++)
                {
                    if(absentList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }

                for (int i = 0; i < holidayList.size(); i++)
                {
                    if(holidayList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }

                for (int i = 0; i < helfLeaveList.size(); i++)
                {
                    if(helfLeaveList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorb), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }

                for (int i = 0; i < paidLeaveList.size(); i++)
                {
                    if(paidLeaveList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }

                for (int i = 0; i < casualLeaveList.size(); i++)
                {
                    if(casualLeaveList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorLightBlue), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }

                for (int i = 0; i < sickLeaveList.size(); i++)
                {
                    if(sickLeaveList.get(i).equals(Currentdate))
                    {
                        ivEventColor.setVisibility(View.VISIBLE);
                        ivEventColor.setColorFilter(context.getResources().getColor(R.color.colorLightGreen), PorterDuff.Mode.SRC_IN);
                        llDay.setPadding(20,20,20,18);
                    }
                }
            }
            else
            {
                //light grey background color
                button.setTextColor(context.getResources().getColor(R.color.colorLightGrey));
                ivEventColor.setVisibility(View.GONE);
                llDay.setPadding(20,20,20,30);
            }

        }
        else
        {
            gridView = convertView;
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}