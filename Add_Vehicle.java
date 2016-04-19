package com.mohib.khan.vtrack;

import android.location.Address;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_Vehicle extends AppCompatActivity implements View.OnClickListener  {


    EditText gps,make,model,owner_cel,pass,owner,reg,delete_gps;
    Button add_vehicle,delete;
    List<Veh_Data> list = new ArrayList<Veh_Data>();
    Databasehelper db;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__vehicle);

        db = new Databasehelper(getApplicationContext());
        delete_gps =(EditText) findViewById(R.id.editText);
        gps = (EditText) findViewById(R.id.gps);
        reg = (EditText) findViewById(R.id.reg);
        pass = (EditText) findViewById(R.id.pass);
        owner = (EditText) findViewById(R.id.owner);
        make = (EditText) findViewById(R.id.make);
        model = (EditText) findViewById(R.id.model);
        owner_cel = (EditText) findViewById(R.id.year);
        add_vehicle = (Button) findViewById(R.id.button5);

        tv = (TextView) findViewById(R.id.tv);
        add_vehicle.setOnClickListener(this);

        //add some students
        Veh_Data data = new Veh_Data();
//         data.name = "Mohib";
//         data.phone_no = 12345;
//           data.password ="54321";
//         db.addStudentDetail(data);
        //  data.name = "Eric";
        // data.phone_no = 123456789;
        //data.password = "987654321";
        // db.addStudentDetail(data);
        list = db.getAllVehilesList();
        print(list);


    }

    public void onClick(View v) {

        if(v == v.findViewById(R.id.button5)){
            Toast.makeText(Add_Vehicle.this, "Vehicle Added successfully", Toast.LENGTH_SHORT).show();
            Veh_Data data = new Veh_Data();
            data.gps_cell = gps.getText().toString();
            data.regnum = reg.getText().toString();
            data.auto_pass = pass.getText().toString();
            //data.city = spinner2.getText().toString();
            data.ownerr = owner.getText().toString();
            String user_ph = make.getText().toString();
            data.makee = user_ph;
            data.modell = model.getText().toString();
            data.ownerr_cell = owner_cel.getText().toString();
                    //Integer.parseInt(year.getText().toString());
            //data.phone_no = Integer.parseInt(user_ph);
            db.addVehicleDetail(data);
            list = db.getAllVehilesList();//signup
            print(list);
        }
        else if(v ==findViewById(R.id.delete)){
            //Veh_Data data = new Veh_Data();
            String gps_cell = delete_gps.getText().toString();
            db.deleteVehicle(gps_cell);
            list = db.getAllVehilesList();//signup
            print(list);

        }

    }
    private void print(List<Veh_Data> list) {
        // TODO Auto-generated method stub
        String value = "";
        for(Veh_Data sms : list){
            value = value +"GPS: "+sms.gps_cell+", Reg No: "+sms.regnum+"\nPassword: "
                    +sms.auto_pass+ "Owner: "+sms.ownerr+"\nMake: "
                    +sms.makee+ "Model: "+sms.modell+ "\n";
        }
        tv.setText(value);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
