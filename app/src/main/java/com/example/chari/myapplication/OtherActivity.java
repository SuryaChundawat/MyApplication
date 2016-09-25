package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chari on 7/29/2016.
 */
public class OtherActivity extends AppCompatActivity
{
    ActionBar mactionbar;
    EditText edt_Other_frLeftLattitude,edt_Other_frLeftLongitude,edt_Other_frLeftAltitude,edt_Other_frRgttLattitude,edt_Other_frRgttLongitude,edt_Other_frRgttAltitude,edt_Other_BkLftLattitude,edt_Other_BkLftLongitude,edt_Other_BkLftAltitude,edt_Other_BkRgtAltitude,edt_Other_BkRgtLongitude,edt_Other_BkRgtLattitude;
    ImageButton getGpsOther_frLft,getGpsOther_frRgt,getGpsOther_BkLfcr,getGpsOther_BkRgtcr;
    Button btn_Other_sbmit;
    Double latitude,longitude,altitude;
    private GPSTracker gps;
    TextView text_agencyCodeOther,Other_fr_rg_cr,Other_Bk_lf_cr,Other_Bk_Ri_cr;
    SQLController sqlcon;
    private ProgressDialog PD;
    private String GetAgentCodeMain;
    private String ImEINo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        sqlcon = new SQLController(this);
        findviewIdOther();
        IMEI();

