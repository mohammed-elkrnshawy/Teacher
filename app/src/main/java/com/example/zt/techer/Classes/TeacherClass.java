package com.example.zt.techer.Classes;

import java.io.Serializable;

/**
 * Created by zt on 28/03/18.
 */

public class TeacherClass implements Serializable {

    private String Name;
    private String Uid;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public TeacherClass(String name, String password) {
        Name = name;
        Password = password;
    }

    public TeacherClass(String name, String password, String uid) {
        Name = name;
        Uid = uid;
        Password = password;
    }

    private String Password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
