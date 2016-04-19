package com.mohib.khan.vtrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText name,phone,password,emaill;
    Spinner city;
    private Spinner spinner2;
    Button register;
    List<Data> list = new ArrayList<Data>();
    Databasehelper db;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        db = new Databasehelper(getApplicationContext());
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        emaill = (EditText) findViewById(R.id.email);
        city = (Spinner) findViewById(R.id.spinner2);
        register = (Button) findViewById(R.id.register);

        tv = (TextView) findViewById(R.id.tv);
        register.setOnClickListener(this);

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
        list = db.getAllStudentsList();
        print(list);

    }

    private void addItemsOnSpinner2() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Karachi");
        list.add("Lahore");
        list.add("Islamabad");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }
    private void addListenerOnButton() {

    }
    private void addListenerOnSpinnerItemSelection() {

    }

    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.register)) {
            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
            Data data = new Data();
            data.name = name.getText().toString();
            data.password = password.getText().toString();
            data.email = emaill.getText().toString();
            //data.city = spinner2.getText().toString();
            data.city = spinner2.getItemAtPosition(0).toString();
            String user_ph = phone.getText().toString();
            data.phone_no = user_ph;
            //data.phone_no = Integer.parseInt(user_ph);
            db.addStudentDetail(data);
            list = db.getAllStudentsList();//signup
            print(list);
        } else if (v == findViewById(R.id.textView4))
            startActivity(new Intent(this, MainActivity.class));

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
