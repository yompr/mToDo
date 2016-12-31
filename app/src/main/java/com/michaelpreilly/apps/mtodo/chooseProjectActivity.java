package com.michaelpreilly.apps.mtodo;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.List;


public class chooseProjectActivity extends ListActivity {

    private ArrayAdapter<String> mProjectAdapter;
    public static List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateProjectList();

        ArrayAdapter<Project> adapter = new ProjectListArrayAdapter(this, projectList);
        setListAdapter(adapter);


        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project c = projectList.get(position);
                Intent returnIntent = new Intent();

               /* if (c.getName().equals("<Add New Project>")) {

                    // we could pop up dialog here to get new project name
                }*/
                returnIntent.putExtra("project", c.getName());
                setResult(RESULT_OK, returnIntent);

                finish();
            }
        });

    }


    private void populateProjectList() {
        projectList = new ArrayList<Project>();

        for (DataSnapshot data : com.michaelpreilly.apps.mtodo.MainActivity.projectData.getChildren()) {
            // Log.d("MPR-LIST-BUILD",String.valueOf(data.getKey())+", ");
            projectList.add(new Project(String.valueOf(data.getKey())));
        }
        projectList.add(new Project("<Add New Project>"));
    }

}

