package com.mohib.khan.vtrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Name = "Name";
    SharedPreferences sharedpreferences;
    List<Data> list = new ArrayList<Data>();
    Databasehelper db;
    Button edit;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new Databasehelper(getApplicationContext());

        tv = (TextView) findViewById(R.id.textView5);
        edit = (Button) findViewById(R.id.edit);
        //add some students
        Data data = new Data();
//         data.name = "Mohib";
//         data.phone_no = 12345;
//           data.password ="54321";
//         db.addStudentDetail(data);
        //  data.name = "Eric";
        // data.phone_no = 123456789;
        //data.password = "987654321";
        // db.addStudentDetail(data);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String phone = (sharedpreferences.getString("Phone", "phone"));
        String password = (sharedpreferences.getString("Password", "password"));
        String username = (sharedpreferences.getString("UserName", "str[1]"));
        String email = (sharedpreferences.getString("Email", "str[3]"));
        String city = (sharedpreferences).getString("City", "str[4]");
        String value="";
        value=value+"\nCell No :  "+phone+"\n\nUsername :  "+username+"\n\nEmail :  "+email+"\n\nCity :  "+city;
        tv.setText(value);
        //list = db.getAllStudentsList();
        //print(list);

    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.edit))
            startActivity(new Intent(this,Edit_profile.class));
    }
    private void print(List<Data> list) {
        // TODO Auto-generated method stub
        String value = "";
        for(Data sm : list){
            value = value +"Phone: "+sm.phone_no+", Name: "+sm.name+" Password: "+sm.password+ "email: "+sm.email+"city: "+sm.city+"\n";
        }
        tv.setText(value);
    }
}
