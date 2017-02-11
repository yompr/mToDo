package com.michaelpreilly.apps.mtodo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dad on 1/2/17.
 */

public class Contact {

    private String key;
    private String contactName;
    private String emailAddr;
    private Integer importance;



    public Contact(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }


    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }


    public Contact(String key, Map<String, String> theMap) {
        this.key = key;

        // Now take apart the MAP

        for (Map.Entry<String, String> e : theMap.entrySet()) {
            String myKey = "", myValue = "";

            //Log.d("MPR-MTASK-MAP",(e.getKey() + ": " + e.getValue()));

            if (e.getKey().equals("contactName")) {
                //  Log.d("MPR-MTASK-MAP","Setting Project Name");
                this.setContactName(e.getValue());
            }

            if (e.getKey().equals("emailAddr")) {
                //  Log.d("MPR-MTASK-MAP","Setting Task Name");
                this.setEmailAddr(e.getValue());
            }
            if (e.getKey().equals("importance")) {
                //  Log.d("MPR-MTASK-MAP","Setting Task Name");
                this.setImportance(Integer.parseInt(e.getValue()));
            }
        }
    }

    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        // result.put("uid", key);
        result.put("contactName", contactName);
        result.put("emailAddr", emailAddr);
        result.put("importance", importance);

        return result;
    }

}
