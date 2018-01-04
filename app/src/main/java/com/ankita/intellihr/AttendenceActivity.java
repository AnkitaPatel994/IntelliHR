package com.ankita.intellihr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AttendenceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton previousButton,forwardButton;
    TextView txtMonthName;
    private int currentMonthIndex = 0;
    GridView gvCalDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        txtMonthName = (TextView)findViewById(R.id.txtMonthName);
        previousButton = (ImageButton)findViewById(R.id.previousButton);
        forwardButton = (ImageButton)findViewById(R.id.forwardButton);
        gvCalDay = (GridView)findViewById(R.id.gvCalDay);

        DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        Date date = new Date();

        String monthName = dateFormat.format(date);

        txtMonthName.setText(monthName);

        Calendar cal =  Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int month_M = cal.get(Calendar.MONTH)+1;

        GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
        randomNo.execute();

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMonthIndex--;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int month_M = cal.get(Calendar.MONTH)+1;

                txtMonthName.setText(monthName);

                GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
                randomNo.execute();

            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMonthIndex++;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int month_M = cal.get(Calendar.MONTH)+1;

                txtMonthName.setText(monthName);

                GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
                randomNo.execute();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myinfo)
        {
            Intent i = new Intent(getApplicationContext(),EmployeeInfoActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_leave)
        {
            Intent i = new Intent(getApplicationContext(),LeaveActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_logout)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String[] getMonth(int month, int year) {

        int gridSizeX = 7, gridSizeY = 6;
        int gridsize = gridSizeX * gridSizeY;
        String[] myStringArray = new String[gridSizeX*gridSizeY];

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, 1);
        //this gets the day of week range 1-7, Monday - Sunday
        int dayOfWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        //backtracks to the beginning of current week (Monday)
        gregorianCalendar.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - dayOfWeek);

        for (int i = 0; i < gridsize; i++)
        {
            myStringArray[i] = String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
            gregorianCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return myStringArray;
    }

    private List<String> getSunday(int month, int year) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, 1);
        List<String> myStringSunArray = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("d/M/yyyy");
        do {
            int dayOfWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY)
                myStringSunArray.add(fmt.format(gregorianCalendar.getTime()));
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
        } while (gregorianCalendar.get(Calendar.MONTH) == month);

        return myStringSunArray;
    }

    private class GetRandomNo extends AsyncTask<String,Void,String> {

        int month, month_m, year;

        List<String> absentList = new ArrayList<>();
        List<String> holidayList = new ArrayList<>();
        List<String> helfLeaveList = new ArrayList<>();
        List<String> paidLeaveList = new ArrayList<>();
        List<String> casualLeaveList = new ArrayList<>();
        List<String> sickLeaveList = new ArrayList<>();

        public GetRandomNo(int month, int month_m, int year) {

            this.month = month;
            this.month_m = month_m;
            this.year = year;

        }

        @Override
        protected String doInBackground(String... strings) {

            paidLeaveList.add("4/12/2017");
            paidLeaveList.add("5/12/2017");

            holidayList.add("25/12/2017");

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            gvCalDay.setAdapter(new DayGridAdapter(AttendenceActivity.this, getMonth(month,year), getSunday(month,year), month_m, year, absentList, paidLeaveList, helfLeaveList, holidayList, casualLeaveList, sickLeaveList));
        }
    }

}
