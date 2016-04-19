package com.mohib.khan.vtrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class GetAddress extends AppCompatActivity {

    TextView Gettext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);

        Gettext = (TextView) findViewById(R.id.gettext);
        MapsActivity ma = new MapsActivity();
    String value =  ma.getCompleteAddressString(1,1);//these values will be sent from the text message from vehicle's cell to client's android app
    Gettext.setText(value);


    }
}
