package com.example.elms;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class LeaveTable {
    private String reason;
    private String time;
    private int days;
    private String date;
    private String status;
    private String empName;

    public LeaveTable(String empName, String empDepartment, String empReason, String empLeaveType, String empDate, String empTime) {
        this.empName = empName;
        this.empDepartment = empDepartment;
        this.empLeaveType = empLeaveType;
        this.empDate = empDate;
        this.empReason = empReason;
        this.empTime = empTime;
    }

    private String empDepartment;
    private String empLeaveType;
    private String empDate;
    private String empReason;
    private String empTime;


    public LeaveTable(String reason, String time, int days, String status, String date) {
        this.reason = reason;
        this.time = time;
        this.days = days;
        this.status = status;
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public String getTime() {
        return time;
    }

    public int getDays() {
        return days;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public String getEmpLeaveType() {
        return empLeaveType;
    }

    public String getEmpDate() {
        return empDate;
    }

    public String getEmpReason() {
        return empReason;
    }

    public String getEmpTime() {
        return empTime;
    }
}
