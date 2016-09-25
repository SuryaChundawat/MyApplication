package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chari on 8/2/2016.
 */
public class Login_Sign_Up_Activity extends AppCompatActivity
{

    private SQLController sqlcon;
    private EditText editSignUpAgentCode,editSignUpAgentPass;
    private Button btnSignUp,DeleteDatabase;
    private ActionBar mActionBar;
    private static final int blue = 0xff0000ff;
    private String jsonStr;
    private Integer referenceValue=0;
    private String[] items1;
    ServiceHandler serviceHandler =new ServiceHandler();
    private POSTdetalis posTdetalis;



    private ProgressDialog pDialog;
    private String AgentCode,Password,ImEINo,HardCoreImei;
    private String agencyName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.Sign_Up));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        posTdetalis= new POSTdetalis();


        sqlcon = new SQLController(this);
        sqlcon=sqlcon.open();
        FindView();

        IMEI();

    }


    private boolean haveNetworkConnection()
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    public Login_Sign_Up_Activity()
    {


    }



    public void VerfiyAgencySub(View view)
    {

        if(editSignUpAgentCode.getText().toString().length()> 0 && editSignUpAgentPass.getText().toString().length()> 0)
        {
            AgentCode =editSignUpAgentCode.getText().toString();
            Password = editSignUpAgentPass.getText().toString();

            if (haveNetworkConnection()==true)
            {
                sqlcon.InsertLoginEntry(editSignUpAgentCode.getText().toString(), editSignUpAgentPass.getText().toString());
                CllearAllfield();

                new HttpAsyncTaskFromDetails().execute(HelperStatic.URL_AGENCYNAME);


            }else
            {

                AlertDialog alertbox = new AlertDialog.Builder(Login_Sign_Up_Activity.this)
                        .setMessage("Please Check Network")
                        .setPositiveButton("OK", null)
                        .show();

            }



        }else
        {
            Toast.makeText(getApplicationContext(),"Empty Please Fill",Toast.LENGTH_SHORT).show();
            return;

        }


    }


    private class HttpAsyncTaskFromDetails extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login_Sign_Up_Activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls)
        {
            //string_json_Copy="";
            String s=getformDetailsJson();
            Log.e("Object value",s);
            Log.e("URL From z",urls.toString());

            posTdetalis.POSTFormdetalis(urls[0],s);

            String result = posTdetalis.POSTFormdetalis(urls[0],s);
            Log.e("result",result);

            if (result != null)
            {

                try {
                    JSONObject jsonObj = new JSONObject(result);
                    agencyName = jsonObj.getString("AgencyName");
                    Log.e("Agency Name",agencyName);
                    sqlcon.insertResponeLogin(agencyName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            // return POSTFormdetalis(urls[0],s);

            return result;



        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(String.valueOf(result));

            pDialog.dismiss();



            if (!((result==null)&&(result.equals(""))))
            {

                Intent intent = new Intent(Login_Sign_Up_Activity.this,User_Register.class);
                startActivity(intent);
                finish();

            }
            else
            {

                Toast.makeText(getApplicationContext(),"Please Check The Network",Toast.LENGTH_SHORT).show();

            }


            // Dismiss the progress dialog

        }


    }

    public void DeleteTable(View view)
    {
        boolean IsDelete=sqlcon.DeleteTable();
        if (IsDelete==true)
        {

            Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();

        }


    }


    private String getformDetailsJson() {
        String AgCode =AgentCode ;
        String Password1 =Password;
        String IMEINo = "1234567890";


        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("AgencyCode", AgCode);
            jsonObject.put("IMEINo", IMEINo);
            jsonObject.put("Password", Password1);


            jsonArray.put(jsonObject);
            jsonObject1.put("jsonObject_Details", jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Jdon Login Str",jsonObject1.toString());

        return jsonObject1.toString();
    }



    public void CllearAllfield()
    {
        editSignUpAgentCode.setText("");
        editSignUpAgentPass.setText("");
       /* editSignUpConfmPass.setText("");*/



    }


    public void FindView()
    {
        //EditText
        editSignUpAgentCode=(EditText)findViewById(R.id.editSignUpAgentCode);
        editSignUpAgentPass=(EditText)findViewById(R.id.editSignUpAgentPass);
        /*editSignUpConfmPass=(EditText)findViewById(R.id.editSignUpConfmPass);*/

        //Btn
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        DeleteDatabase=(Button)findViewById(R.id.DeleteDatabase);

    }


    private String IMEI()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        return ImEINo;
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
        super.onBackPressed();
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit Register ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Login_Sign_Up_Activity.this.finish();
                        Intent intent1 = new Intent(Login_Sign_Up_Activity.this, MainActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                      /*  Intent intent = new Intent(Login_Sign_Up_Activity.this, MainActivity.class);
                        startActivity(intent);

                        Login_Sign_Up_Activity.super.onBackPressed();
                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private boolean getIsFirstPref()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login_Sign_Up_Activity.this);
        return preferences.getBoolean("is_first", true);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        sqlcon.close();
    }

}
