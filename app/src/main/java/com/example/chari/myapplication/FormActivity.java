package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chari on 7/17/2016.
 */


public class FormActivity extends AppCompatActivity {

    private Spinner sp_SelectIdPrf, sp_SelectProduct, SpinFlorNo;
    public static EditText edit_idNumber, edt_address1, edt_address2, edt_AgentCode, edt_State, edt_Distric, edt_CityName, edit_FromNo, edit_Name, AgentCode;
    public static CheckBox chk_flat, chk_land, chk_banglow, chk_other, chk_plot;
    private Editable GetAgencyCode;
    private Button btn_form_sub, btn_form_Banglow, btn_form_Land, btn_form_other;
    private LinearLayout linearLayout1;
    private GPSTracker gps;
    private ActionBar mactionbar;
    private List<String> listId = new ArrayList<String>();
    private List<String> listPrd = new ArrayList<String>();
    private List<Integer> listFlorNo = new ArrayList<Integer>();
    SQLController sqlcon;
    private double latitude, longitude, altitude;
    private ProgressDialog PD;
    private TextView text_agencyCodeForm, text_UserNameForm, editFlatNu;
    private String fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district, state, agencycode, UserName, ptrSave, ImEINo, GetAgent, GetAgentCodeMain, FlatNo, getaddress = "", prttypeflat;


    final Handler mHandler = new Handler();

    private String mResult;

    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            Log.d("Inchoo tutorial", mResult);
        }
    };
    private int a;
    private int ItemPositionPRODUCT,ItemPositionIDPF;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        sqlcon = new SQLController(this);

        findviewbyid();
        SetText();
        IMEI();
        addItemsOnSpinnerID();
       // addFlorNo();
        addItemsOnSpinnerProduct();

        GetAgentCodeMain = User_Register.UserName;
        //text_agencyCodeForm.setText("WellCome To Agency "+ GetAgentCodeMain);


        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.fromactivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);



        //All Check Flat  on Click Listner

        chk_flat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {



                if (((chk_flat)).isChecked()) {

                    ptrSave ="1";
                    btn_form_sub.setVisibility(View.VISIBLE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);

                    chk_banglow.setChecked(false);
                    chk_other.setChecked(false);
                    chk_land.setChecked(false);
                    chk_plot.setChecked(false);


                } else {

                    btn_form_sub.setVisibility(View.GONE);

                    btn_form_Banglow.setVisibility(View.VISIBLE);

                }

            }
        });


        //All Check Flat  on Click Listner
        chk_banglow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((chk_banglow)).isChecked()) {
                    ptrSave = "3";

                    btn_form_Banglow.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);

                    chk_flat.setChecked(false);
                    chk_other.setChecked(false);
                    chk_land.setChecked(false);
                    chk_plot.setChecked(false);

                } else {
                    btn_form_Banglow.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }
            }
        });


        //All check Plot on click listner

        chk_plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ptrSave = "5";
                btn_form_other.setVisibility(View.VISIBLE);
                btn_form_sub.setVisibility(View.GONE);
                btn_form_Land.setVisibility(View.GONE);
                btn_form_Banglow.setVisibility(View.GONE);
                chk_flat.setChecked(false);
                chk_banglow.setChecked(false);
                chk_land.setChecked(false);
                chk_other.setChecked(false);




            }
        });


        //All Check Other  on Click Listner
        chk_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((chk_other)).isChecked()) {
                    ptrSave = "2";
                    btn_form_other.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);
                    chk_flat.setChecked(false);
                    chk_banglow.setChecked(false);
                    chk_land.setChecked(false);
                    chk_plot.setChecked(false);


                } else {
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }

            }
        });


        //All Check Land  on Click Listner
        chk_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((chk_land)).isChecked()) {
                    ptrSave = "4";

                    btn_form_Land.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);

                    chk_flat.setChecked(false);
                    chk_banglow.setChecked(false);
                    chk_other.setChecked(false);

                } else {
                    btn_form_Land.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }

            }
        });

        btn_form_Banglow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetValue();
                ForCommon();


                Thread t = new Thread() {
                    public void run() {

                        Intent intent = new Intent(FormActivity.this, BanglowActivity.class);
                        startActivity(intent);


                        Log.d("Inchoo tutorial", "My thread is running");
                        mResult = "This is my new result";
                        mHandler.post(mUpdateResults);


                    }
                };

                t.start();


