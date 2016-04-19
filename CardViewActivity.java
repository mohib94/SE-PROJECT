package com.mohib.khan.vtrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case R.id.Add_Vehicle:
                startActivity(new Intent(this,Add_Vehicle.class));
                break;
            case R.id.Account:
                startActivity(new Intent(this,Account_Activity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this,Profile.class));
                break;
            case R.id.nav_reg_veh:
                startActivity(new Intent(this,Registered_Vehicles.class));
                break;
            case R.id.nav_logout:
                //action logout delete all sessions and goto MainActivity.java login page
                startActivity(new Intent(this,MainActivity.class));
                break;


        }



        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                startActivity(new Intent(CardViewActivity.this, vehicleLogin.class));
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        Databasehelper db;
        List<Veh_Data> list = new ArrayList<Veh_Data>();
        db = new Databasehelper(getApplicationContext());
        ArrayList results = new ArrayList<DataObject>();
        //String[] str ={"Mohib","Tahreem","Munazza","Usama","Monis","Nouman","Aman","Waqar"};
        //String[] str_reg= {"APL-877","AZL-875","ART-554","AGH-123","AXX-342","AWE-796","ABB-542","APP-421"};
        list = db.getAllVehilesList();//signup
        int loop_count=print(list);
        String value="";

        String[] owners = new String[loop_count];
        String[] Regs = new String[loop_count];
        String[] passwords = new String[loop_count];
        String[] makes = new String[loop_count];
        String[] models = new String[loop_count];
        String[] gpses = new String[loop_count];
        String[] owner_Celll = new String[loop_count];

        int i=0;
        for(Veh_Data sms : list) {
            owners[i] = sms.ownerr;
            Regs[i] = sms.regnum;
            passwords[i] = sms.auto_pass;
            makes[i] = sms.makee;
            models[i] = sms.modell;
            gpses[i] = sms.gps_cell;
            owner_Celll[i] = sms.ownerr_cell;
        i++;
        }


//            value = value +"GPS: "+sms.gps_cell+", Reg No: "+sms.regnum+"\nPassword: "
//                    +sms.auto_pass+ "Owner: "+sms.ownerr+"\nMake: "
//                    +sms.makee+ "Model: "+sms.modell+"\nYear: "+sms.yearr+"\n";


        for (int index = 0; index < loop_count; index++) {
            DataObject obj = new DataObject("Owner: " + owners[index],
                    "Reg-Num: " + Regs[index]);
            results.add(index, obj);
        }
        return results;
    }
    private int print(List<Veh_Data> list) {
        // TODO Auto-generated method stub
        ArrayList results = new ArrayList<DataObject>();
        String value = "";
        int size = list.size();
        int index=0;
        for(Veh_Data sms : list) {
            index++;

        }
//            value = value +"GPS: "+sms.gps_cell+", Reg No: "+sms.regnum+"\nPassword: "
//                    +sms.auto_pass+ "Owner: "+sms.ownerr+"\nMake: "
//                    +sms.makee+ "Model: "+sms.modell+"\nYear: "+sms.yearr+"\n";



        //tv.setText(value);
        return index;
    }
}