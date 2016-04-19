package com.mohib.khan.vtrack;

/**
 * Created by khan on 3/12/2016.
 */
public class Data {

    public String name;
    public String phone_no;
    public String password;
    public String email;
    public String city;
    public Data(String name, String phone_no, String password,String ccity,String eemail) {
        // TODO Auto-generated constructor stub

        this.name = name;
        this.phone_no = phone_no;
        this.password = password;
        this.city=ccity;
        this.email=eemail;
    }
    public Data(){

    }
}
