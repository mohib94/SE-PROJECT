package com.mohib.khan.vtrack;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFragment extends Fragment {
    public static final String MyPREFERENCES = "MPrefs" ;
    public static final String Name = "Name";
    TextView tvr;
    Button abut;
    //tvName.setText(host_name);
    SharedPreferences sharedpreferences;
    // this = your fragment
//    SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // abut = (Button)findViewById(R.id.button4);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv = null;
//        tvr = (TextView)this.getView().findViewById(R.id.textView5);

        String value = "";
        //sharedpreferences = getActivity().getSharedPreferences(
        //        MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String phone = (sharedpreferences.getString("Phone", "phone"));
        String password = (sharedpreferences.getString("Password", "password"));
        String username = (sharedpreferences.getString("UserName", "str[1]"));
        String email = (sharedpreferences.getString("Email", "str[3]"));
        String city = (sharedpreferences.getString("City", "str[4]"));
        //String phone = "something";
        value = value+"Phone : "+phone;
        //+"Password : "+password + "\nUserName : "+username+"Email : "+email+"City : "+city +"\n";
        Log.d("tag",value);
        //tvr.setText(value);
        //tvr.setText("Text from a fragment");
        //((TextView) this.getView().findViewById(R.id.textView5)).setText(value);
       // TextView t = (TextView) this.getView().findViewById(R.id.textView5);
        //t.setText(value);

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_main, container, false);


        //TextView text = (TextView) getActivity().findViewById(R.id.textView25);
      //text.setText(value);

        return v;
    }



}
