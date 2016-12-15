package com.michaelpreilly.apps.mtodo;

/**
 * Created by dad on 12/15/16.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
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

    public MTask(String key, String taskName) {
        this.key = key;
        this.taskName = taskName;
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

