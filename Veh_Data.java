package com.mohib.khan.vtrack;

/**
 * Created by khan on 4/17/2016.
 */
public class Veh_Data {

    public String gps_cell;
    public String regnum;
    public String auto_pass;
    public String ownerr;
    public String makee;
    public String modell;
    public String ownerr_cell;
    public Veh_Data(String gps_no, String regnum, String car_pass, String owner,String make,String model,String owner_mobile) {
        // TODO Auto-generated constructor stub

        this.gps_cell = gps_no;
        this.regnum = regnum;
        this.auto_pass = car_pass;
        this.ownerr = owner;
        this.makee = make;
        this.modell = model;
        this.ownerr_cell=owner_mobile;
    }
    public Veh_Data(){

    }
}


