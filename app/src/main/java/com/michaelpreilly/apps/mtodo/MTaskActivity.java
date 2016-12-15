package com.michaelpreilly.apps.mtodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.EditText;
;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MTaskActivity extends AppCompatActivity {
    private ArrayAdapter<String> projectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText myDate;
        final EditText myProject;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtask);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDate = (EditText) findViewById(R.id.targetDateEditText);
        Calendar rightNow = Calendar.getInstance();
        int mYear = rightNow.get(Calendar.YEAR);
        int mMonth = rightNow.get(Calendar.MONTH)+1;
        int mDay = rightNow.get(Calendar.DAY_OF_MONTH);
        myDate.setText(Integer.toString(mMonth) +"/"+Integer.toString(mDay)+"/"+Integer.toString(mYear));

        myDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int mMonth=0;
                int mDay=0;
                int mYear=0;
                int md=0;
                int yd=0;

                String tmpStr;

                String dateIn = myDate.getText().toString();

                md = dateIn.indexOf("/");
                yd = dateIn.lastIndexOf("/");

                tmpStr = dateIn.substring(0,md);
                mMonth = Integer.valueOf(tmpStr);
                tmpStr = dateIn.substring(md+1,yd);
                mDay = Integer.valueOf(tmpStr);
                tmpStr = dateIn.substring(yd+1);
                mYear = Integer.valueOf(tmpStr);

                //Log.d("MPR-date-In",Integer.toString(mMonth) +"/"+Integer.toString(mDay)+"/"+Integer.toString(mYear)+"-"+Integer.toString(md)+"-"+Integer.toString(yd));

                DatePickerDialog mDatePicker = new DatePickerDialog(MTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                            myDate.setText((selectedmonth + 1) + "/" + (selectedday) + "/" + selectedyear);

                       // Log.d("MPR-date-Out",Integer.toString(selectedmonth) +"/"+Integer.toString(selectedday)+"/"+Integer.toString(selectedyear));
                        ;


                    }
                }, mYear, mMonth-1, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        myProject = (EditText) findViewById(R.id.projectEdit);
        final Intent intent = new Intent(this, chooseProjectActivity.class);
        myProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, 17);
                // TODO: Need to create a list of intents and not hardcode this value
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final EditText myProject;
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 17 && resultCode == Activity.RESULT_OK) {
            myProject = (EditText) findViewById(R.id.projectEdit);
            myProject.setText(data.getStringExtra("project"));
        }
    }

    //Other activity results go here.. if (requestCode == 17 && resultCode == Activity.RESULT_OK) { }



}
