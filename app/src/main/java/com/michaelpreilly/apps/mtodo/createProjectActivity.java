package com.michaelpreilly.apps.mtodo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;


public class createProjectActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
    }


    public void create_project(View view) {
        final EditText myDesc;
        final EditText myName;
        final TextView myError;

        myDesc = (EditText) findViewById(R.id.projectDescEdit);
        myName = (EditText) findViewById(R.id.projectNameEdit);
        String projName = myName.getText().toString();

        myError = (TextView) findViewById(R.id.projectErrorText);
        myError.setText("");

        if ( projName.length() == 0  ) {
            myError.setText("Invalid Project Name");
            return;
        }

        for (DataSnapshot data : com.michaelpreilly.apps.mtodo.MainActivity.projectData.getChildren()) {

            if (projName.equals (String.valueOf(data.getKey()))){
                myError.setText("Project Name exists");
                return;
            }

        }

        // TODO: ARE THERE OTHER ERRORS TO LOOK FOR ?

        Project mProject = new Project(myName.getText().toString(), myDesc.getText().toString());
        Map<String, Object> projectValues = mProject.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(myName.getText().toString(), projectValues);

        MainActivity.projectsDBRef.updateChildren(childUpdates);

        //Log.d("MPR-CREATE-PROJECT","GOT Create");
        createProjectActivity.this.finish();
    }
    public void cancel_create_project(View view) {

        //Log.d("MPR-CREATE-PROJECT","GOT cancel");
        createProjectActivity.this.finish();
    }
}
