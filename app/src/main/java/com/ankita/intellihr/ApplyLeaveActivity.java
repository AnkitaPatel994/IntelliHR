package com.ankita.intellihr;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApplyLeaveActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spLeaveType,spLeaveDayTypeStart,spLeaveDayTypeEnd;
    TextView txtStartDate,txtEndDate;
    TextInputLayout input_layout_leave_reason;
    EditText txtLeaveReason;
    Button btnApply;
    LinearLayout llStartDate,llEndDate;

    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_applyleave);
        setContentView(R.layout.activity_apply_leave);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        spLeaveType = (Spinner)findViewById(R.id.spLeaveType);
        spLeaveDayTypeStart = (Spinner)findViewById(R.id.spLeaveDayTypeStart);
        spLeaveDayTypeEnd = (Spinner)findViewById(R.id.spLeaveDayTypeEnd);

        llStartDate = (LinearLayout)findViewById(R.id.llStartDate);
        llStartDate.setOnClickListener(this);
        llEndDate = (LinearLayout)findViewById(R.id.llEndDate);
        llEndDate.setOnClickListener(this);

        txtStartDate = (TextView)findViewById(R.id.txtStartDate);
        txtEndDate = (TextView)findViewById(R.id.txtEndDate);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy");
        Date date = new Date();
        txtStartDate.setText(sdf.format(date));
        txtEndDate.setText(sdf.format(date));

        input_layout_leave_reason = (TextInputLayout)findViewById(R.id.input_layout_leave_reason);

        txtLeaveReason = (EditText)findViewById(R.id.txtLeaveReason);

        btnApply = (Button)findViewById(R.id.btnApply);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String leaveType = String.valueOf(spLeaveType.getSelectedItem());
                String leaveDayTypeStart = String.valueOf(spLeaveDayTypeStart.getSelectedItem());
                String leaveDayTypeEnd = String.valueOf(spLeaveDayTypeEnd.getSelectedItem());

                String startDate = txtStartDate.getText().toString();
                String endDate = txtEndDate.getText().toString();

                String leaveReason = txtLeaveReason.getText().toString();

                Toast.makeText(getApplicationContext(),leaveType+"\n"+leaveDayTypeStart+"\n"+leaveDayTypeEnd+"\n"+startDate+"\n"+endDate+"\n"+leaveReason,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == llStartDate)
        {
            DatePickerDialog datePickerDialog = new DatePickerDialog(ApplyLeaveActivity.this,new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    startYear=year;
                    startMonth=monthOfYear;
                    startDay=dayOfMonth;
                    //txtStartDate.setText(startDay + "/" + (startMonth + 1) + "/" + startYear);

                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");
                    Date date = new Date(startYear, startMonth, startDay-1);
                    String dayOfWeek = simpledateformat.format(date);
                    int smonth = startMonth + 1;
                    String sm = null;
                    if(smonth<10)
                    {
                        sm = "0" +smonth;
                    }
                    else
                    {
                        sm = String.valueOf(smonth);
                    }

                    String sd = null;
                    if(startDay<10)
                    {
                        sd = "0" +startDay;
                    }
                    else
                    {
                        sd = String.valueOf(startDay);
                    }

                    txtStartDate.setText(dayOfWeek+", "+sd + "/" + sm + "/" + startYear);
                }
            }, startYear, startMonth, startDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
        }
        else if(v == llEndDate)
        {
            DatePickerDialog datePickerDialog1 = new DatePickerDialog(ApplyLeaveActivity.this,new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    startYear=year;
                    startMonth=monthOfYear;
                    startDay=dayOfMonth;
                    //txtStartDate.setText(startDay + "/" + (startMonth + 1) + "/" + startYear);

                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");
                    Date date = new Date(startYear, startMonth, startDay-1);
                    String dayOfWeek = simpledateformat.format(date);

                    int smonth = startMonth + 1;
                    String sm = null;
                    if(smonth<10)
                    {
                        sm = "0"+smonth;
                    }
                    else
                    {
                        sm = String.valueOf(smonth);
                    }

                    String sd = null;
                    if(startDay<10)
                    {
                        sd = "0" +startDay;
                    }
                    else
                    {
                        sd = String.valueOf(startDay);
                    }

                    txtEndDate.setText(dayOfWeek+", "+ sd + "/" + sm + "/" + startYear);
                }
            }, startYear, startMonth, startDay);
            datePickerDialog1.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog1.show();
        }
    }
}