        GetAgentCodeMain=MainActivity.AgentCode;
        text_agencyCodeOther.setText("Wellcome to Agency "+GetAgentCodeMain);

        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.Otheractivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);



        getGpsOther_frLft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);


                Gpsfind();



                edt_Other_frLeftLattitude.setText(latitude.toString());
                edt_Other_frLeftLongitude.setText(longitude.toString());
                edt_Other_frLeftAltitude.setText(altitude.toString());
                getGpsOther_frLft.setImageResource(R.mipmap.gps1);
            }
        });

        getGpsOther_frRgt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Other_frRgttLattitude.setText(latitude.toString());
                edt_Other_frRgttLongitude.setText(longitude.toString());
                edt_Other_frRgttAltitude.setText(altitude.toString());
                getGpsOther_frRgt.setImageResource(R.mipmap.gps1);
            }
        });

        getGpsOther_BkLfcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);
                Gpsfind();

                edt_Other_BkLftLattitude.setText(latitude.toString());
                edt_Other_BkLftLongitude.setText(longitude.toString());
                edt_Other_BkLftAltitude.setText(altitude.toString());
                getGpsOther_BkLfcr.setImageResource(R.mipmap.gps1);

            }
        });
        getGpsOther_BkRgtcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Other_BkRgtLattitude.setText(latitude.toString());
                edt_Other_BkRgtLongitude.setText(longitude.toString());
                edt_Other_BkRgtAltitude.setText(altitude.toString());
                getGpsOther_BkRgtcr.setImageResource(R.mipmap.gps1);

            }
        });

        btn_Other_sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OtherActivity.this,Verificatio_activity.class);
                startActivity(i);
                finish();

                Snackbar snackbar = Snackbar
                        .make(view, "Submit SuccessFull", Snackbar.LENGTH_LONG);
                Login_Sign_Up_Activity.info(snackbar).show();
            }
        });


    }

    public String IMEI()
    {

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        Log.e("Imei no is ",ImEINo);
        Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ ImEINo,Toast.LENGTH_LONG).show();

        return ImEINo;
    }

    public void insertDataOther()
    {

        String frntleftlat =edt_Other_frLeftLattitude.getText().toString() ;
        String frntleftlong = edt_Other_frLeftLongitude.getText().toString();
        String frntleftalt= edt_Other_frLeftAltitude.getText().toString();
        String frntrightlat =edt_Other_frRgttLattitude.getText().toString() ;
        String frntrightlong=edt_Other_frRgttLongitude.getText().toString() ;
        String frntrightalt= edt_Other_frRgttAltitude.getText().toString();
        String backleftlat = edt_Other_BkLftLattitude.getText().toString();
        String backleftlong =edt_Other_BkLftLongitude.getText().toString() ;
        String backleftalt= edt_Other_BkLftAltitude.getText().toString();
        String backrightlat= edt_Other_BkRgtLattitude.getText().toString();
        String backrightlong =edt_Other_BkRgtLongitude.getText().toString();
        String backrightalt= edt_Other_BkRgtAltitude.getText().toString();
        String imeino =ImEINo ;
        String username ="nmb";
        String agencycode =GetAgentCodeMain;



        sqlcon.open();
        sqlcon.insertDataOther(frntleftlat, frntleftlong, frntleftalt, frntrightlat, frntrightlong, frntrightalt, backleftlat, backleftlong, backleftalt, backrightlat, backrightlong, backrightalt, imeino, username, agencycode);







    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String>
    {

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
            progressDialog = ProgressDialog.show(OtherActivity.this,
                    "Featching Geo Tag",
                    "Wait for seconds");


            //switch ()


         /*   if (getGpsOther_frLft.isClickable())
            {
                getGpsOther_frLft.setImageResource(R.mipmap.gps1);


            }else if (getGpsOther_frRgt.isClickable())
            {
                getGpsOther_frRgt.setImageResource(R.mipmap.gps1);

            }else if (getGpsOther_BkLfcr.isClickable())
            {
                getGpsOther_BkLfcr.setImageResource(R.mipmap.gps1);

            }else if (getGpsOther_BkRgtcr.isClickable())
            {

                getGpsOther_BkRgtcr.setImageResource(R.mipmap.gps1);
            }else
            {


            }*/
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }



    }




    public void Gpsfind()
    {
        gps = new GPSTracker(OtherActivity.this);
        // check if GPS enabled



        if(gps.canGetLocation())
        {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            altitude = gps.getAltitude();

            Log.e("lat",""+latitude);
            Log.e("long",""+longitude);
            Log.e("Altitude",""+altitude);



            // \n is for new line
           /* Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+ "\nAlti: " + altitude, Toast.LENGTH_LONG).show();*/
        }else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }



    public void findviewIdOther()
    {
        //Edit text

        //frontleft
        edt_Other_frLeftLattitude=(EditText)findViewById(R.id.edt_Other_frLeftLattitude);
        edt_Other_frLeftLongitude=(EditText)findViewById(R.id.edt_Other_frLeftLongitude);
        edt_Other_frLeftAltitude=(EditText)findViewById(R.id.edt_Other_frLeftAltitude);

        //frontRight
        edt_Other_frRgttLattitude=(EditText)findViewById(R.id.edt_Other_frRgttLattitude);
        edt_Other_frRgttLongitude=(EditText)findViewById(R.id.edt_Other_frRgttLongitude);
        edt_Other_frRgttAltitude=(EditText)findViewById(R.id.edt_Other_frRgttAltitude);

        //BackLeft
        edt_Other_BkLftLattitude=(EditText)findViewById(R.id.edt_Other_BkLftLattitude);
        edt_Other_BkLftLongitude=(EditText)findViewById(R.id.edt_Other_BkLftLongitude);
        edt_Other_BkLftAltitude=(EditText)findViewById(R.id.edt_Other_BkLftAltitude);

        //BackRight
        edt_Other_BkRgtLattitude=(EditText)findViewById(R.id.edt_Other_BkRgtLattitude);
        edt_Other_BkRgtLongitude=(EditText)findViewById(R.id.edt_Other_BkRgtLongitude);
        edt_Other_BkRgtAltitude=(EditText)findViewById(R.id.edt_Other_BkRgtAltitude);

        //Image Button

        getGpsOther_frLft=(ImageButton)findViewById(R.id.getGpsOther_frLft);
        getGpsOther_frRgt=(ImageButton)findViewById(R.id.getGpsOther_frRgt);
        getGpsOther_BkLfcr=(ImageButton)findViewById(R.id.getGpsOther_BkLfcr);
        getGpsOther_BkRgtcr=(ImageButton)findViewById(R.id.getGpsOther_BkRgtcr);

        //Button
        btn_Other_sbmit=(Button)findViewById(R.id.btn_Other_sbmit);

        //TextView
        text_agencyCodeOther=(TextView)findViewById(R.id.text_agencyCodeOther);
        /*Other_fr_rg_cr=(TextView)findViewById(R.id. Other_fr_rg_cr);
        Other_Bk_lf_cr=(TextView)findViewById(R.id. Other_Bk_lf_cr);
        Other_Bk_Ri_cr=(TextView)findViewById(R.id. Other_Bk_Ri_cr);*/


    }

    @Override
    public void onBackPressed()
    {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit Other ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        OtherActivity.this.finish();
                        Intent intent1 = new Intent(OtherActivity.this, FormActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                      /*  Intent intent = new Intent(OtherActivity.this, FormActivity.class);
                        startActivity(intent);

                        OtherActivity.super.onBackPressed();
                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
