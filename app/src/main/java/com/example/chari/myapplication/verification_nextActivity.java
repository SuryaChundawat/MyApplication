package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chari on 7/19/2016.
 */
public class verification_nextActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_post_veri;
    private EditText edtUserName, edtPassword, edtUserCode;
    Editable GetAgencyCode1= MainActivity.editAgencycode.getText();
    private ActionBar mactionbar;
    String GetAgentcode;
    public static TableRow tableRow;
    public SQLController sqlcon;
    private TableLayout table_layout;
    private ProgressDialog PD;
    public static TextView Fromno,AppId,IdNo,Add1,Add2,City,State,AgentCode;
    private static Editable GetAgencyCode,GetAgencyCode2,GetAgencyCode3,GetAgencyCode4,GetAgencyCode5,GetAgencyCode6,GetAgencyCode7,GetAgencyCode8;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_next);

        sqlcon = new SQLController(this);

        mactionbar = getSupportActionBar();
        mactionbar.setTitle( getResources().getString(R.string.VerificationNext));
        mactionbar.setDisplayHomeAsUpEnabled(true);

        findview();

        GetAgentcode=GetAgencyCode1.toString();



        new MyAsync().execute();
       /* CallMethode();*/

    }


    public verification_nextActivity()
    {


    }

    public void AddHeader()
    {
        tableRow = new TableRow(this);


        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView Check = new TextView(this);
        Check.setText("     ");
        Check.setGravity(Gravity.CENTER);
        Check.setTextColor(Color.BLUE);
        //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        Check.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        Check.setPadding(5, 5, 5, 0);
        tableRow.addView(Check);  // Adding textView to tablerow.


        TextView SrNo = new TextView(this);
        SrNo.setText(" Serial No ");
        SrNo.setGravity(Gravity.CENTER);
        SrNo.setTextColor(Color.BLUE);
        SrNo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        SrNo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        SrNo.setPadding(5, 5, 5, 0);
        tableRow.addView(SrNo);  // Adding textView to tablerow.


        TextView fromno = new TextView(this);
        fromno.setText(" From No ");
        fromno.setGravity(Gravity.CENTER);
        fromno.setTextColor(Color.BLUE);
        fromno.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        fromno.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        fromno.setPadding(5, 5, 5, 0);
        tableRow.addView(fromno);  // Adding textView to tablerow.

        TextView name = new TextView(this);
        name.setText(" Name ");
        name.setTextColor(Color.BLUE);
        name.setGravity(Gravity.CENTER);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        name.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        name.setPadding(5, 5, 5, 0);
        tableRow.addView(name);  // Adding textView to tablerow.

        TextView idtype = new TextView(this);
        idtype.setText(" Id Type ");
        idtype.setGravity(Gravity.CENTER);
        idtype.setTextColor(Color.BLUE);
        idtype.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        idtype.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        idtype.setPadding(5, 5, 5, 0);
        tableRow.addView(idtype);  // Adding textView to tablerow.

        TextView idno = new TextView(this);
        idno.setText(" Id No ");
        idno.setGravity(Gravity.CENTER);
        idno.setTextColor(Color.BLUE);
        idno.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        idno.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        idno.setPadding(5, 5, 5, 0);
        tableRow.addView(idno);  // Adding textView to tablerow.

        TextView producttype = new TextView(this);
        producttype.setText(" Product Type ");
        producttype.setGravity(Gravity.CENTER);
        producttype.setTextColor(Color.BLUE);
        producttype.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        producttype.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        producttype.setPadding(5, 5, 5, 0);
        tableRow.addView(producttype);  // Adding textView to tablerow.

        TextView prttypeflat = new TextView(this);
        prttypeflat.setText(" PtrType");
        prttypeflat.setGravity(Gravity.CENTER);
        prttypeflat.setTextColor(Color.BLUE);
        prttypeflat.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        prttypeflat.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeflat.setPadding(5, 5, 5, 0);
        tableRow.addView(prttypeflat);  // Adding textView to tablerow.

       /* TextView prttypeland = new TextView(this);
        prttypeland.setText(" PtrLand ");
        prttypeland.setGravity(Gravity.CENTER);
        prttypeland.setTextColor(Color.BLUE);
        prttypeland.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        prttypeland.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeland.setPadding(5, 5, 5, 0);
        tableRow.addView(prttypeland);  // Adding textView to tablerow.

        TextView prttypebanglow = new TextView(this);
        prttypebanglow.setText(" PtrBanglow ");
        prttypebanglow.setGravity(Gravity.CENTER);
        prttypebanglow.setTextColor(Color.BLUE);
        prttypebanglow.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        prttypebanglow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypebanglow.setPadding(5, 5, 5, 0);
        tableRow.addView(prttypebanglow);  // Adding textView to tablerow.

            TextView prttypeother = new TextView(this);
        prttypeother.setText(" PtrOther ");
        prttypeother.setGravity(Gravity.CENTER);
        prttypeother.setTextColor(Color.BLUE);
        prttypeother.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        prttypeother.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeother.setPadding(5, 5, 5, 0);
        tableRow.addView(prttypeother);  // Adding textView to tablerow.*/

        TextView address1 = new TextView(this);
        address1.setText(" Address1 ");
        address1.setGravity(Gravity.CENTER);
        address1.setTextColor(Color.BLUE);
        address1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        address1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        address1.setPadding(5, 5, 5, 0);
        tableRow.addView(address1);  // Adding textView to tablerow.


        TextView address2 = new TextView(this);
        address2.setText(" Address2 ");
        address2.setGravity(Gravity.CENTER);
        address2.setTextColor(Color.BLUE);
        address2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        address2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        address2.setPadding(5, 5, 5, 0);
        tableRow.addView(address2);  // Adding textView to tablerow.

        TextView city = new TextView(this);
        city.setText(" City ");
        city.setGravity(Gravity.CENTER);
        city.setTextColor(Color.BLUE);
        city.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        city.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        city.setPadding(5, 5, 5, 0);
        tableRow.addView(city);  // Adding textView to tablerow.


        TextView district = new TextView(this);
        district.setText(" District ");
        district.setGravity(Gravity.CENTER);
        district.setTextColor(Color.BLUE);
        district.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        district.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        district.setPadding(5, 5, 5, 0);
        tableRow.addView(district);  // Adding textView to tablerow.

       /* TextView state = new TextView(this);
        state.setText(" State ");
        state.setGravity(Gravity.CENTER);
        state.setTextColor(Color.BLUE);
        state.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        state.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        state.setPadding(5, 5, 5, 0);
        tableRow.addView(state);  // Adding textView to tablerow.

        TextView agentcode = new TextView(this);
        agentcode.setText(" Agent Code ");
        agentcode.setGravity(Gravity.CENTER);
        agentcode.setTextColor(Color.BLUE);
        agentcode.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        agentcode.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        agentcode.setPadding(5, 5, 5, 0);
        tableRow.addView(agentcode);  // Adding textView to tablerow.*/




        table_layout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

            //For Deivider----------------



        tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));




        TextView Chek1 = new TextView(this);
        Chek1.setText("-----");
        Chek1.setVisibility(View.INVISIBLE);
        Chek1.setTextColor(Color.BLUE);
        Chek1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        Chek1.setPadding(5, 0, 0, 0);
        tableRow.addView(Chek1); // Adding textView to tablerow.


        TextView SrNo1 = new TextView(this);
        SrNo1.setText("-----------------");
        SrNo1.setTextColor(Color.BLUE);
        SrNo1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        SrNo1.setPadding(5, 0, 0, 0);
        tableRow.addView(SrNo1); // Adding textView to tablerow.




        TextView fromno1 = new TextView(this);
            fromno1.setText("-----------------");
        fromno1.setTextColor(Color.BLUE);
        fromno1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        fromno1.setPadding(5, 0, 0, 0);
        tableRow.addView(fromno1); // Adding textView to tablerow.


            TextView name1 = new TextView(this);
        name1.setText("-----------------");
        name1.setTextColor(Color.BLUE);
        name1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        name1.setPadding(5, 0, 0, 0);
        tableRow.addView(name1); // Adding textView to tablerow.

            TextView idtype1 = new TextView(this);
        idtype1.setText("-----------------");
        idtype1.setTextColor(Color.BLUE);
        idtype1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        idtype1.setPadding(5, 0, 0, 0);
        tableRow.addView(idtype1); // Adding textView to tablerow.

            TextView idno1 = new TextView(this);
        idno1.setText("-----------------");
        idno1.setTextColor(Color.BLUE);
        idno1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        idno1.setPadding(5, 0, 0, 0);
        tableRow.addView(idno1); // Adding textView to tablerow.

            TextView producttype1 = new TextView(this);
        producttype1.setText("-----------------");
        producttype1.setTextColor(Color.BLUE);
        producttype1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        producttype1.setPadding(5, 0, 0, 0);
        tableRow.addView(producttype1); // Adding textView to tablerow.


            TextView prttypeflat1 = new TextView(this);
        prttypeflat1.setText("-----------------");
        prttypeflat1.setTextColor(Color.BLUE);
        prttypeflat1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeflat1.setPadding(5, 0, 0, 0);
        tableRow.addView(prttypeflat1); // Adding textView to tablerow.


        /*    TextView prttypeland1 = new TextView(this);
        prttypeland1.setText("-----------------");
        prttypeland1.setTextColor(Color.BLUE);
        prttypeland1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeland1.setPadding(5, 0, 0, 0);
        tableRow.addView(prttypeland1); // Adding textView to tablerow.


            TextView prttypebanglow1 = new TextView(this);
        prttypebanglow1.setText("-----------------");
        prttypebanglow1.setTextColor(Color.BLUE);
        prttypebanglow1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypebanglow1.setPadding(5, 0, 0, 0);
        tableRow.addView(prttypebanglow1); // Adding textView to tablerow.


            TextView prttypeother1 = new TextView(this);
        prttypeother1.setText("-----------------");
        prttypeother1.setTextColor(Color.BLUE);
        prttypeother1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        prttypeother1.setPadding(5, 0, 0, 0);
        tableRow.addView(prttypeother1 ); // Adding textView to tablerow.*/


        TextView address11 = new TextView(this);
        address11.setText("-----------------");
        address11.setTextColor(Color.BLUE);
        address11.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        address11.setPadding(5, 0, 0, 0);
        tableRow.addView(address11 ); // Adding textView to tablerow.


        TextView address21 = new TextView(this);
        address21.setText("-----------------");
        address21.setTextColor(Color.BLUE);
        address21.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        address21.setPadding(5, 0, 0, 0);
        tableRow.addView(address21 ); // Adding textView to tablerow.


        TextView city1 = new TextView(this);
        city1.setText("-----------------");
        city1.setTextColor(Color.BLUE);
        city1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        city1.setPadding(5, 0, 0, 0);
        tableRow.addView(city1 ); // Adding textView to tablerow.


        TextView district1 = new TextView(this);
        district1.setText("-----------------");
        district1.setTextColor(Color.BLUE);
        district1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        district1.setPadding(5, 0, 0, 0);
        tableRow.addView(district1 ); // Adding textView to tablerow.


       /* TextView state1 = new TextView(this);
        state1.setText("-----------------");
        state1.setTextColor(Color.BLUE);
        state1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        state1.setPadding(5, 0, 0, 0);
        tableRow.addView(state1 ); // Adding textView to tablerow.

        TextView agentcode1 = new TextView(this);
        agentcode1.setText("-----------------");
        agentcode1.setTextColor(Color.BLUE);
        agentcode1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        agentcode1.setPadding(5, 0, 0, 0);
        tableRow.addView(agentcode1 ); // Adding textView to tablerow.*/





        table_layout.addView(tableRow, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));




        }







    public  void BuildTable()
    {

        sqlcon.open();
        Cursor c = sqlcon.readEntry();

        int rows = c.getCount();
        int cols = c.getColumnCount();

        c.moveToFirst();

        AddHeader();

        // outer for loop
        for (int i = 0; i < rows; i++)
        {


            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            row.addView(checkBox);

            // inner for loop
            for (int j = 0; j < cols; j++)
            {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                /*tv.setBackgroundResource(R.drawable.lightcolour);*/
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);
                tv.setText(c.getString(j));

                row.addView(tv);

            }

            c.moveToNext();

            table_layout.addView(row);

        }
        sqlcon.close();
    }

    private class MyAsync extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {

            super.onPreExecute();

            table_layout.removeAllViews();

            PD = new ProgressDialog(verification_nextActivity.this);
            PD.setTitle("Please Wait..");
            PD.setMessage("Loading...");
            PD.setCancelable(false);
            PD.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            BuildTable();
            PD.dismiss();
        }
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


    public void PostData(View view)
    {
        final Dialog dialog = new Dialog(verification_nextActivity.this);
        dialog.setContentView(R.layout.userdetailsverification);


        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = sqlcon.Passwordupload(password);
                Log.e("StorePassword is ",storedPassword);
                if (password.equals(storedPassword))
                {
                    dialog.dismiss();
                   if (haveNetworkConnection()==true)
                    {
                        new CommonServerSenderHelper(verification_nextActivity.this).FormDetailsPostTest();
                        Toast.makeText(verification_nextActivity.this, "User Login Successfull", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(verification_nextActivity.this,Verificatio_activity.class));

                    }else
                    {

                        AlertDialog alertbox = new AlertDialog.Builder(verification_nextActivity.this)
                                .setMessage("Please Check Network")
                                .setPositiveButton("OK", null)
                                .show();

                    }




                } else
                {
                   Toast.makeText(verification_nextActivity.this, "User Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        }); dialog.show();


        //sqlcon.Update_Deposit_Table_Details(sRow_Index);

        //Toast.makeText(verification_nextActivity.this, "added successfully.", Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(), "Work Under Construction", Toast.LENGTH_SHORT).show();

       /* Snackbar snackbar = Snackbar
                .make(view, "added successfully. ", Snackbar.LENGTH_LONG);
        Login_Sign_Up_Activity.info(snackbar).show();*/




    }

    public void findview()
    {
        //Button
        btn_post_veri = (Button) findViewById(R.id.btn_post_veri);

        //TableLayOut
        table_layout = (TableLayout) findViewById(R.id.TableDy);

    }







    @Override
    public void onClick(View view)
    {
        PostData(view);
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
                .setMessage("Exit Post/Upload ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        verification_nextActivity.this.finish();
                        Intent intent1 = new Intent(verification_nextActivity.this, Verificatio_activity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);


                       /* Intent intent = new Intent(verification_nextActivity.this, Verificatio_activity.class);
                        startActivity(intent);
                        verification_nextActivity.super.onBackPressed();
                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }




}
