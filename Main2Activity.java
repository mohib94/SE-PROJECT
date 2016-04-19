package com.mohib.khan.vtrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Name = "Name";
    TextView tv;
    Button abut;
    //tvName.setText(host_name);
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView) findViewById(R.id.textView22);
        abut = (Button) findViewById(R.id.button4);

        String value = "";
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String phone = (sharedpreferences.getString("Phone", "phone"));
        String password = (sharedpreferences.getString("Password", "password"));
        String username = (sharedpreferences.getString("UserName", "str[1]"));
        String email = (sharedpreferences.getString("Email", "str[3]"));
        String city = (sharedpreferences.getString("City", "str[4]"));
        //String phone = "something";
        value = value+"Phone : "+phone+"Password : "+password + "\nUserName : "+username+"Email : "+email+"City : "+city +"\n";
        tv.setText(value);
    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == findViewById(R.id.button4)) {
            startActivity(new Intent(this, afterLogin.class));
        }

    }
}
