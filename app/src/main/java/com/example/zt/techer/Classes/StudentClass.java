package com.example.zt.techer.Classes;

import java.io.Serializable;

/**
 * Created by zt on 28/03/18.
 */

public class StudentClass implements Serializable {
    public StudentClass(String name) {
        Name = name;
    }

    private String Name;

    public StudentClass(String name, String uid) {
        Name = name;
        Uid = uid;
    }

    private String Uid;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }




}
