package com.michaelpreilly.apps.mtodo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dad on 12/15/16.
 */

public class Project {


    private String name;
    private String description;

    public Project(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    public Project(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return description;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Description", description);
        return result;
    }

}
