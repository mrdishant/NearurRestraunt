package com.nearur;


import java.util.*;

public class DriverMain {

   static NTable nTable;
   static NUser nUser;
   static NBooking nBooking;

    public static void main(String[] nt){
        nUser=new NUser();
        nBooking=new NBooking();
        Scanner scanner=new Scanner(System.in);
        System.out.println("--Welcome to Nearur Restraunt--");

        System.out.print("Enter Number of Seats Required : ");
        int n=scanner.nextInt();
        System.out.print("Enter Time(24 hr format) : ");
        int t=scanner.nextInt();

        System.out.println("Fetching Details.....");
        nTable=nBooking.book(n,t);

        if(nTable!=null){

           // System.out.print("FullName : ");
            nUser.setName("Naina");
           // System.out.print("Mobile Number : ");
            nUser.setMobile(9023074222l);
            nUser.setAllotedtable(nTable);
            nUser.setSeats(n);
            Calendar c=Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,t);
            nUser.setTime(c.getTime().toString());

            if(nBooking.confirm(nUser)){
                nTable.setTime(String.valueOf(t));
                nBooking.jdbc.update(nTable);

                if(nTable.mTable!=null){
                nTable.mTable.setTime(String.valueOf(t));
                nBooking.jdbc.update(nTable.mTable);
                }

                System.out.println("Reservation Confirmed Table number "+nTable.getId()+" At "+nTable.getTime()+" O'Clock");
                System.out.println("--Thanks for Visiting Nearur--");
            }else {
                System.out.println("--Sorry, some error occured please try again!!--");
            }
        }else{
            System.out.println("No Table Available Please try With different Timings....");
        }
        nBooking.jdbc.close();
    }
}
