package com.mohib.khan.vtrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Phone = "Phone";
    public static final String Password = "Password";
    public static final String UserName = "UserName";
    public static final String Email = "Email";
    public static final String City = "City";

    SharedPreferences sharedpreferences;
    EditText username,phone,password;
    Button login;
    Databasehelper db;
    public static String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Databasehelper(getApplicationContext());
        username = (EditText) findViewById(R.id.editText14);
        phone = (EditText) findViewById(R.id.phone_frag);
        password = (EditText) findViewById(R.id.password_frag);
        login = (Button) findViewById(R.id.login_frag);



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

        if(v == findViewById(R.id.login_frag)) {
            if (phone.getText().length() == 0 && password.getText().length() == 0 && username.getText().length() == 0) {
                // show phone toast
                Toast.makeText(MainActivity.this, "Please Enter Phone,Username and Password ", Toast.LENGTH_LONG).show();
            }
            else if (phone.getText().length() == 0) {
                //show password toast
                Toast.makeText(MainActivity.this, "Please Enter Phone", Toast.LENGTH_LONG).show();
            }
            else if (username.getText().length() == 0) {
                //show password toast
                Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_LONG).show();
            }
            else if (password.getText().length() == 0) {
                //show password toast
                Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_LONG).show();
            }
            else {


                Data data = new Data();
                String[] str = new String[5];
                String[] str2 = new String[5];
                String pass = password.getText().toString();
                str[0] = password.getText().toString();
                //data.password = password.getText().toString();
                //           data.password = password.getText().toString();
                String u_name = username.getText().toString();
                str[1] = username.getText().toString();
                //data.name = username.getText().toString();

                String login_ph = phone.getText().toString();
                str[2] = phone.getText().toString();
                //data.phone_no = phone.getText().toString();
                //          data.phone_no = Integer.parseInt(login_ph);

                String phone_no =login_ph;
                str[3]=null;
                str[4]=null;
                //int phone_no = Integer.parseInt(login_ph);
                //db.addStudentDetail(data);
                str2 = db.logincheck(str);

                if(str2[0].equals("empty") && str2[1].equals("empty1"))
                {

                    Toast.makeText(MainActivity.this, "No Record Found", Toast.LENGTH_LONG).show();
                }
                else if(str2[0].equals("empty") && str2[1].equals(str[1])){
                    //if (!db.logincheck(pass, phone_no,u_name)) {
                    //if(!db.phone_no){

                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                }
                else if(str[0].equals(str2[0]) && str2[1].equals("empty1")){
                    //if (!db.logincheck(pass, phone_no,u_name)) {
                    //if(!db.phone_no){

                    Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_LONG).show();
                }

               //else if (db.logincheck(pass, phone_no,u_name)) {
                //else if (db.logincheck(data)) {
                else if(str[0].equals(str2[0]) && str[1].equals(str2[1])){

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Phone, phone.getText().toString());
                    editor.putString(Password, password.getText().toString());
                    editor.putString(UserName,str[1]);
                    editor.putString(Email,str[3]);
                    editor.putString(City,str[4]);
                    editor.commit();// commit is important here.
                    Log.d(TAG, "Password2 : " + str2[0] + "Name2 : " + str2[1] + "Password : " + str[0] + "Name : " + str[1]);
                 //  startActivity(new Intent(this, Main2Activity.class));
                     startActivity(new Intent(this, CardViewActivity.class));

                }

            }
        }
        if(v == findViewById(R.id.register))
            startActivity(new Intent(this,Register.class));
    }
}