/*
                Intent i = new Intent(FormActivity.this,BanglowActivity.class);
                startActivity(i);*/
                finish();




               /* if (edit_FromNo.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "From Number is empty ", Toast.LENGTH_LONG).show();
                }else if (edit_Name.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
                }else if (edit_idNumber.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Id number is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address1.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 1 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address2.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 2 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_CityName.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "CityName is empty", Toast.LENGTH_LONG).show();
                }else if (edt_Distric.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "District is empty", Toast.LENGTH_LONG).show();
                }else if (edt_State.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "State is empty", Toast.LENGTH_LONG).show();
                }else if (edt_AgentCode.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "AgentCode is empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data comun data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);

                    Intent i = new Intent(FormActivity.this,BanglowActivity.class);
                    startActivity(i);
                    finish();


                }*/


            }
        });

        btn_form_Land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetValue();
                ForCommon();


                Thread t = new Thread() {
                    public void run() {

                        Intent intent = new Intent(FormActivity.this, LandActivity.class);
                        startActivity(intent);


                        Log.d("Inchoo tutorial", "My thread is running");
                        mResult = "This is my new result";
                        mHandler.post(mUpdateResults);
                    }
                };

                t.start();


               /* Intent i = new Intent(FormActivity.this,LandActivity.class);
                startActivity(i);*/
                finish();

               /* if (edit_FromNo.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "From Number is empty ", Toast.LENGTH_LONG).show();
                }else if (edit_Name.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
                }else if (edit_idNumber.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Id number is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address1.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 1 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address2.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 2 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_CityName.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "CityName is empty", Toast.LENGTH_LONG).show();
                }else if (edt_Distric.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "District is empty", Toast.LENGTH_LONG).show();
                }else if (edt_State.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "State is empty", Toast.LENGTH_LONG).show();
                }else if (edt_AgentCode.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "AgentCode is empty", Toast.LENGTH_LONG).show();
                }
                else
                {

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data comun data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);



                    Intent i = new Intent(FormActivity.this,LandActivity.class);
                    startActivity(i);
                    finish();


                }
*/


            }
        });

        btn_form_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetValue();
                ForCommon();


                Thread t = new Thread() {
                    public void run() {

                        Intent intent = new Intent(FormActivity.this, OtherActivity.class);
                        startActivity(intent);


                        Log.d("Inchoo tutorial", "My thread is running");
                        mResult = "This is my new result";
                        mHandler.post(mUpdateResults);
                    }
                };

                t.start();

                /*Intent i = new Intent(FormActivity.this,OtherActivity.class);
                startActivity(i);*/
                finish();

               /* if (edit_FromNo.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "From Number is empty ", Toast.LENGTH_LONG).show();
                }else if (edit_Name.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
                }else if (edit_idNumber.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Id number is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address1.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 1 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address2.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 2 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_CityName.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "CityName is empty", Toast.LENGTH_LONG).show();
                }else if (edt_Distric.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "District is empty", Toast.LENGTH_LONG).show();
                }else if (edt_State.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "State is empty", Toast.LENGTH_LONG).show();
                }else if (edt_AgentCode.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "AgentCode is empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data comun data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);


                    Intent i = new Intent(FormActivity.this,OtherActivity.class);
                    startActivity(i);
                    finish();


                }*/


            }
        });



        sp_SelectIdPrf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
            {

                ItemPositionIDPF=position+1;
                Log.e("IteamPosition",""+ItemPositionIDPF);

                Toast.makeText(getApplicationContext(),"Position="+position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        sp_SelectProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
            {

                ItemPositionPRODUCT=position+1;
                Log.e("IteamPosition product",""+ItemPositionPRODUCT);

                Toast.makeText(getApplicationContext(),"Position="+position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Button Submit form
        btn_form_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetValue();
                ForCommon();

                CllearAllfield();


                Thread t = new Thread() {
                    public void run() {

                        Intent intent = new Intent(FormActivity.this, Flat.class);
                        startActivity(intent);


                        Log.d("Inchoo tutorial", "My thread is running");
                        mResult = "This is my new result";
                        mHandler.post(mUpdateResults);
                    }
                };

                t.start();


               /* Intent intent = new Intent(FormActivity.this,Flat.class);
                startActivity(intent);*/
                finish();

              /*  if (edit_FromNo.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "From Number is empty ", Toast.LENGTH_LONG).show();
                }else if (edit_Name.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
                }else if (edit_idNumber.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Id number is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address1.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 1 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_address2.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "Address 2 is empty ", Toast.LENGTH_LONG).show();
                }else if (edt_CityName.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "CityName is empty", Toast.LENGTH_LONG).show();
                }else if (edt_Distric.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "District is empty", Toast.LENGTH_LONG).show();
                }else if (edt_State.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "State is empty", Toast.LENGTH_LONG).show();
                }else if (edt_AgentCode.getText().toString().trim().length() <= 0)
                {
                    Toast.makeText(FormActivity.this, "AgentCode is empty", Toast.LENGTH_LONG).show();
                }
                else
                {

                    GetValue();
                    ForCommon();

                    //Inserting data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);

                    CllearAllfield();

                    Intent intent = new Intent(FormActivity.this,Flat.class);
                    startActivity(intent);
                    finish();



                }
*/
            }
        });


    }


    public void GetValue()
    {
        fromno = edit_FromNo.getText().toString();
        name = edit_Name.getText().toString();
        idtype =  String.valueOf(ItemPositionIDPF);
        idno = edit_idNumber.getText().toString();
        producttype = String.valueOf(ItemPositionPRODUCT);
        prttype = ptrSave;
        address1 = edt_address1.getText().toString();
        address2 = edt_address2.getText().toString();
        city = edt_CityName.getText().toString();
        district = edt_Distric.getText().toString();
        state = edt_State.getText().toString();
        agencycode = text_agencyCodeForm.getText().toString();
        UserName = text_UserNameForm.getText().toString();
    }


    public void addItemsOnSpinnerID() {

        listId.add("Select Your Id");
        listId.add("Adhar Card");
        listId.add("Voter Id ");
        listId.add("Driving Licence");
        listId.add("Passport");
        listId.add("Pan Card");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listId);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_SelectIdPrf.setAdapter(adapter1);


    }


    // Temp. Spinner After values came from Json
    public void addItemsOnSpinnerProduct() {
        Log.e("Spiinner 2nd is workign", "Working is on");


        listPrd.add("Select Product");
        listPrd.add("Personal Loan");
        listPrd.add("Home Loan");
        listPrd.add("Mortgage");
        listPrd.add("Saving Account");
        listPrd.add("Current Account");
        listPrd.add("Demat Account");
        listPrd.add("Trading Account");
        listPrd.add("Mutual Fund");
        listPrd.add("Credit Cards");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listPrd);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_SelectProduct.setAdapter(adapter1);

    }




    // For Gps Tracker
    public void Gpsfind() {
        gps = new GPSTracker(FormActivity.this);

        PD = new ProgressDialog(FormActivity.this);
        PD.setTitle("Please Wait..");
        PD.setMessage("Loading...");
        PD.setCancelable(false);
        PD.show();
        // check if GPS enabled

        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            altitude = gps.getAltitude();
            try {
                getaddress = gps.getAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }


            PD.dismiss();


            Log.e("lat", "" + latitude);
            Log.e("long", "" + longitude);
            Log.e("Altitude", "" + altitude);

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nAlti: " + altitude + "\nAddress " + getaddress, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        // getImeiNo();
    }

    public void ForCommon() {

        if (edit_FromNo.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "From Number is empty ", Toast.LENGTH_LONG).show();
        } else if (edit_Name.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
        } else if (edit_idNumber.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "Id number is empty ", Toast.LENGTH_LONG).show();
        } else if (edt_address1.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "Address 1 is empty ", Toast.LENGTH_LONG).show();
        } else if (edt_address2.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "Address 2 is empty ", Toast.LENGTH_LONG).show();
        } else if (edt_CityName.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "CityName is empty", Toast.LENGTH_LONG).show();
        } else if (edt_Distric.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "District is empty", Toast.LENGTH_LONG).show();
        } else if (edt_State.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "State is empty", Toast.LENGTH_LONG).show();
        } else if (edt_AgentCode.getText().toString().trim().length() <= 0) {
            Toast.makeText(FormActivity.this, "AgentCode is empty", Toast.LENGTH_LONG).show();
        } else {

            sqlcon.open();
            sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);


        }


    }


    private String getImeiNo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();
        // Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ device_id,Toast.LENGTH_LONG).show();
        return device_id;
    }

   /* @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        int ItemPosition=position+1;
        Log.e("IteamPosition",""+ItemPosition);

        Toast.makeText(getApplicationContext(),"Position="+position,Toast.LENGTH_SHORT).show();


    }*/

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(FormActivity.this,
                    "Featching Geo Tag",
                    "Wait for seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }


    public String IMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getDeviceId();
        ImEINo = telephonyManager.toString();
        Log.e("Imei no is ", ImEINo);
        Toast.makeText(getApplicationContext(), "Your ImEI No IS :" + ImEINo, Toast.LENGTH_LONG).show();

        return ImEINo;
    }

    // FindViewByID
    public void findviewbyid() {

        //Button
        btn_form_sub = (Button) findViewById(R.id.btn_form_sub);
        btn_form_Banglow = (Button) findViewById(R.id.btn_Banglow);
        btn_form_Land = (Button) findViewById(R.id.btn_Land);
        btn_form_other = (Button) findViewById(R.id.btn_Other);
        //Spinner
        sp_SelectIdPrf = (Spinner) findViewById(R.id.sp_SelectIdPrf);
        sp_SelectProduct = (Spinner) findViewById(R.id.sp_SelectProduct);
        /*SpinFlorNo = (Spinner) findViewById(R.id.SpinFlorNo);*/
        //EditText
        edit_FromNo = (EditText) findViewById(R.id.edit_FromNo);
        edit_Name = (EditText) findViewById(R.id.edit_Name);
        edit_idNumber = (EditText) findViewById(R.id.edit_idNumber);
        edt_address1 = (EditText) findViewById(R.id.edt_address1);
        edt_address2 = (EditText) findViewById(R.id.edt_address2);
        edt_CityName = (EditText) findViewById(R.id.edt_CityName);
        edt_Distric = (EditText) findViewById(R.id.edt_Distric);
        edt_State = (EditText) findViewById(R.id.edt_State);
        edt_AgentCode = (EditText) findViewById(R.id.edt_AgentCode);

        //CheckBox
        chk_flat = (CheckBox) findViewById(R.id.chk_flat);
        chk_land = (CheckBox) findViewById(R.id.chk_land);
        chk_banglow = (CheckBox) findViewById(R.id.chk_banglow);
        chk_other = (CheckBox) findViewById(R.id.chk_other);
        chk_plot=(CheckBox) findViewById(R.id.chk_plot);


        //TextView
        text_agencyCodeForm = (TextView) findViewById(R.id.text_agencyCodeForm);
        text_UserNameForm = (TextView) findViewById(R.id.text_UserNameForm);
        /*editFlatNu = (TextView) findViewById(R.id.editFlatNu);*/

        //LinearLayout
        linearLayout1 = (LinearLayout) findViewById(R.id.lay_florNo);
    }

    public void CllearAllfield()
    {
        edit_FromNo.setText("");
        edit_Name.setText("");
        edit_idNumber.setText("");
        edt_address1.setText("");
        edt_address2.setText("");
        edt_CityName.setText("");
        edt_Distric.setText("");
        edt_State.setText("");
    }

    public void SetText() {

        GetAgentCodeMain = MainActivity.AgentCode;


        Log.e("Responce fromm table", sqlcon.getResponceagency());
        Log.e("Responce fromm table", sqlcon.getUserName());

        String str = "\n" + "User:" + sqlcon.getUserName();

        text_agencyCodeForm.setText("Agency :-  " + sqlcon.getResponceagency());
        text_UserNameForm.setText("User :-  " + sqlcon.getUserName());
        edt_AgentCode.setText(GetAgentCodeMain);


    }

    public void Shap() {


        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.WHITE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(9);

        text_agencyCodeForm.setBackground(shape);



       /* ShapeDrawable shape2 = new ShapeDrawable(new RectShape());
        shape2.getPaint().setColor(Color.WHITE);
        shape2.getPaint().setStyle(Paint.Style.STROKE);
        shape2.getPaint().setStrokeWidth(9);

        btnlogin.setBackground(shape2);*/


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit FormActivity ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        FormActivity.this.finish();
                        Intent intent1 = new Intent(FormActivity.this, Verificatio_activity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
