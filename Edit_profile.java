package com.mohib.khan.vtrack;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Edit_profile extends AppCompatActivity implements View.OnClickListener   {

    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Name = "Name";
    SharedPreferences sharedpreferences;

    EditText username,password,email;
    Button edit;
    List<Veh_Data> list = new ArrayList<Veh_Data>();
    Databasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new Databasehelper(getApplicationContext());
        username = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText4);
        edit = (Button) findViewById(R.id.button);
        edit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.button)){
            Toast.makeText(Edit_profile.this, "Successfully Edited", Toast.LENGTH_SHORT).show();

            Data data = new Data();
            String name = username.getText().toString();
            String pass = password.getText().toString();
            String emaiil = email.getText().toString();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String phone = (sharedpreferences.getString("Phone", "phone"));
            String password = (sharedpreferences.getString("Password", "password"));
            String username = (sharedpreferences.getString("UserName", "str[1]"));
            String email = (sharedpreferences.getString("Email", "str[3]"));

            if(password.equals(pass) || pass.equals(""))
                data.password=password;
            else
                data.password=pass;
            if(username.equals(name) || name.equals(""))
                data.name=username;
            else
                data.name = name;
            if(emaiil.equals(email) || emaiil.equals(""))
                data.email=emaiil;
            else
                data.email=email;
            //Integer.parseInt(year.getText().toString());
            //data.phone_no = Integer.parseInt(user_ph);
            db.updateEntry(data,phone);
            //list = db.getAllVehilesList();//signup
            //print(list);
        }
    }
}
