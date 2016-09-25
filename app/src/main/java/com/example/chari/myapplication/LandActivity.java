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
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chari on 7/29/2016.
 */
public class LandActivity extends AppCompatActivity
{
    ActionBar mactionbar;
    EditText edt_Land_frLeftLattitude,edt_Land_frLeftLongitude,edt_Land_frLeftAltitude,edt_Land_frRgttLattitude,edt_Land_frRgttLongitude,edt_Land_frRgttAltitude,edt_Land_BkLftLattitude,edt_Land_BkLftLongitude,edt_Land_BkLftAltitude,edt_Land_BkRgtAltitude,edt_Land_BkRgtLongitude,edt_Land_BkRgtLattitude;
    ImageButton getGpsLand_frLft,getGpsLand_frRgt,getGpsLand_BkLfcr,getGpsLand_BkRgtcr;
    Button btn_Land_sbmit;
    TextView text_agencyCodeLand,text_UserNameLand,Land_Bk_lf_cr,Land_Bk_Ri_cr;
    Double latitude,longitude,altitude;
    private GPSTracker gps;
    SQLController sqlcon;
    private ProgressDialog PD;
    private String GetAgentCodeMain;
    private String ImEINo;


    final Handler mHandler = new Handler();

    private String mResult;

    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            Log.d("Inchoo tutorial", mResult);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        sqlcon = new SQLController(this);

        findview();
        IMEI();
        SetText();
        //Shap();

