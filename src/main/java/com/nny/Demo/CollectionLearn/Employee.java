package com.nny.Demo.CollectionLearn;

import java.util.Date;

/**
 * 员工
 */
public class Employee implements Comparable<Employee>{

    private String name;
    private int number;
    private Date hireDate;//雇佣日期

    @Override
    public int compareTo(Employee o) {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
