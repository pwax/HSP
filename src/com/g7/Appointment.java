package com.g7;

/**
 * Created by perry_13 on 10/11/15.
 */
public class Appointment {

    public int userID;
    public String info;
    public int appointmentID;
    public int doctorID;

    public Appointment(int userID, String info, int appointmentID, int doctorID){
        this.userID = userID;
        this.info = info;
        this.appointmentID = appointmentID;
        this.doctorID = doctorID;
    }


}
