package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chari on 8/24/2016.
 */
public class User_Register extends AppCompatActivity
{
    EditText edit_UserName,edit_UserPassword,edit_UserConfPassword,edit_UserEmail,edit_UserAge,edit_UserPhoneNo;
    TextView Txt_UserAgencyName;
    Button btn_ver_reg;
    SQLController sqlcon;
    public static String UserName;
    String User_Name,User_Email,User_Password,User_ConfimPass,User_Age,User_MoblieNu;
    private String ImEINo;
    private POSTdetalis posTdetalis;
    private ActionBar mActionBar;
    private Integer referenceValue=0;
    private ProgressDialog pDialog;
    private ServiceHandler serviceHandler =new ServiceHandler();

    /*public static String UserRegister="http://friskcon.com/RestServiceGetAgency/AgencyRestService.svc/SetAgencyOfficerDetails/";*/
    private String User_AgencyCode,jsonStr;
    private String[] items1;
    private String Uder_ConPassword;
    private String agencyName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.UserRegister));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        posTdetalis= new POSTdetalis();

        sqlcon = new SQLController(this);
        findView();
        SetText();
        //Shap();
        IMEI();
        //Shap();


    }

    public void GetText()
    {
        User_AgencyCode=sqlcon.getResponceagency();
        User_Name=edit_UserName.getText().toString();
        User_Email=edit_UserEmail.getText().toString();
        User_Password=edit_UserPassword.getText().toString();
        Uder_ConPassword =edit_UserConfPassword.getText().toString();
        User_ConfimPass=edit_UserConfPassword.getText().toString();
        User_Age=edit_UserAge.getText().toString();
        User_MoblieNu=edit_UserPhoneNo.getText().toString();
    }

    public User_Register()
    {


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



    public void RegisterUser(View view)
    {
        GetText();

        if(getIsFirstPref())
        {

            if (edit_UserName.getText().toString().length()>0 &&edit_UserEmail.getText().toString().length()>0&&edit_UserPassword.getText().toString().length()>0
                    &&edit_UserConfPassword.getText().toString().length()>0&&edit_UserAge.getText().toString().length()>0&&edit_UserPhoneNo.getText().toString().length()>0
                    )
            {
                sqlcon.open();
                sqlcon.InsertUserRegister(User_Name, User_Password, User_Email, User_Age, User_MoblieNu, ImEINo);
                //UserName=User_Name;
                UserName=sqlcon.getUserName();
                Log.e("UserName",UserName);

               /* if (AppController.getInstance().haveNetworkConnection())
                {*/

                if (User_Password.equals(Uder_ConPassword))
                {
                    if (haveNetworkConnection()==true)
                    {
                        new HttpAsyncTaskUserDeatils().execute(HelperStatic.URL_USERDEATILS);

                    } else
                    {
                        AlertDialog alertbox = new AlertDialog.Builder(User_Register.this)
                                .setMessage("Please Turn ON Internet ")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }else
                {

                    Toast.makeText(getApplicationContext(),"PassWord Not Match",Toast.LENGTH_SHORT).show();
                }
            }else
            {
                if (!(edit_UserName.getText().toString().length()>0))
                {

                    Toast.makeText(getApplicationContext(),"Enter User Name",Toast.LENGTH_SHORT).show();



                }
                else if (!(edit_UserEmail.getText().toString().length()>0))
                {
                    Toast.makeText(getApplicationContext(),"Enter EmailAddress",Toast.LENGTH_SHORT).show();



                }else if (!(edit_UserPassword.getText().toString().length()>0)) {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();

            }
                else if (!(edit_UserAge.getText().toString().length()>0))
                {
                    Toast.makeText(getApplicationContext(),"Enter Age",Toast.LENGTH_SHORT).show();

                }
                else if (!(edit_UserPhoneNo.getText().toString().length()>0))
                {
                    Toast.makeText(getApplicationContext(),"Enter Moblie Number",Toast.LENGTH_SHORT).show();

                }else if(!(edit_UserConfPassword.getText().toString().length()>0))
            {

                Toast.makeText(getApplicationContext()," Enter Confirm Password",Toast.LENGTH_SHORT).show();

            }
            else
                {
                    Toast.makeText(getApplicationContext(),"Plese Enter valid Entreies",Toast.LENGTH_SHORT).show();

                }

            }

        }else
        {
            if (edit_UserName.getText().toString().length()>0 &&edit_UserEmail.getText().toString().length()>0&&edit_UserPassword.getText().toString().length()>0
                    &&edit_UserConfPassword.getText().toString().length()>0&&edit_UserAge.getText().toString().length()>0&&edit_UserPhoneNo.getText().toString().length()>0
                    )
            {
                Intent intent = new Intent(User_Register.this,MainActivity.class);
                startActivity(intent);
                finish();

            }else
            {
                Toast.makeText(getApplicationContext(),"Plese Enter valid Entreies",Toast.LENGTH_SHORT).show();

            }
        }
/*
        if(User_Name.equals("")||User_Email.equals("")||User_Password.equals("")||User_ConfimPass.equals("")||User_Age.equals("")||User_MoblieNu.equals(""))
        {

            Snackbar snackbar = Snackbar
                    .make(view, "Empty Please Fill", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            return;
        }else if (!(User_Password.equals(User_ConfimPass)))
        {

            Snackbar snackbar = Snackbar
                    .make(view, "Password Not Match", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            return;
        }else
        {

            sqlcon.InsertUserRegister(User_Name, User_Password, User_Email, User_Age, User_MoblieNu, ImEINo);
            Snackbar snackbar = Snackbar
                    .make(view, "User Register SuccessFully", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            CllearAllfield();
            return;
        }*/
    }


/*
    public void Shap() {


        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.WHITE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(9);

        Txt_UserAgencyName.setBackground(shape);



       *//* ShapeDrawable shape2 = new ShapeDrawable(new RectShape());
        shape2.getPaint().setColor(Color.WHITE);
        shape2.getPaint().setStyle(Paint.Style.STROKE);
        shape2.getPaint().setStrokeWidth(9);

        btnlogin.setBackground(shape2);*//*


    }*/

    public void SetText()
    {
        Log.e("Responce fromm table",sqlcon.getResponceagency());
        Txt_UserAgencyName.setText(sqlcon.getResponceagency());
    }


    private String getformDetailsUserJson()
    {


        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray= new JSONArray();
        JSONObject jsonObject1= new JSONObject();

        try {



            jsonObject.put("AgencyCode", sqlcon.getResponceagency());
            jsonObject.put("OfficerName", User_Name);
            jsonObject.put("Password", User_Password);
            jsonObject.put("Age", User_Age);
            jsonObject.put("Email", User_Email);
            jsonObject.put("Mobile", User_MoblieNu);
            jsonObject.put("IMEINo", ImEINo);




            jsonArray.put(jsonObject);
            jsonObject1.put("jsonObject_Details", jsonArray);


        } catch (JSONException e)
        {
            e.printStackTrace();
        }




        Log.e("Jdon Login Str",jsonObject1.toString());
        return jsonObject1.toString();


    }


    private class HttpAsyncTaskUserDeatils extends AsyncTask<String, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(User_Register.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(String... urls)
        {
            //string_json_Copy="";
            String sUser=getformDetailsUserJson();
            Log.e("Object value",sUser);
            Log.e("URL From z",urls.toString());

            posTdetalis.POSTFormdetalis(urls[0],sUser);

           String result =posTdetalis.POSTFormdetalis(urls[0],sUser);
            Log.e("responce",result);

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

        
            

            return null;



        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            pDialog.dismiss();



            Intent intent = new Intent(User_Register.this,MainActivity.class);
            startActivity(intent);
            finish();



            // Dismiss the progress dialog

        }

    }
















    public class GetContacts extends AsyncTask<Void ,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(User_Register.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            referenceValue=1;
            String result = "";

            try
            {

                String HardCoreMoblie="1253565464";
                String HardCoreIMEI="757465464";

                result = HelperStatic.URL_USERDEATILS
                        + "AgencyCode="+User_AgencyCode
                        + "/OfficerName=" + User_Name + "/Password=" + User_Password
                        + "/Age=" + User_Age + "/Mobile=" + HardCoreMoblie + "/IMEINo=" + HardCoreIMEI ;
                Log.e("Result is", result);

            } catch (Exception e1)
            {
                e1.printStackTrace();
            }

            jsonStr = serviceHandler.makeServiceCall( result  ,ServiceHandler.GET);
            Log.e("Json is hitte",jsonStr);

         /*   Toast.makeText(getApplicationContext(),jsonStr,Toast.LENGTH_LONG).show();*/
           /* Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null)
            {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);// Getting JSON Array node
                    JSONObject agencyobj = jsonObj.getJSONObject("JSONDataResult");

                   *//* JSONObject jsonObj1 = new JSONObject(agencyobj.toString());
                    JSONObject agencyobj2 = jsonObj1.getJSONObject("AgencyName");*//*

                    String SplitAgenName =agencyobj .toString();




                    Log.e("Json Responce is1 ",agencyobj .toString());

                    items1 = SplitAgenName.split(":");
                    String sSpinnerVal = items1[0];
                    String sSpinnerVal1 = items1[1];

                    Log.e("Json Responce Splited1 ",sSpinnerVal);
                    Log.e("Json Responce Splited2",sSpinnerVal1);

                    sqlcon.insertResponeLogin(sSpinnerVal1 );

                   *//* Log.e("Json Responce is2 ",agencyobj2 .toString());*//*

                    // looping through All Contacts
                   *//* for (int i = 0; i < contacts.length(); i++)
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

                    }*//*
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }*/

            //pDialog.dismiss();

            return null;



        }


        protected void onProgressUpdate(Void args)
        {

            Toast.makeText(getApplicationContext(),jsonStr, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);



            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            Intent intent = new Intent(User_Register.this,MainActivity.class);
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

    private String IMEI()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        return ImEINo;
    }

    private boolean getIsFirstPref()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(User_Register.this);
        return preferences.getBoolean("is_first", true);
    }


    public void CllearAllfield()
    {
        edit_UserName.setText("");
        edit_UserPassword.setText("");
        edit_UserConfPassword.setText("");
        edit_UserEmail.setText("");
        edit_UserAge.setText("");
        edit_UserPhoneNo.setText("");
    }

    public void Shap()
    {

        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.GRAY);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(3);

        edit_UserName.setBackground(shape);
        edit_UserPassword.setBackground(shape);
        edit_UserConfPassword.setBackground(shape);
        edit_UserEmail.setBackground(shape);
        edit_UserAge.setBackground(shape);
        edit_UserPhoneNo.setBackground(shape);
        Txt_UserAgencyName.setBackground(shape);

       /* edit_UserName.setBackgroundResource(R.drawable.edittext_bg);*

        */
    }

    public  void  findView()
    {
        //EditText
        edit_UserName =(EditText)findViewById(R.id.edit_UserName);
        edit_UserPassword =(EditText)findViewById(R.id.edit_UserPassword);
        edit_UserConfPassword =(EditText)findViewById(R.id.edit_UserConfPassword);
        edit_UserEmail =(EditText)findViewById(R.id.edit_UserEmail);
        edit_UserAge =(EditText)findViewById(R.id.edit_UserAge);
        edit_UserPhoneNo =(EditText)findViewById(R.id.edit_UserPhoneNo);

        //Textview
        Txt_UserAgencyName=(TextView)findViewById(R.id.Txt_UserAgencyName);

        //Button
        btn_ver_reg=(Button)findViewById(R.id.btn_ver_reg);

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
                .setMessage("Exit User Register ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        User_Register.this.finish();
                        Intent intent1 = new Intent(User_Register.this, MainActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
