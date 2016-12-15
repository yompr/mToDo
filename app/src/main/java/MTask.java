import android.provider.ContactsContract;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dad on 11/26/16.
 */
@IgnoreExtraProperties

public class MTask {
    private String uid;
    private String taskName;
    private Date creationDate;
    private Date completionDate;
    private String  project;
    private String requestor;
    private String assignee;

    public MTask(String uid, String taskName) {
        this.uid = uid;
        this.taskName = taskName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("TaskName", taskName);
        result.put("CreationDate", creationDate);
        result.put("CompletionDate", completionDate);
        result.put("Project", project);
        result.put("Requestor", requestor);
        result.put("Assignee", assignee);

        return result;
    }

}
