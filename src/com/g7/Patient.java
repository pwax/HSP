package com.g7;

/**
 * Created by Curtis on 10/26/2015.
 */
public class Patient {

    public String name;
    public int id;
    public String insurance;

    public Patient (String inName, int inId){
        this.name = inName;
        this.id = inId;
    }

    public Patient (String inName, int inId, String inInsurance){
        this.name = inName;
        this.id = inId;
        this.insurance = inInsurance;
    }
}
