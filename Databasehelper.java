package com.mohib.khan.vtrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khan on 3/12/2016.
 */
public class Databasehelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "vtrack_database";

    // Current version of database
    private static final int DATABASE_VERSION = 5;

    // Name of table
    private static final String TABLE_USER = "vtrack_users";
    private static final String TABLE_VEHICLE = "vtrack_vehicles";
    // All Keys used in table_user
    private static final String KEY_PHONE = "phone_no";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CITY = "city";
    //All keys used in table_vehicles
    private static final String KEY_OWNER = "owner";
    private static final String KEY_OWNER_CELL = "owner_cell";
    private static final String KEY_MAKE = "make";
    private static final String KEY_MODEL = "model";
    private static final String KEY_REG = "reg";
    private static final String KEY_GPS_CELL = "gps_cell";
    private static final String KEY_CAR_PASS = "car_pass";



    public static String TAG = "tag";

    // Students Table Create Query
    /**
     * CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name
     * TEXT,phone_number TEXT);
     */

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_PHONE
            + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_CITY + " TEXT );";

    private static final String CREATE_TABLE_VEHICLE = "CREATE TABLE "
            + TABLE_VEHICLE + "(" + KEY_GPS_CELL
            + " TEXT PRIMARY KEY," + KEY_REG + " TEXT,"
            + KEY_CAR_PASS + " TEXT,"
            + KEY_OWNER + " TEXT,"
            + KEY_MAKE + " TEXT,"
            + KEY_MODEL + " TEXT,"
            + KEY_OWNER_CELL + " TEXT );";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER); // create USERS table
        db.execSQL(CREATE_TABLE_VEHICLE); //create VEHICLES table

    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE); // drop table if exists

        onCreate(db);
    }


    public long addStudentDetail(Data data) {//signup
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values of table_users
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, data.name);
        values.put(KEY_PHONE, data.phone_no);
        values.put(KEY_PASSWORD, data.password);
        values.put(KEY_EMAIL, data.email);
        values.put(KEY_CITY, data.city);
        Log.i(TAG, "City : " + data.city + "Email : " + data.email + "Name : " + data.name);

        // insert row in students table

        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }
    public boolean deleteVehicle(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();


        return db.delete(TABLE_VEHICLE, KEY_GPS_CELL + "=" + rowId, null) > 0;

    }
    public long addVehicleDetail(Veh_Data v_data) {//Vehicle signup
        SQLiteDatabase db = this.getWritableDatabase();

        //Adding Vehicles in table_vehicle

        // Creating content values of table_users
        ContentValues values = new ContentValues();
        values.put(KEY_GPS_CELL, v_data.gps_cell);
        values.put(KEY_REG, v_data.regnum);
        values.put(KEY_CAR_PASS, v_data.auto_pass);
        values.put(KEY_OWNER, v_data.ownerr);
        values.put(KEY_MAKE, v_data.makee);
        values.put(KEY_MODEL, v_data.modell);
        values.put(KEY_OWNER_CELL, v_data.ownerr_cell);
        Log.i(TAG,"GPS Cell : " + v_data.gps_cell +"Reg Num : " + v_data.regnum + "Car Password : " + v_data.auto_pass
                + "Owner : " + v_data.ownerr + "Owner Mobile : " + v_data.ownerr_cell+ "Make : " + v_data.makee+
                "Model : " + v_data.modell  );

        // insert row in students table

        long insert = db.insert(TABLE_VEHICLE, null, values);

        return insert;
    }


    public int updateEntry(Data data,String phone) {//login
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();


        // update row in students table base on students.is value
        //tableuser,() values(),where KEY_PHONE "=" phone;
       // String where = KEY_PHONE +"=?";
       // String[] whereArgs = new String[] {String.valueOf(phone)};
       // db.update(TABLE_USER, "(KEY_EMAIL , KEY_PASSWORD ,
     //           KEY_NAME)" + "VALUES"+ (data.email , data.password , data.name ) +, where,whereArgs);
        //return db.update(TABLE_USER,(KEY_EMAIL,KEY_PASSWORD,KEY_NAME) + "VALUES" +(data.email,data.password,data.name)+ ","+ KEY_PHONE +
          //              " = "+ phone +","+
            //    new String[] { String.valueOf(data.phone_no) });
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMAIL, data.email);
        contentValues.put(KEY_PASSWORD, data.password);
        contentValues.put(KEY_NAME, data.name);
        return db.update(TABLE_USER, contentValues, KEY_PHONE + " =? ", new String[] { String.valueOf(phone) });
