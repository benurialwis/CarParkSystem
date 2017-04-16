/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

import java.text.SimpleDateFormat;

public class DateTime {

    /*get current time from the system*/
    public long getCurrentTime() {
        long milliTime = System.currentTimeMillis();
        return milliTime;
    }

    /*get time in simple date format*/
    public String[] displayTime() {
        String[] time = new String[2];
        long milliTime = getCurrentTime(); //get system time
        SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd,yyyy"); //define date format
        String currentDate = sdfDate.format(milliTime);
        time[0] = currentDate; //store date in array
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm"); //define time format
        String currentTime = sdfTime.format(milliTime);
        time[1] = currentTime; //store time in array
        return time;
    }

    /*calculate parked duration of vehicle*/
    public int[] calculateTime(long entryTime, long leaveTime) {
        int time[] = new int[2];
        long duration = leaveTime - entryTime; //calculate duration

        //convert in to miniutes and hours
        int sec = (int) (duration / 1000 % 60);
        int min = (int) (duration / (1000 * 60) % 60);
        time[0] = min;
        int hr = (int) (duration / (1000 * 60 * 60) % 60);
        time[1] = hr;
        int dy = (int) (duration / (1000 * 60 * 60 * 24) % 24);
        //int yr = (int) (duration / (1000 * 60 * 60 * 24 * 365) % 365);
        System.out.println("Duration: " + dy + " days " + hr
                + " hours " + min + " minutes " + sec + " seconds");
        return time;
    }

}
