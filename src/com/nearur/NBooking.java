package com.nearur;


import java.util.ArrayList;
import java.util.Calendar;

public class NBooking {

    private final int threshold=10;
    private final int opening=8;
    private final int closing=23;
    NTable nTable=null;
    JDBC jdbc;

    public NBooking(){
        jdbc=new JDBC();
        jdbc.open();
    }

    public NTable book(int n,int time){

        boolean booking;

        booking=first(n);

        if(booking){
            nTable.setTime(String.valueOf(time));
            return nTable;
        }else{
            booking=second(n,time);
            if(booking){
                return nTable;
            }else{
                booking=merge(n,time);
                if(booking){
                    return nTable;
                }else{
                 return nTable;
                }
            }
        }
    }

    public boolean confirm(NUser nUser){
        boolean confirmation=false;

        int x=jdbc.confirm(nUser);
        if(x>0){
            confirmation=true;
        }
        return confirmation;
    }

    private boolean first(int n){
    ArrayList<NTable> nTables=jdbc.fetchfreeseats(n);
    if(nTables.size()>0) {
        int min = nTables.get(0).getSeats() - n, r = 0, idx = 0;
        for (int i = 1; i < nTables.size(); i++) {
            r = nTables.get(i).getSeats() - n;
            if (min > r) {
                min = r;
                idx = i;
            }
        }

        nTable = nTables.get(idx);
        nTable.setStatus(1);
        return true;
    }else{
        return false;
    }
    }
    private boolean second(int n,int t){

        ArrayList<NTable> nTableArrayList=jdbc.fetchalloted(n,t);
        if(nTableArrayList.size()>0) {
            int min = nTableArrayList.get(0).getSeats() - n, r = 0, idx = 0;
            for (int i = 1; i < nTableArrayList.size(); i++) {
                r = nTableArrayList.get(i).getSeats() - n;
                if (min > r) {
                    min = r;
                    idx = i;
                }
            }

            nTable = nTableArrayList.get(idx);
            nTable.setStatus(1);
            return true;
        }else{
            return false;
        }

    }

    private boolean merge(int n,int t){
        ArrayList<NTable>nTableArrayList=jdbc.fetchallfree(t);
        if(nTableArrayList.size()>0) {
            int idx = 0;
            NTable nTable2;
            for(int i=0;i<nTableArrayList.size();i++){
                if(nTableArrayList.get(i).getMerger()!=null) {
                    String[] ch = nTableArrayList.get(i).getMerger().split(",");
                    for (String c : ch) {
                        if ((nTable2 = contain(nTableArrayList, Integer.parseInt(c))) != null) {
                            if (nTableArrayList.get(i).getSeats() + nTable2.getSeats() >= n) {
                                idx = i;
                                nTableArrayList.get(idx).mTable = nTable2;
                                nTable = nTableArrayList.get(idx);
                                nTable.setStatus(2);
                                nTable2.setStatus(2);
                                break;
                            }
                        }
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }

    private NTable contain(ArrayList<NTable> nTableArrayList,int n){
        for(NTable nTable:nTableArrayList) {
            if (nTable.getId() == n) {
                return nTable;
            }
        }
        return null;
    }
}
