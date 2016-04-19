package com.mohib.khan.vtrack;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Vehicle_Frag extends Fragment implements View.OnClickListener {


    EditText gps,make,model,year,pass,owner,reg;
    Button add_vehicle;
    List<Veh_Data> list = new ArrayList<Veh_Data>();
    Databasehelper db;
    TextView tv;


    public Vehicle_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=null;
        //gps,make,model,year,pass,owner,reg
        db = new Databasehelper(getActivity().getApplicationContext());
        gps = (EditText) v.findViewById(R.id.gps);
        reg = (EditText) v.findViewById(R.id.reg);
        pass = (EditText) v.findViewById(R.id.pass);
        owner = (EditText) v.findViewById(R.id.owner);
        make = (EditText) v.findViewById(R.id.make);
        model = (EditText) v.findViewById(R.id.model);
        year = (EditText) v.findViewById(R.id.year);
        add_vehicle = (Button) v.findViewById(R.id.button5);

        tv = (TextView) v.findViewById(R.id.tv);
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_, container, false);
    }

    @Override
    public void onClick(View v) {

        if(v == v.findViewById(R.id.button5)){
            Veh_Data data = new Veh_Data();
            data.gps_cell = gps.getText().toString();
            data.regnum = reg.getText().toString();
            data.auto_pass = pass.getText().toString();
            //data.city = spinner2.getText().toString();
            data.ownerr = owner.toString();
            String user_ph = make.getText().toString();
            data.makee = user_ph;
            data.modell = model.getText().toString();


            //data.phone_no = Integer.parseInt(user_ph);
            db.addVehicleDetail(data);
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
                    +sms.makee+ "Model: "+sms.modell+"\nYear: "+sms.ownerr_cell+"\n";
        }
        tv.setText(value);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