        GetAgentCodeMain=sqlcon.getUserName();
        //text_agencyCodeLand.setText("Wellcome To Agency "+GetAgentCodeMain);


        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.Landactivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);



        getGpsLand_frLft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();
                edt_Land_frLeftLattitude.setText(latitude.toString());
                edt_Land_frLeftLongitude.setText(longitude.toString());
                edt_Land_frLeftAltitude.setText(altitude.toString());
                getGpsLand_frLft.setImageResource(R.mipmap.gps1);
            }
        });

        getGpsLand_frRgt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Land_frRgttLattitude.setText(latitude.toString());
                edt_Land_frRgttLongitude.setText(longitude.toString());
                edt_Land_frRgttAltitude.setText(altitude.toString());
                getGpsLand_frRgt.setImageResource(R.mipmap.gps1);

            }
        });

        getGpsLand_BkLfcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Land_BkLftLattitude.setText(latitude.toString());
                edt_Land_BkLftLongitude.setText(longitude.toString());
                edt_Land_BkLftAltitude.setText(altitude.toString());
                getGpsLand_BkLfcr.setImageResource(R.mipmap.gps1);

            }
        });
        getGpsLand_BkRgtcr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "1";
                runner.execute(sleepTime);

                Gpsfind();

                edt_Land_BkRgtLattitude.setText(latitude.toString());

                edt_Land_BkRgtLongitude.setText(longitude.toString());
                edt_Land_BkRgtAltitude.setText(altitude.toString());
                getGpsLand_BkRgtcr.setImageResource(R.mipmap.gps1);

            }
        });

        btn_Land_sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                insertDataLand();

                Thread t = new Thread() {
                    public void run() {

                        Intent i = new Intent(LandActivity.this,Verificatio_activity.class);
                        startActivity(i);
                        Log.d("Inchoo tutorial", "My thread is running");
                        mResult = "This is my new result";
                        mHandler.post(mUpdateResults);
                    }
                };

                t.start();

               /* Intent i = new Intent(LandActivity.this,Verificatio_activity.class);
                startActivity(i);*/
                finish();
                Toast.makeText(getApplicationContext(),"Submit Successfully",Toast.LENGTH_SHORT).show();

               /* Snackbar snackbar = Snackbar
                        .make(view, "Submit SuccessFull", Snackbar.LENGTH_LONG);
                Login_Sign_Up_Activity.info(snackbar).show();*/
            }
        });






    }

    public String IMEI()
    {

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        Log.e("Imei no is ",ImEINo);
       // Toast.makeText(getApplicationContext(),"Your ImEI No IS :"+ ImEINo,Toast.LENGTH_LONG).show();

        return ImEINo;
    }

    public void insertDataLand()
    {

        String frntleftlat =edt_Land_frLeftLattitude.getText().toString() ;
        String frntleftlong = edt_Land_frLeftLongitude.getText().toString();
        String frntleftalt= edt_Land_frLeftAltitude.getText().toString();
        String frntrightlat =edt_Land_frRgttLattitude.getText().toString() ;
        String frntrightlong=edt_Land_frRgttLongitude.getText().toString() ;
        String frntrightalt= edt_Land_frRgttAltitude.getText().toString();
        String backleftlat = edt_Land_BkLftLattitude.getText().toString();
        String backleftlong =edt_Land_BkLftLongitude.getText().toString() ;
        String backleftalt= edt_Land_BkLftAltitude.getText().toString();
        String backrightlat= edt_Land_BkRgtLattitude.getText().toString();
        String backrightlong =edt_Land_BkRgtLongitude.getText().toString();
        String backrightalt= edt_Land_BkRgtAltitude.getText().toString();
        String florno="";
        String imeino =ImEINo ;
        String username =GetAgentCodeMain;
        String agencycode =sqlcon.getResponceagency();


        sqlcon.open();
        sqlcon.insertDataLand(frntleftlat, frntleftlong, frntleftalt, frntrightlat, frntrightlong, frntrightalt, backleftlat, backleftlong, backleftalt, backrightlat, backrightlong, backrightalt, florno, imeino, username, agencycode);







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
            progressDialog = ProgressDialog.show(LandActivity.this,
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
        gps = new GPSTracker(LandActivity.this);
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

    public void SetText()
    {

        GetAgentCodeMain = sqlcon.getUserName();


        Log.e("Responce fromm table", sqlcon.getResponceagency());

        text_agencyCodeLand.setText("Agency :-" + sqlcon.getResponceagency());
        text_UserNameLand.setText("User :-" + sqlcon.getUserName());
    }

    public void Shap() {


        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.BLUE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(9);

        text_agencyCodeLand.setBackground(shape);



    }



    public void findview()
    {
        //frontleft
        edt_Land_frLeftLattitude=(EditText)findViewById(R.id.edt_Land_frLeftLattitude);
        edt_Land_frLeftLongitude=(EditText)findViewById(R.id.edt_Land_frLeftLongitude);
        edt_Land_frLeftAltitude=(EditText)findViewById(R.id.edt_Land_frLeftAltitude);

        //frontRight
        edt_Land_frRgttLattitude=(EditText)findViewById(R.id.edt_Land_frRgttLattitude);
        edt_Land_frRgttLongitude=(EditText)findViewById(R.id.edt_Land_frRgttLongitude);
        edt_Land_frRgttAltitude=(EditText)findViewById(R.id.edt_Land_frRgttAltitude);

        //BackLeft
        edt_Land_BkLftLattitude=(EditText)findViewById(R.id.edt_Land_BkLftLattitude);
        edt_Land_BkLftLongitude=(EditText)findViewById(R.id.edt_Land_BkLftLongitude);
        edt_Land_BkLftAltitude=(EditText)findViewById(R.id.edt_Land_BkLftAltitude);

        //BackRight
        edt_Land_BkRgtAltitude=(EditText)findViewById(R.id.edt_Land_BkRgtAltitude);
        edt_Land_BkRgtLongitude=(EditText)findViewById(R.id.edt_Land_BkRgtLongitude);
        edt_Land_BkRgtLattitude=(EditText)findViewById(R.id.edt_Land_BkRgtLattitude);

        //Image Button


        getGpsLand_frLft=(ImageButton)findViewById(R.id.getGpsLand_frLft);
        getGpsLand_frRgt=(ImageButton)findViewById(R.id.getGpsLand_frRgt);
        getGpsLand_BkLfcr=(ImageButton)findViewById(R.id.getGpsLand_BkLfcr);
        getGpsLand_BkRgtcr=(ImageButton)findViewById(R.id.getGpsLand_BkRgtcr);

        //Button
        btn_Land_sbmit=(Button)findViewById(R.id.btn_Land_sbmit);

        //TextView
        text_agencyCodeLand=(TextView)findViewById(R.id.text_agencyCodeLand);
        text_UserNameLand=(TextView)findViewById(R.id.text_UserNameLand);
        /*Land_Bk_lf_cr=(TextView)findViewById(R.id.Land_Bk_lf_cr);
        Land_Bk_Ri_cr=(TextView)findViewById(R.id.Land_Bk_Ri_cr);*/



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit Land ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                        LandActivity.this.finish();
                        Intent intent1 = new Intent(LandActivity.this, FormActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                        /*Intent intent = new Intent(LandActivity.this, FormActivity.class);
                        startActivity(intent);

                        LandActivity.super.onBackPressed();

                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
