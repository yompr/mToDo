package com.michaelpreilly.apps.mtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class createContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
    }

    public void create_contact(View view) {
        final EditText myEmail;
        final EditText myName;
        final TextView myError;


        myName = (EditText) findViewById(R.id.createContactName);
        String contactName = myName.getText().toString();
        myEmail = (EditText) findViewById(R.id.createContactEmail);
        String emailAddr = myName.getText().toString();


        myError = (TextView) findViewById(R.id.contactErrorText);
        myError.setText("");

        if ((contactName.length() == 0) || contactName.equals("Name")) {
            myError.setText("Invalid Contact Name");
            return;
        }


    }
    }