//        String strSQL = "UPDATE" + TABLE_USER + " SET " + KEY_EMAIL +"="+data.email+","+KEY_PASSWORD+"="
//                +data.password+","+KEY_NAME+"="+data.name +"WHERE"+ KEY_PHONE +" = "+ phone;
//            db.execSQL(strSQL);
//        //return db.execSQL(strSQL);

    }

    /*public void deleteEntry(long id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }*/

//    public boolean logincheck(String data,String phone , String username){
//        //public boolean logincheck(Data data){
//
//            SQLiteDatabase db = this.getReadableDatabase();
//
//        // SELECT * FROM students WHERE id = ?;
////        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
////        + KEY_PHONE + " = " + phone + " AND " + KEY_CITY + " = " + username;
////
//            String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
//        + KEY_PHONE + " = " + phone;
//
//                // Log.d(TAG, selectQuery);
//
//        //String[] arg={KEY_PHONE,KEY_CITY,KEY_EMAIL,KEY_NAME};
//        Cursor c = db.rawQuery(selectQuery, null);
//        //Cursor c=db.query(TABLE_USER,arg,KEY_PHONE + " = " + phone + " AND " + KEY_NAME + " = " + username,null,null,null,null);
//        if (c != null)
//            c.moveToFirst();
//
//        String phone_no = c.getString(c.getColumnIndex(KEY_PHONE));
//        String password = c.getString(c.getColumnIndex(KEY_PASSWORD));
//        String name = c.getString(c.getColumnIndex(KEY_NAME));
//        String e_mail = c.getString(c.getColumnIndex(KEY_EMAIL));
//        String c_ity = c.getString(c.getColumnIndex(KEY_CITY));
//        //phone.equals(phone_no) &&
//        //if(name.equals(username) &&  password.equals(data)) {
//            if(phone_no.equals(phone) && name.equals(username) &&  password.equals(data)) {
//
//                Log.d(TAG,"yes");
//            return true;
//
//        }
//            else if(!phone_no.equals(phone) || !name.equals(username) ||  !password.equals(data)){
//                Log.d(TAG,password + data + "  " +phone + phone_no );
//
//                return false;
//            }
//
//        else
////            Log.d(TAG,password + data + "  " +phone + phone_no);
//              Log.d(TAG,password + data + "  " +phone + phone_no);
//
//        return false;
//    }


// {
//        Data overdose = new Data();
//        Data find = new Data();
//        String[] returnit = new String[5];
//        overdose.name = username;
//        overdose.phone_no = phone;
//        overdose.password = data;
//
//        find=logincheck(overdose);
//        returnit[0] = find.phone_no;
//        returnit[1] = find.name;
//        returnit[2] = find.password;
//        returnit[3] = find.email;
//        returnit[4] = find.city;
//
//        return returnit;
//
//    }
//    {
//            public String[] logincheck(String data,String phone , String username){
    //Vehicle login method
