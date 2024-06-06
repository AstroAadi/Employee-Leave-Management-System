package com.example.elms;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    public static Connection connectDb(){
        try{

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "54321");
        }
        catch(Exception e){e.printStackTrace();}
        return null;
    }

    public static ObservableList<LeaveTable> getDataLeave(){
        Connection conn = connectDb();
        ObservableList<LeaveTable> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from emp_leaves");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new LeaveTable( rs.getString("reason"), rs.getString("time"), Integer.parseInt(rs.getString("days")), rs.getString("status"), rs.getString("date") ));
            }
        }catch (Exception e){}
        return list;
    }

    public static ObservableList<LeaveTable> getEmpLeave(){
        Connection conn2 = connectDb();
        ObservableList<LeaveTable> list2 = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn2.prepareStatement("select * from admin_dashboard");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list2.add(new LeaveTable( rs.getString("name"), rs.getString("department"), rs.getString("reason"), rs.getString("typeOfLeave"), rs.getString("date"), rs.getString("time") ));
            }
        }catch (Exception e){}
        return list2;
    }

}