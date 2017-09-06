package com.nearur;


//POJO BEAN
public class NTable {

    private int id;
    private int seats;
    private String merger;
    private int status;
    private String time;
    public NTable mTable;

    public NTable(int id, int seats, String merger, int status, String time) {
        this.id = id;
        this.seats = seats;
        this.merger = merger;
        this.status = status;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getMerger() {
        return merger;
    }

    public void setMerger(String merger) {
        this.merger = merger;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NTable{" +
                "id=" + id +
                ", seats=" + seats +
                ", merger='" + merger + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                '}';
    }
}