public String[] Veh_logincheck(String[] str3){
    SQLiteDatabase db = this.getReadableDatabase();
    String phone= str3[2];//phone




    String selectQuery = "SELECT  * FROM " + TABLE_VEHICLE + " WHERE "
            + KEY_GPS_CELL + " = " + phone;

    Cursor c = db.rawQuery(selectQuery, null);


    if (c != null)
        c.moveToFirst();

    String gps_no = c.getString(c.getColumnIndex(KEY_GPS_CELL));
    String car_pass = c.getString(c.getColumnIndex(KEY_CAR_PASS));
    String regnum = c.getString(c.getColumnIndex(KEY_REG));
    String owwner = c.getString(c.getColumnIndex(KEY_OWNER));
    String maakee = c.getString(c.getColumnIndex(KEY_MAKE));
    String moodell = c.getString(c.getColumnIndex(KEY_MODEL));
    String owneer_cell = c.getString(c.getColumnIndex(KEY_OWNER_CELL));

    //phone.equals(phone_no) &&
    //if(name.equals(username) &&  password.equals(data)) {
    String data = str3[0];//password
    String username = str3[1];//username

    if(regnum.equals(username) &&  car_pass.equals(data)) {
//            data.city=c_ity;
//            data.email=e_mail;
//            data.phone_no=phone_no;
//            data.name=name;
//            data.password=password;
        str3[3] = owwner;//email
        str3[4] = maakee;//city
        str3[5] = moodell;//email
        str3[6] = owneer_cell;//email

        Log.d(TAG,"yes");
        return str3;

    }
    if(!regnum.equals(username) &&  car_pass.equals(data)){
        Log.d(TAG,"condition 1 if"+ car_pass + data + "  " +regnum + username );
        username="empty1";
        str3[1] = username;
        return str3;
    }
    if(regnum.equals(username) &&  !car_pass.equals(data)){
        Log.d(TAG,"condition 2 else if" + car_pass + data + "  " +regnum + username );
        data="empty";
        str3[0] = data; //password
        return str3;
    }

    if((!regnum.equals(username) && !car_pass.equals(data)) || (!phone.equals(gps_no))) {
        username = "empty1";
        data = "empty";
        str3[1] = username;
        str3[0] = data; //password
        Log.d(TAG, car_pass + data + "  " + phone + gps_no);
        Log.d(TAG, car_pass + data + "  " + phone + gps_no);
        return str3;

    }
    return str3;
}
    //client login method
    public String[] logincheck(String[] str3){
    SQLiteDatabase db = this.getReadableDatabase();
        String phone= str3[2];//phone



        // SELECT * FROM students WHERE id = ?;
//        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
//        + KEY_PHONE + " = " + phone + " AND " + KEY_CITY + " = " + username;
//
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_PHONE + " = " + phone;

        // Log.d(TAG, selectQuery);

        //String[] arg={KEY_PHONE,KEY_CITY,KEY_EMAIL,KEY_NAME};
        Cursor c = db.rawQuery(selectQuery, null);
        //Cursor c=db.query(TABLE_USER,arg,KEY_PHONE + " = " + phone + " AND " + KEY_NAME + " = " + username,null,null,null,null);
        if (c != null)
            c.moveToFirst();

        String phone_no = c.getString(c.getColumnIndex(KEY_PHONE));
        String password = c.getString(c.getColumnIndex(KEY_PASSWORD));
        String name = c.getString(c.getColumnIndex(KEY_NAME));
        String e_mail = c.getString(c.getColumnIndex(KEY_EMAIL));
        String c_ity = c.getString(c.getColumnIndex(KEY_CITY));
        //phone.equals(phone_no) &&
        //if(name.equals(username) &&  password.equals(data)) {
        String data = str3[0];//password
        String username = str3[1];//username

        if(name.equals(username) &&  password.equals(data)) {
//            data.city=c_ity;
//            data.email=e_mail;
//            data.phone_no=phone_no;
//            data.name=name;
//            data.password=password;
            str3[3] = e_mail;//email
            str3[4] = c_ity;//city
            Log.d(TAG,"yes");
            return str3;

        }
        if(!name.equals(username) &&  password.equals(data)){
            Log.d(TAG,"condition 1 if"+ password + data + "  " +name + username );
            username="empty1";
            str3[1] = username;
            return str3;
        }
        if(name.equals(username) &&  !password.equals(data)){
            Log.d(TAG,"condition 2 else if" + password + data + "  " +name + username );
            data="empty";
            str3[0] = data; //password
            return str3;
        }

        if((!name.equals(username) && !password.equals(data)) || (!phone.equals(phone_no))) {
            username = "empty1";
            data = "empty";
            str3[1] = username;
            str3[0] = data; //password
            Log.d(TAG, password + data + "  " + phone + phone_no);
            Log.d(TAG, password + data + "  " + phone + phone_no);
        return str3;

        }
        return str3;
    }
    public Data getStudent(Data data) {//login
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_PHONE + " = " + data.phone_no;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        data.phone_no = c.getString(c.getColumnIndex(KEY_PHONE));
        data.password = c.getString(c.getColumnIndex(KEY_PASSWORD));
        data.name = c.getString(c.getColumnIndex(KEY_NAME));
        data.email = c.getString(c.getColumnIndex(KEY_EMAIL));
        data.city = c.getString(c.getColumnIndex(KEY_CITY));

        return data;
    }

    //getting values of respective vehicle
    public List<Veh_Data> getVehicle(Veh_Data data) {//login
        //Veh_Data data= new Veh_Data();
        List<Veh_Data> userArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_VEHICLE + " WHERE "
                + KEY_OWNER_CELL + " = " + data.ownerr_cell;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                Veh_Data v_data = new Veh_Data();
                v_data.gps_cell = c.getString(c.getColumnIndex(KEY_GPS_CELL));
                v_data.regnum = c.getString(c
                        .getColumnIndex(KEY_REG));
                v_data.auto_pass = c.getString(c.getColumnIndex(KEY_CAR_PASS));
                v_data.ownerr = c.getString(c.getColumnIndex(KEY_OWNER));
                v_data.makee = c.getString(c.getColumnIndex(KEY_MAKE));
                v_data.modell = c.getString(c.getColumnIndex(KEY_MODEL));
                v_data.ownerr_cell = c.getString(c.getColumnIndex(KEY_OWNER_CELL));



                // adding to Students list
                userArrayList.add(v_data);
            } while (c.moveToNext());
        }

        return userArrayList;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * StudentsModel
     *
     * @return
     */
    //getting all vehicles through this method
    public List<Veh_Data> getAllVehilesList() {
        List<Veh_Data> userArrayList = new ArrayList<>();

        String selectQuery1 = "SELECT  * FROM " + TABLE_VEHICLE;
        Log.d(TAG, selectQuery1);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery1, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Veh_Data v_data = new Veh_Data();
                v_data.gps_cell = c.getString(c.getColumnIndex(KEY_GPS_CELL));
                v_data.regnum = c.getString(c
                        .getColumnIndex(KEY_REG));
                v_data.auto_pass = c.getString(c.getColumnIndex(KEY_CAR_PASS));
                v_data.ownerr = c.getString(c.getColumnIndex(KEY_OWNER));
                v_data.makee = c.getString(c.getColumnIndex(KEY_MAKE));
                v_data.modell = c.getString(c.getColumnIndex(KEY_MODEL));
                v_data.ownerr_cell = c.getString(c.getColumnIndex(KEY_OWNER_CELL));



                // adding to Students list
                userArrayList.add(v_data);
            } while (c.moveToNext());
        }

        return userArrayList;
    }


    public List<Data> getAllStudentsList() {
        List<Data> userArrayList = new ArrayList<Data>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Data data = new Data();
                data.phone_no = c.getString(c.getColumnIndex(KEY_PHONE));
                data.password = c.getString(c
                        .getColumnIndex(KEY_PASSWORD));
                data.name = c.getString(c.getColumnIndex(KEY_NAME));
                data.email = c.getString(c.getColumnIndex(KEY_EMAIL));
                data.city = c.getString(c.getColumnIndex(KEY_CITY));


                // adding to Students list
                userArrayList.add(data);
            } while (c.moveToNext());
        }

        return userArrayList;
    }

}
