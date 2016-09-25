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
public class BanglowActivity extends AppCompatActivity
{
    private ActionBar mactionbar;
    EditText edt_Bang_frLeftLattitude,edt_Bang_frLeftLongitude,edt_Bang_frLeftAltitude,edt_Bang_frRgttLattitude,edt_Bang_frRgttLongitude,edt_Bang_frRgttAltitude,edt_Bang_BkLftLattitude,edt_Bang_BkLftLongitude,edt_Bang_BkLftAltitude,edt_Bang_BkRgtAltitude,edt_Bang_BkRgtLongitude,edt_Bang_BkRgtLattitude;
    ImageButton getGpsBang_frLft,getGpsBang_frRgt,getGpsBang_BkLfcr,getGpsBang_BkRgtcr;
    Button btn_Banglow_sbmit;
    Double latitude,longitude,altitude;
    private GPSTracker gps;
    SQLController sqlcon;
    private TextView text_agencyCodeBang,Banglow_fr_rg_cr,Banglow_Bk_lf_cr,Banglow_Bk_Ri_cr;
    private ProgressDialog PD;
    private String GetAgentCodeMain;
    private String ImEINo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banglow);

        sqlcon = new SQLController(this);

        GetAgentCodeMain=MainActivity.AgentCode;

        findviewId();
        IMEI();

        GetAgentCodeMain=MainActivity.AgentCode;
        text_agencyCodeBang.setText("WellCome to Agency "+GetAgentCodeMain);


        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.Banglowactivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);


        getGpsBang_frLft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);



                Gpsfind();

                edt_Bang_frLeftLattitude.setText(latitude.toString());
                edt_Bang_frLeftLongitude.setText(longitude.toString());
                edt_Bang_frLeftAltitude.setText(altitude.toString());

                getGpsBang_frLft.setImageResource(R.mipmap.gps1);
                //Banglow_fr_lf_cr.setTextColor(Color.BLUE);
            }
        });

        getGpsBang_frRgt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Bang_frRgttLattitude.setText(latitude.toString());
                edt_Bang_frRgttLongitude.setText(longitude.toString());
                edt_Bang_frRgttAltitude.setText(altitude.toString());


                getGpsBang_frRgt.setImageResource(R.mipmap.gps1);

               // Banglow_fr_rg_cr.setTextColor(Color.BLUE);

            }
        });

        getGpsBang_BkLfcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Bang_BkLftLattitude.setText(latitude.toString());
                edt_Bang_BkLftLongitude.setText(longitude.toString());
                edt_Bang_BkLftAltitude.setText(altitude.toString());

                getGpsBang_BkLfcr.setImageResource(R.mipmap.gps1);

               // Banglow_Bk_lf_cr.setTextColor(Color.BLUE);

            }
        });
        getGpsBang_BkRgtcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Bang_BkRgtLattitude.setText(latitude.toString());
                edt_Bang_BkRgtLongitude.setText(longitude.toString());
                edt_Bang_BkRgtAltitude.setText(altitude.toString());

                getGpsBang_BkRgtcr.setImageResource(R.mipmap.gps1);



               // Banglow_Bk_Ri_cr.setTextColor(Color.BLUE);

            }
        });

        btn_Banglow_sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertDataBanglow();

                Intent i = new Intent(BanglowActivity.this,Verificatio_activity.class);
                startActivity(i);
                finish();

                Snackbar snackbar = Snackbar
                        .make(view, "Submit SuccessFull", Snackbar.LENGTH_LONG);
                Login_Sign_Up_Activity.info(snackbar).show();
            }
        });


    }



     public void insertDataBanglow()
     {
         String frntleftlat =edt_Bang_frLeftLattitude.getText().toString() ;
         String frntleftlong = edt_Bang_frLeftLongitude.getText().toString();
         String frntleftalt= edt_Bang_frLeftAltitude.getText().toString();
         String frntrightlat =edt_Bang_frRgttLattitude.getText().toString() ;
         String frntrightlong=edt_Bang_frRgttLongitude.getText().toString() ;
         String frntrightalt= edt_Bang_frRgttAltitude.getText().toString();
         String backleftlat = edt_Bang_BkLftLattitude.getText().toString();
         String backleftlong =edt_Bang_BkLftLongitude.getText().toString() ;
         String backleftalt= edt_Bang_BkLftAltitude.getText().toString();
         String backrightlat= edt_Bang_BkRgtLattitude.getText().toString();
         String backrightlong =edt_Bang_BkRgtLongitude.getText().toString();
         String backrightalt= edt_Bang_BkRgtAltitude.getText().toString();
         String imeino =ImEINo ;
         String username="XZB";
         String agencycode=GetAgentCodeMain;

         sqlcon.open();
         sqlcon.insertDataBanglow(frntleftlat, frntleftlong, frntleftalt, frntrightlat, frntrightlong, frntrightalt, backleftlat, backleftlong, backleftalt, backrightlat, backrightlong, backrightalt, imeino, username, agencycode);




     }

    public String IMEI()
    {

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        Log.e("Imei no is ",ImEINo);
        Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ ImEINo,Toast.LENGTH_LONG).show();

        return ImEINo;
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
            progressDialog = ProgressDialog.show(BanglowActivity.this,
                    "Featching Geo Tag",
                    "Wait for seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    public void Gpsfind()
    {
        gps = new GPSTracker(BanglowActivity.this);



        if(gps.canGetLocation())
        {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            altitude = gps.getAltitude();
        }else
        {
            gps.showSettingsAlert();
        }





    }




    public void findviewId()
    {
        //Edit text

        //frontleft
        edt_Bang_frLeftLattitude=(EditText)findViewById(R.id.edt_Bang_frLeftLattitude);
        edt_Bang_frLeftLongitude=(EditText)findViewById(R.id.edt_Bang_frLeftLongitude);
        edt_Bang_frLeftAltitude=(EditText)findViewById(R.id.edt_Bang_frLeftAltitude);

        //frontRight
        edt_Bang_frRgttLattitude=(EditText)findViewById(R.id.edt_Bang_frRgttLattitude);
        edt_Bang_frRgttLongitude=(EditText)findViewById(R.id.edt_Bang_frRgttLongitude);
        edt_Bang_frRgttAltitude=(EditText)findViewById(R.id.edt_Bang_frRgttAltitude);

        //BackLeft
        edt_Bang_BkLftLattitude=(EditText)findViewById(R.id.edt_Bang_BkLftLattitude);
        edt_Bang_BkLftLongitude=(EditText)findViewById(R.id.edt_Bang_BkLftLongitude);
        edt_Bang_BkLftAltitude=(EditText)findViewById(R.id.edt_Bang_BkLftAltitude);

        //BackRight
        edt_Bang_BkRgtLattitude=(EditText)findViewById(R.id.edt_Bang_BkRgtLattitude);
        edt_Bang_BkRgtLongitude=(EditText)findViewById(R.id.edt_Bang_BkRgtLongitude);
        edt_Bang_BkRgtAltitude=(EditText)findViewById(R.id.edt_Bang_BkRgtAltitude);



        //Image Button

        getGpsBang_frLft=(ImageButton)findViewById(R.id.getGpsBang_frLft);
        getGpsBang_frRgt=(ImageButton)findViewById(R.id.getGpsBang_frRgt);
        getGpsBang_BkLfcr=(ImageButton)findViewById(R.id.getGpsBang_BkLfcr);
        getGpsBang_BkRgtcr=(ImageButton)findViewById(R.id.getGpsBang_BkRgtcr);

        //Button
        btn_Banglow_sbmit=(Button)findViewById(R.id.btn_Banglow_sbmit);

        //TextView
        text_agencyCodeBang=(TextView)findViewById(R.id. text_agencyCodeBang);
       /* Banglow_fr_rg_cr=(TextView)findViewById(R.id. Banglow_fr_rg_cr);
        Banglow_Bk_lf_cr=(TextView)findViewById(R.id. Banglow_Bk_lf_cr);
        Banglow_Bk_Ri_cr=(TextView)findViewById(R.id. Banglow_Bk_Ri_cr);*/
    }

    @Override
    public void onBackPressed()
    {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit Banglow ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        BanglowActivity.this.finish();
                        Intent intent1 = new Intent(BanglowActivity.this, FormActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                       /* Intent intent = new Intent(BanglowActivity.this, FormActivity.class);
                        startActivity(intent);

                        BanglowActivity.super.onBackPressed();

                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }


}
