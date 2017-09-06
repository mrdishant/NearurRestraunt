package com.nearur;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {

    Connection con;
    PreparedStatement pmt;
    ResultSet resultSet;


    public JDBC(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void open(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost/restraunt","root","naina");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(NTable table){
        int i=0;
        String sql="update nearurtable set status=? , timing=? where id=?";
        try {
            pmt=con.prepareStatement(sql);
            pmt.setInt(1,table.getStatus());
            pmt.setInt(2,Integer.parseInt(table.getTime()));
            pmt.setInt(3,table.getId());
            i=pmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  i;
    }

    public ArrayList<NTable> fetchallfree(int t){
        ArrayList<NTable> arrayList=new ArrayList<>();

        String sql="select * from nearurtable where timing<=?";
        try {
            pmt=con.prepareStatement(sql);
            pmt.setInt(1,t-2);
            resultSet=pmt.executeQuery();

            while (resultSet.next()){
                arrayList.add(new NTable(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),String.valueOf(resultSet.getInt(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;
    }

    public ArrayList<NTable> fetchalloted(int n,int t){
        ArrayList<NTable> arrayList=new ArrayList<>();

        String sql="select * from nearurtable where status=? and seats>=? and timing<=?";
        try {
            pmt=con.prepareStatement(sql);
            pmt.setInt(1,1);
            pmt.setInt(2,n);
            pmt.setInt(3,t-2);
            resultSet=pmt.executeQuery();

            while (resultSet.next()){
                arrayList.add(new NTable(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),String.valueOf(resultSet.getInt(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;
    }


    public ArrayList<NTable> fetchfreeseats(int n){
        ArrayList<NTable> arrayList=new ArrayList<>();

        String sql="select * from nearurtable where status=? and seats >= ?";
        try {
            pmt=con.prepareStatement(sql);
            pmt.setInt(1,0);
            pmt.setInt(2,n);
            resultSet=pmt.executeQuery();

            while (resultSet.next()){
                arrayList.add(new NTable(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),String.valueOf(resultSet.getInt(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;
    }

    public int confirm(NUser nUser){
        int i=0;

        String sql="insert into nearurusers (Name,Phone,Seats,TableAlloted,timebook) values(?,?,?,?,?)";
        try {
            pmt=con.prepareStatement(sql);
            pmt.setString(1,nUser.getName());
            pmt.setLong(2,nUser.getMobile());
            pmt.setInt(3,nUser.getSeats());
            pmt.setInt(4,nUser.getAllotedtable().getId());
            pmt.setString(5,nUser.getTime());
            i=pmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
