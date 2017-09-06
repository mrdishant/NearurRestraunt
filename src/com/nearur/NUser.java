package com.nearur;

public class NUser {

    private String name;
    private long mobile;
    private int seats;
    private NTable allotedtable;
    private String time;

    public NUser(){

    }

    public NUser(String name, long mobile, int seats, NTable allotedtable, String time) {
        this.name = name;
        this.mobile = mobile;
        this.seats = seats;
        this.allotedtable = allotedtable;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public NTable getAllotedtable() {
        return allotedtable;
    }

    public void setAllotedtable(NTable allotedtable) {
        this.allotedtable = allotedtable;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NUser{" +
                "name='" + name + '\'' +
                ", mobile=" + mobile +
                ", seats=" + seats +
                ", allotedtable=" + allotedtable +
                ", time='" + time + '\'' +
                '}';
    }
}
