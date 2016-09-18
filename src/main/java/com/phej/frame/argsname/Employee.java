package com.phej.frame.argsname;

import java.util.Date;

public class Employee implements AuditAware {
    private String name;
    private int age;
    private Date changed;

    public void setAge(int a) {
        System.out.println("setAge(" + a + ") called");
        this.age = a;
    }

    public void setName(String name) {
        System.out.println("setName(" + name + ") called");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Date getChanged() {
        return changed;
    }

    public Object someSecuredInfo() {
        return new Object();
    }
}