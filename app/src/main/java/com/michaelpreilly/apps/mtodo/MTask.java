package com.michaelpreilly.apps.mtodo;

/**
 * Created by dad on 12/15/16.
 */

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dad on 11/26/16.
 */
@IgnoreExtraProperties

public  class MTask {
    private String key;
    private String taskName;
    private Date creationDate;
    private Date completionDate;
    private String  project;
    private String requestor;
    private String assignee;

    public MTask() {
        // Default constructor required for calls to DataSnapshot.getValue(MTask.class)
    }

    public MTask(String key) {
        this.key = key;

    }

    public MTask(String key, String theMap) {
        this.key = key;
        // Now take apart the MAP
    }


    public MTask(String key, Map<String, String> theMap) {
        this.key = key;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        // Now take apart the MAP

        for (Map.Entry<String, String> e : theMap.entrySet()){
            String myKey="", myValue="";

            //Log.d("MPR-MTASK-MAP",(e.getKey() + ": " + e.getValue()));

            if (e.getKey().equals("Project"))
            {
              //  Log.d("MPR-MTASK-MAP","Setting Project Name");
                this.setProject(e.getValue());
            }


            if (e.getKey().equals("TaskName"))
            {
              //  Log.d("MPR-MTASK-MAP","Setting Task Name");
                this.setTaskName(e.getValue());
            }

            if (e.getKey().equals("CompletionDate"))
            {
                //  Log.d("MPR-MTASK-MAP","Setting Task Name");
                try {
                    Date aDate = df.parse(e.getValue());
                    this.setCompletionDate(aDate);
                }
                catch (ParseException ex) {
                    Log.d("MPR-MTASK-EXCEPTION",ex.toString());
                }
            }

            if (e.getKey().equals("CreationDate"))
            {
                //  Log.d("MPR-MTASK-MAP","Setting Task Name");
                //Date creationDate
                try {
                Date aDate = df.parse(e.getValue());
                this.setCreationDate(aDate);
                }
                catch (ParseException ex) {
                    Log.d("MPR-MTASK-EXCEPTION",ex.toString());
                }
            }



        }


    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }


    @Exclude
    public Map<String, Object> toMap() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        HashMap<String, Object> result = new HashMap<>();
       // result.put("uid", key);
        result.put("TaskName", taskName);
        result.put("CreationDate", df.format(creationDate));
        result.put("CompletionDate", df.format(completionDate));
        result.put("Project", project);
        result.put("Requestor", requestor);
        result.put("Assignee", assignee);

        return result;
    }

}

