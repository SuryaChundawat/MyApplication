package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import AppController.AppController;

/**
 * Created by Chari on 8/2/2016.
 */
public class Login_Sign_Up_Activity extends AppCompatActivity
{

    SQLController sqlcon;
    EditText editSignUpAgentCode,editSignUpAgentPass,editSignUpConfmPass;
    Button btnSignUp;
    private static final int blue = 0xff0000ff;
    String jsonStr;
    String[] items1;
    ServiceHandler serviceHandler =new ServiceHandler();

    /*http://friskcon.com/AgencyRestService/AgencyRestService.svc/GetAgencyName/AgencyCode=Agency/Password=a/IMIENo=1234567890*/

    public static String url = "http://friskcon.com/AgencyRestService/AgencyRestService.svc/GetAgencyName/";
    /*public static String url = "http://friskcon.com/AgencyRestService/AgencyRestService.svc/GetAgencyName/";*/
    private ProgressDialog pDialog;
    private String AgentCode,Password,ImEINo,HardCoreImei;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sqlcon = new SQLController(this);
        sqlcon=sqlcon.open();
        FindView();
        AgentCode =editSignUpAgentCode.getText().toString();
        Password = editSignUpAgentPass.getText().toString();
        IMEI();

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(getIsFirstPref())
                {

                    if (editSignUpAgentCode.getText().toString().length()> 0 &&editSignUpAgentPass.getText().toString().length()> 0 ) {

                        if (AppController.getInstance().haveNetworkConnection())
                        {

                            if (jsonStr.equals(" "))
                            {
                                sqlcon.InsertLoginEntry(AgentCode, Password);
                                CllearAllfield();
                                new GetContacts().execute();
                            }else
                            {
                                AlertDialog alertbox = new AlertDialog.Builder(Login_Sign_Up_Activity.this)
                                        .setMessage("Please Turn ON Internet ")
                                        .setPositiveButton("OK", null)
                                        .show();

                            }




                        }else
                        {
                            Toast.makeText(getApplicationContext(), "Please turn on internet connection.", Toast.LENGTH_LONG).show();

                        }

                    }else
                    {
                        if (!(editSignUpAgentCode.getText().toString().length()> 0))
                        {
                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Agent Code", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();

                        }else if(!(editSignUpAgentPass.getText().toString().length()>0))
                        {

                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Password", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();


                        }else

                        {

                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Valid Values", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();



                        }






                    }





                }else
                {
                    if (editSignUpAgentCode.getText().toString().length()> 0 &&editSignUpAgentPass.getText().toString().length()> 0) {
                        if (sqlcon.checkUserLogin(AgentCode,Password)) {

                            Intent intent = new Intent(Login_Sign_Up_Activity.this, User_Register.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Valid Values", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();


                        }
                    }else
                    {
                        if (!(editSignUpAgentCode.getText().toString().length()> 0))
                        {
                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Agent Code", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();

                        }else if(!(editSignUpAgentPass.getText().toString().length()>0))
                        {

                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Password", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();


                        }else

                        {

                            Snackbar snackbar = Snackbar
                                    .make(view, "Enter Valid Values", Snackbar.LENGTH_LONG);
                            Login_Sign_Up_Activity.info(snackbar).show();



                        }



                    }



                }





                /* AgentCode =editSignUpAgentCode.getText().toString();
                 Password = editSignUpAgentPass.getText().toString();*/
               /* String ConfPassword =editSignUpConfmPass.getText().toString();*/

               /* if(AgentCode.equals("")||Password.equals(""))
                {

                    Snackbar snackbar = Snackbar
                            .make(view, "Empty Please Fill", Snackbar.LENGTH_LONG);
                    Login_Sign_Up_Activity.info(snackbar).show();
                    //Toast.makeText(getApplicationContext(),"Empty Please Fill",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    sqlcon.InsertLoginEntry(AgentCode, Password);
                    CllearAllfield();

                    new GetContacts().execute();
                    // Save the Data in Database
                   ;


                    //Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

                    *//*Intent intent = new Intent(Login_Sign_Up_Activity.this,User_Register.class);
                    startActivity(intent);
                    finish();*//*


                }*/




            }
        });



    }



    public class GetContacts extends AsyncTask<Void ,Void,Void>
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
        protected Void doInBackground(Void... voids)
        {



            String result = "";


            try
            {

                String AgCode="Agency";
                String AgCode1="a";
                HardCoreImei="1234567890";

               /* result = url
                        +login_name+"/"
                        +login_pass;*/
                HardCoreImei="1234567890";


                result = url
                        + "AgencyCode="+AgCode
                        + "/Password=" + AgCode1 + "/IMIENo=" + HardCoreImei;





                Log.e("Result is", result);


            } catch (Exception e1)
            {
                e1.printStackTrace();
            }



            jsonStr = serviceHandler.makeServiceCall( result  ,ServiceHandler.GET);
            Log.e("Json is hitte",jsonStr);



         /*   Toast.makeText(getApplicationContext(),jsonStr,Toast.LENGTH_LONG).show();*/


            Log.d("Response: ", "> " + jsonStr);




            if (jsonStr != null)
            {



                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONObject agencyobj = jsonObj.getJSONObject("JSONDataResult");

                   /* JSONObject jsonObj1 = new JSONObject(agencyobj.toString());
                    JSONObject agencyobj2 = jsonObj1.getJSONObject("AgencyName");*/

                    String SplitAgenName =agencyobj .toString();




                    Log.e("Json Responce is1 ",agencyobj .toString());

                    items1 = SplitAgenName.split(":");
                    String sSpinnerVal = items1[0];
                    String sSpinnerVal1 = items1[1];

                    Log.e("Json Responce Splited1 ",sSpinnerVal);
                    Log.e("Json Responce Splited2",sSpinnerVal1);

                    sqlcon.insertResponeLogin(sSpinnerVal1 );




                   /* Log.e("Json Responce is2 ",agencyobj2 .toString());*/

                    // looping through All Contacts
                   /* for (int i = 0; i < contacts.length(); i++)
                    {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String email = c.getString(TAG_EMAIL);
                        String address = c.getString(TAG_ADDRESS);
                        String gender = c.getString(TAG_GENDER);

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject(TAG_PHONE);
                        String mobile = phone.getString(TAG_PHONE_MOBLIE);
                        String home = phone.getString(TAG_PHONE_HOME);
                        String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_EMAIL, email);
                        contact.put(TAG_PHONE_MOBLIE, mobile);

                        // adding contact to contact list
                        conList.add(contact);

                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }


               // }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            //pDialog.dismiss();

            return null;



        }


        protected void onProgressUpdate(Void args)
        {

            Toast.makeText(getApplicationContext(),jsonStr, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);



            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            Intent intent = new Intent(Login_Sign_Up_Activity.this,User_Register.class);
            startActivity(intent);
            finish();



            /**
             * Updating parsed JSON data into ListView
             * */
            /*ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, conList,
                    R.layout.list_v, new String[] { TAG_NAME, TAG_EMAIL,
                    TAG_PHONE_MOBLIE }, new int[] { R.id.name,
                    R.id.email, R.id.mobile });

            setListAdapter(adapter);*/
        }


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

    }

    private static View getSnackBarLayout(Snackbar snackbar)
    {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static Snackbar colorSnackBar(Snackbar snackbar, int colorId)
    {
        View snackBarView = getSnackBarLayout(snackbar);
        if (snackBarView != null)
        {
            snackBarView.setBackgroundColor(colorId);

        }

        return snackbar;
    }

    private String IMEI()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        return ImEINo;
    }

    public static Snackbar info(Snackbar snackbar)
    {
        return colorSnackBar(snackbar, blue);
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
