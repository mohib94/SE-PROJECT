package com.mohib.khan.vtrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class afterLogin extends ActionBarActivity {
    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Name = "Name";
    TextView tv;
    Button abut;
    //tvName.setText(host_name);
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        tv = (TextView) findViewById(R.id.textView16);
        abut = (Button) findViewById(R.id.button3);
        String value = "";
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String phone = (sharedpreferences.getString("Phone", "phone"));
        String password = (sharedpreferences.getString("Password", "password"));
        //String phone = "something";
            value = value+"Phone : "+phone+"Password : "+password+"\n";
        tv.setText(value);

    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == findViewById(R.id.button3)) {
            startActivity(new Intent(this, Main2Activity.class));
        }

    }
    @Override
    protected void onResume() {
        super.onResume();

    }


}