package com.g7;

/**
 * Created by Curtis on 10/29/2015.
 */
public class Prescription {

    public int userID;
    public String info;
    public int prescriptionID;

    public Prescription(int userID, String info, int prescriptionID){

        this.userID = userID;
        this.info = info;
        this.prescriptionID = prescriptionID;
    }
}
