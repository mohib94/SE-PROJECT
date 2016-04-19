package com.mohib.khan.vtrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class vehicleLogin extends AppCompatActivity implements View.OnClickListener{

    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String gps = "gps";
    public static final String reg = "reg";
    public static final String pass = "pass";
    public static final String owner = "owner";
    public static final String make = "make";
    public static final String model = "model";
    public static final String owner_cel = "owner_cel";

        SharedPreferences sharedpreferences;
        EditText regno,gpsno,password;
        Button login;
        Databasehelper db;
public static String TAG = "tag";
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_login);

        db = new Databasehelper(getApplicationContext());
        regno = (EditText) findViewById(R.id.editText9);//username
        gpsno = (EditText) findViewById(R.id.editText10);//phone
        password = (EditText) findViewById(R.id.editText11);//password
        login = (Button) findViewById(R.id.button2);



        }
public void serve(View v)
        {
        Intent i=new Intent();
        i.setClass(this,Register.class);
        startActivity(i);
        }



@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }


@Override
public void onClick(View v) {

        if (v == findViewById(R.id.button2)) {
                if (gpsno.getText().length() == 0 && password.getText().length() == 0 && regno.getText().length() == 0) {
                        // show phone toast
                        Toast.makeText(vehicleLogin.this, "Please Enter Registration No , GPS Cell and Password ", Toast.LENGTH_LONG).show();
                } else if (gpsno.getText().length() == 0) {
                        //show password toast
                        Toast.makeText(vehicleLogin.this, "Please Enter GPS CELL", Toast.LENGTH_LONG).show();
                } else if (regno.getText().length() == 0) {
                        //show password toast
                        Toast.makeText(vehicleLogin.this, "Please Enter Username", Toast.LENGTH_LONG).show();
                } else if (password.getText().length() == 0) {
                        //show password toast
                        Toast.makeText(vehicleLogin.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                } else {


                        Data data = new Data();
                        String[] str = new String[7];
                        String[] str2 = new String[7];
                        String pass = password.getText().toString();
                        str[0] = password.getText().toString();
                        //data.password = password.getText().toString();
                        //           data.password = password.getText().toString();
                        String u_name = regno.getText().toString();
                        str[1] = regno.getText().toString();
                        //data.name = username.getText().toString();

                        String login_ph = gpsno.getText().toString();
                        str[2] = gpsno.getText().toString();
                        //data.phone_no = phone.getText().toString();
                        //          data.phone_no = Integer.parseInt(login_ph);

                        String phone_no = login_ph;
                        str[3] = null;
                        str[4] = null;
                        str[5] = null;
                        str[6] = null;

                        //int phone_no = Integer.parseInt(login_ph);
                        //db.addStudentDetail(data);
                        str2 = db.Veh_logincheck(str);

                        if (str2[0].equals("empty") && str2[1].equals("empty1")) {

                                Toast.makeText(vehicleLogin.this, "No Record Found", Toast.LENGTH_LONG).show();
                        } else if (str2[0].equals("empty") && str2[1].equals(str[1])) {
                                //if (!db.logincheck(pass, phone_no,u_name)) {
                                //if(!db.phone_no){

                                Toast.makeText(vehicleLogin.this, "Invalid Password", Toast.LENGTH_LONG).show();
                        } else if (str[0].equals(str2[0]) && str2[1].equals("empty1")) {
                                //if (!db.logincheck(pass, phone_no,u_name)) {
                                //if(!db.phone_no){

                                Toast.makeText(vehicleLogin.this, "Invalid Registration No", Toast.LENGTH_LONG).show();
                        }

                        //else if (db.logincheck(pass, phone_no,u_name)) {
                        //else if (db.logincheck(data)) {
                        else if (str[0].equals(str2[0]) && str[1].equals(str2[1])) {

                                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(gps, gpsno.getText().toString());
                                editor.putString(pass, password.getText().toString());
                                editor.putString(reg, str[1]);
                                editor.putString(owner, str[3]);
                                editor.putString(make, str[4]);
                                editor.putString(model, str[5]);
                                editor.putString(owner_cel, str[6]);
                                editor.commit();// commit is important here.
                                //Log.d(TAG, "Password2 : " + str2[0] + "Name2 : " + str2[1] + "Password : " + str[0] + "Name : " + str[1]);
                                //  startActivity(new Intent(this, Main2Activity.class));
                                //Message Sending to Vehicle's Cell
                                String nine2 = "+92";
                                String phoneNumber = "+" + gpsno.getText().toString();//nine2+((EditText)
                                //findViewById(R.id.editView1)).getText().toString();
                                //String message = ((EditText) findViewById(R.id.editText)).getText().toString();
                                try {
                                        SmsManager.getDefault().sendTextMessage(phoneNumber, null, "track", null, null);
                                } catch (Exception e) {
                                        AlertDialog.Builder alertDialogBuilder = new
                                                AlertDialog.Builder(this);
                                        AlertDialog dialog = alertDialogBuilder.create();


                                        dialog.setMessage(e.getMessage());


                                        dialog.show();
                                }
                                        //Message sending code end
                                startActivity(new Intent(this, MapsActivity.class));



                        }
                }
                if (v == findViewById(R.id.register))
                        startActivity(new Intent(this, Register.class));
        }

}

}
