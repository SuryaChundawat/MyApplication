package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
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


public class FormActivity extends AppCompatActivity
{

    private Spinner sp_SelectIdPrf,sp_SelectProduct;
    public static EditText edit_idNumber,edt_address1,edt_address2,editFlatNu,edt_AgentCode,edt_State,edt_Distric,edt_CityName,edit_FromNo,edit_Name,AgentCode;
    private CheckBox chk_flat,chk_land,chk_banglow,chk_other;
    private Editable GetAgencyCode;
    private Button btn_form_sub,btn_form_Banglow,btn_form_Land,btn_form_other;
    private LinearLayout linearLayout1;
    private GPSTracker gps;
    private ActionBar mactionbar;
    private List<String> listId = new ArrayList<String>();
    private List<String> listPrd = new ArrayList<String>();
    SQLController sqlcon;
    private double latitude,longitude,altitude;
    private String FlatNo,getaddress="",prttypeflat;
    private ProgressDialog PD;
    private TextView text_agencyCodeForm;
    private String ptrSave;
    private String ImEINo,GetAgent;
    private String GetAgentCodeMain;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        sqlcon = new SQLController(this);

        findviewbyid();

        GetAgentCodeMain=MainActivity.AgentCode;
        text_agencyCodeForm.setText("WellCome To Agency "+ GetAgentCodeMain);
        edt_AgentCode.setText(GetAgentCodeMain);

        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.fromactivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);




       /* GetAgencyCode= MainActivity.editAgencycode.getText();
        final String xyz=GetAgencyCode.toString();*/
        /*GetAgent=Verificatio_activity.GetAgentCodeMain;*/


        /*Log.e("GetAgency Code",GetAgent);

        TxtagencyCode.setText("Wellcome To Agency "+GetAgent);

        edt_AgentCode.setText(xyz);*/



        IMEI();
        addItemsOnSpinnerID();
        addItemsOnSpinnerProduct();

        //onClick(btn_form_sub);




        //All Check Flat  on Click Listner

        chk_flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( ((chk_flat)).isChecked())
                {

                    ptrSave ="Flat No ="+ editFlatNu.getText().toString();
                    editFlatNu.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.VISIBLE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);

                    chk_banglow.setChecked(false);
                    chk_other.setChecked(false);
                    chk_land.setChecked(false);



                }else
                {

                    editFlatNu.setVisibility(View.GONE);

                }

            }
        });






        //All Check Flat  on Click Listner
        chk_banglow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ( ((chk_banglow)).isChecked())
                {
                    ptrSave="Banglow";

                    btn_form_Banglow.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);
                    editFlatNu.setVisibility(View.GONE);

                    chk_flat.setChecked(false);
                    chk_other.setChecked(false);
                    chk_land.setChecked(false);

                }else
                {
                    btn_form_Banglow.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }
            }
        });


        //All Check Other  on Click Listner
        chk_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if ( ((chk_other)).isChecked())
                {
                    ptrSave ="Other";
                    btn_form_other.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_Land.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);
                    editFlatNu.setVisibility(View.GONE);
                    chk_flat.setChecked(false);
                    chk_banglow.setChecked(false);
                    chk_land.setChecked(false);



                }else
                {
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }

            }
        });



        //All Check Land  on Click Listner
        chk_land.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if ( ((chk_land)).isChecked())
                {
                    ptrSave="Land";

                    btn_form_Land.setVisibility(View.VISIBLE);
                    btn_form_sub.setVisibility(View.GONE);
                    btn_form_other.setVisibility(View.GONE);
                    btn_form_Banglow.setVisibility(View.GONE);
                    editFlatNu.setVisibility(View.GONE);

                    chk_flat.setChecked(false);
                    chk_banglow.setChecked(false);
                    chk_other.setChecked(false);

                }else
                {
                    btn_form_Land.setVisibility(View.GONE);
                    btn_form_sub.setVisibility(View.VISIBLE);

                }

            }
        });

        btn_form_Banglow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String fromno =edit_FromNo.getText().toString();
                String name =edit_Name.getText().toString();
                String idtype =sp_SelectIdPrf.getSelectedItem().toString();
                String idno =edit_idNumber.getText().toString();
                String producttype =sp_SelectProduct.getSelectedItem().toString();
                String prttype =ptrSave;
                String address1 =edt_address1.getText().toString();
                String address2 =edt_address2.getText().toString();
                String city =edt_CityName.getText().toString();
                String district =edt_Distric.getText().toString();
                String state =edt_State.getText().toString();
                /*String agentcode =xyz;*/
                String agentcode ="fg";
                String UserName ="Samir";

                Double Latitude =latitude;
                Double Altitude =altitude;
                Double Longitude=longitude;



                if (edit_FromNo.getText().toString().trim().length() <= 0)
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
                    Gpsfind();

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data comun data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);



                    Intent i = new Intent(FormActivity.this,BanglowActivity.class);
                    startActivity(i);
                    finish();


                }



            }
        });

        btn_form_Land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromno =edit_FromNo.getText().toString();
                String name =edit_Name.getText().toString();
                String idtype =sp_SelectIdPrf.getSelectedItem().toString();
                String idno =edit_idNumber.getText().toString();
                String producttype =sp_SelectProduct.getSelectedItem().toString();
                String prttype =ptrSave;
                String address1 =edt_address1.getText().toString();
                String address2 =edt_address2.getText().toString();
                String city =edt_CityName.getText().toString();
                String district =edt_Distric.getText().toString();
                String state =edt_State.getText().toString();
                /*String agentcode =xyz;*/
                String agentcode ="fg";
                String UserName ="Samir";

                Double Latitude =latitude;
                Double Altitude =altitude;
                Double Longitude=longitude;



                if (edit_FromNo.getText().toString().trim().length() <= 0)
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
                    Gpsfind();

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



            }
        });

        btn_form_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromno =edit_FromNo.getText().toString();
                String name =edit_Name.getText().toString();
                String idtype =sp_SelectIdPrf.getSelectedItem().toString();
                String idno =edit_idNumber.getText().toString();
                String producttype =sp_SelectProduct.getSelectedItem().toString();
                String prttype =ptrSave;
                String address1 =edt_address1.getText().toString();
                String address2 =edt_address2.getText().toString();
                String city =edt_CityName.getText().toString();
                String district =edt_Distric.getText().toString();
                String state =edt_State.getText().toString();
                /*String agentcode =xyz;*/
                String agentcode ="fg";
                String UserName ="Samir";

                Double Latitude =latitude;
                Double Altitude =altitude;
                Double Longitude=longitude;



                if (edit_FromNo.getText().toString().trim().length() <= 0)
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
                    Gpsfind();

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data comun data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);


                    Intent i = new Intent(FormActivity.this,OtherActivity.class);
                    startActivity(i);
                    finish();


                }



            }
        });




        //Button Submit
        btn_form_sub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                String fromno =edit_FromNo.getText().toString();
                String name =edit_Name.getText().toString();
                String idtype =sp_SelectIdPrf.getSelectedItem().toString();
                String idno =edit_idNumber.getText().toString();
                String producttype =sp_SelectProduct.getSelectedItem().toString();
                String prttype =ptrSave;
                /*String prttypeland = "Land";
                String prttypebanglow ="Banglow";
                String prttypeother ="other";*/
                String address1 =edt_address1.getText().toString();
                String address2 =edt_address2.getText().toString();
                String city =edt_CityName.getText().toString();
                String district =edt_Distric.getText().toString();
                String state =edt_State.getText().toString();
                /*String agentcode =xyz;*/
                String agentcode ="fg";
                String UserName ="Samir";

                Double Latitude =latitude;
                Double Altitude =altitude;
                Double Longitude=longitude;



                if (edit_FromNo.getText().toString().trim().length() <= 0)
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
                    Gpsfind();

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    String sleepTime = "1";
                    runner.execute(sleepTime);


                    //Inserting data
                    sqlcon.open();
                    sqlcon.insertData(fromno, name, idtype, idno, producttype, prttype, address1, address2, city, district);

                    //Inserting data for Flat Activity


                    sqlcon.open();
                    sqlcon.InsertForFlat(Latitude , Longitude, Altitude, state, UserName, agentcode);


                    Snackbar snackbar = Snackbar
                            .make(view, "Submit SuccessFull", Snackbar.LENGTH_LONG);
                    Login_Sign_Up_Activity.info(snackbar).show();

                    CllearAllfield();

                }

            }
        });
       }




        public void addItemsOnSpinnerID()
       {

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
       public void addItemsOnSpinnerProduct()
      {
         Log.e("Spiinner 2nd is workign","Working is on");


         listPrd.add("Select Product");
         listPrd.add("Product 1");
         listPrd.add("Product 2");
         listPrd.add("Product 3");
         listPrd.add("Product 4");
         listPrd.add("Product 5");
         ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listPrd);
         adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         sp_SelectProduct.setAdapter(adapter1);

      }





      // For Gps Tracker
      public void Gpsfind()
      {
         gps = new GPSTracker(FormActivity.this);

          PD = new ProgressDialog(FormActivity.this);
          PD.setTitle("Please Wait..");
          PD.setMessage("Loading...");
          PD.setCancelable(false);
          PD.show();
         // check if GPS enabled

          if(gps.canGetLocation())
          {
             latitude = gps.getLatitude();
             longitude = gps.getLongitude();
             altitude = gps.getAltitude();
              try {
                  getaddress=gps.getAddress();
              } catch (IOException e)
              {
                  e.printStackTrace();
              }


              PD.dismiss();


              Log.e("lat",""+latitude);
            Log.e("long",""+longitude);
            Log.e("Altitude",""+altitude);

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+ "\nAlti: " + altitude+ "\nAddress "+ getaddress, Toast.LENGTH_LONG).show();
          }else
          {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
          }
          getImeiNo();
       }

    private String getImeiNo()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();
        Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ device_id,Toast.LENGTH_LONG).show();
        return device_id;
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

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




     public String IMEI()
      {
          TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
          telephonyManager.getDeviceId();
          ImEINo =telephonyManager.toString();
          Log.e("Imei no is ",ImEINo);
          Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ ImEINo,Toast.LENGTH_LONG).show();

          return ImEINo;
      }

  /*  public String getDeviceUniqueID(Activity activity)
    {
        String device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Log.e("device unique Id",device_unique_id);
        return device_unique_id;
    }
*/


    // FindViewByID
       public void findviewbyid()
      {

        //Button
          btn_form_sub=(Button)findViewById(R.id.btn_form_sub);
          btn_form_Banglow=(Button)findViewById(R.id.btn_Banglow);
          btn_form_Land=(Button)findViewById(R.id.btn_Land);
          btn_form_other=(Button)findViewById(R.id.btn_Other);




        //Spinner
        sp_SelectIdPrf=(Spinner)findViewById(R.id.sp_SelectIdPrf);
        sp_SelectProduct=(Spinner)findViewById(R.id.sp_SelectProduct);

        //EditText
        edit_FromNo=(EditText)findViewById(R.id.edit_FromNo);
        edit_Name=(EditText)findViewById(R.id.edit_Name);
        edit_idNumber=(EditText)findViewById(R.id.edit_idNumber);
        edt_address1=(EditText)findViewById(R.id.edt_address1);
        edt_address2=(EditText)findViewById(R.id.edt_address2);
        edt_CityName=(EditText)findViewById(R.id.edt_CityName);
        edt_Distric=(EditText)findViewById(R.id.edt_Distric);
        edt_State=(EditText)findViewById(R.id.edt_State);
        edt_AgentCode=(EditText)findViewById(R.id.edt_AgentCode);
        editFlatNu=(EditText)findViewById(R.id.editFlatNu);
        //CheckBox
        chk_flat=(CheckBox)findViewById(R.id.chk_flat);
        chk_land=(CheckBox)findViewById(R.id.chk_land);
        chk_banglow=(CheckBox)findViewById(R.id.chk_banglow);
        chk_other=(CheckBox)findViewById(R.id.chk_other);


          //TextView
          text_agencyCodeForm=(TextView)findViewById(R.id.text_agencyCodeForm);


         //LinearLayout
         linearLayout1=(LinearLayout)findViewById(R.id.linearLayout1);




      }



    public void Form_Details_Insert()
    {
        String fromno =edit_FromNo.getText().toString();
        String name =edit_Name.getText().toString();
        String idtype =sp_SelectIdPrf.getSelectedItem().toString();
        String idno =edit_idNumber.getText().toString();
        String producttype =sp_SelectProduct.getSelectedItem().toString();
        String prttypeflat =editFlatNu.getText().toString();
        String prttypeland = "Land";
        String prttypebanglow ="Banglow";
        String prttypeother ="other";
        String address1 =edt_address1.getText().toString();
        String address2 =edt_address2.getText().toString();
        String city =edt_CityName.getText().toString();
        String district =edt_Distric.getText().toString();
        String state =edt_State.getText().toString();
        String agentcode =edt_AgentCode.getText().toString();


        Log.e("Form no",fromno);
        Log.e("name",name);
        Log.e("id type",idtype);
        Log.e("idno",idno);
        Log.e("product",producttype);
        Log.e("prductflat",prttypeflat);
        Log.e("prductland",prttypeland);
        Log.e("prductbanglow",prttypebanglow);
        Log.e("prductother",prttypeother);
        Log.e("Address1",address1);
        Log.e("Address2",address2);
        Log.e("City",city);
        Log.e("District",district);
        Log.e("State",state);
        Log.e("AgentCode",agentcode);

        if (edit_FromNo.getText().toString().trim().length() <= 0)
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
            //Inserting data
           /* sqlcon.open();
            sqlcon.insertData(fromno, name, idtype, idno, producttype, prttypeflat, prttypeland, prttypebanglow, prttypeother, address1, address2, city, district, state, agentcode);
*/
        }



        //Inserting data
       // sqlcon.open();
       // sqlcon.insertData(fromno, name, idtype, idno, producttype, prttypeflat, prttypeland, prttypebanglow, prttypeother, address1, address2, city, district, state, agentcode);

    }

    public void CllearAllfield()
    {
        edit_FromNo.setText("");
        edit_Name.setText("");
        edit_idNumber.setText("");
        editFlatNu.setText("");
        edt_address1.setText("");
        edt_address2.setText("");
        edt_CityName.setText("");
        edt_Distric.setText("");
        edt_State.setText("");



    }

    @Override
    public void onBackPressed()
    {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit FormActivity ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        FormActivity.this.finish();
                        Intent intent1 = new Intent(FormActivity.this, Verificatio_activity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);


                       /* Intent intent = new Intent(FormActivity.this, Verificatio_activity.class);
                        startActivity(intent);

                        FormActivity.super.onBackPressed();
                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
