package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chari on 7/17/2016.
 */
public class Verificatio_activity extends AppCompatActivity {

    private Button btnverification,btnpostupload,btnlogout;
    private ActionBar mactionbar;
    private LinearLayout Ver_text;
    private TextView text_agencyCode,text_UserName;
    public static String GetAgentCodeMain;
    SQLController sqlcon;


    final Handler mHandler = new Handler();

    private String mResult;

    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            Log.d("Inchoo tutorial", mResult);
        }
    };

    protected void startTestThread()
    {
        Thread t = new Thread() {
            public void run() {

                Intent intent = new Intent(Verificatio_activity.this, verification_nextActivity.class);
                startActivity(intent);



                Log.d("Inchoo tutorial", "My thread is running");
                mResult = "This is my new result";
                mHandler.post(mUpdateResults);
            }
        };

        t.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_activity);

        sqlcon = new SQLController(this);
        sqlcon=sqlcon.open();

        mactionbar = getSupportActionBar();
        mactionbar.setTitle(getResources().getString(R.string.VerificationActivity));
        mactionbar.setDisplayHomeAsUpEnabled(true);
        findView();
        Shap();
        SetText();

        GetAgentCodeMain=User_Register.UserName;










        btnverification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Verificatio_activity.this,FormActivity.class);
                startActivity(i);
                finish();


            }
        });

        btnpostupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTestThread();

              /*  Intent intent = new Intent(Verificatio_activity.this,verification_nextActivity.class);
                startActivity(intent);*/
                finish();

               /* Toast.makeText(getApplicationContext(),"Data is Uploaded",Toast.LENGTH_SHORT).show();*/
            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(Verificatio_activity.this,MainActivity.class);
                startActivity(intent);



               // Toast.makeText(getApplicationContext(),"LOgOut",Toast.LENGTH_SHORT).show();
                finish();

                Toast.makeText(getApplicationContext(),"LogOut Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void SetText()
    {

        //GetAgentCodeMain=sqlcon.getUserName();


        Log.e("Responce fromm table",sqlcon.getResponceagency());
        Log.e("Responce fromm table",sqlcon.getUserName());



        text_agencyCode.setText("Agency :- "+sqlcon.getResponceagency());
        text_UserName.setText("User :- "+sqlcon.getUserName());


    }

    public void  findView()
    {
        btnverification=(Button)findViewById(R.id.btnverification);
        btnpostupload=(Button)findViewById(R.id.btnpostupload);
        btnlogout=(Button)findViewById(R.id.btnlogout);

        text_agencyCode=(TextView)findViewById(R.id.text_agencyCode);
        text_UserName=(TextView)findViewById(R.id.text_UserName);

        Ver_text=(LinearLayout)findViewById(R.id.Ver_text);




    }

    public void Shap() {


        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.WHITE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(9);

        //Ver_text.setBackground(shape);



       /* ShapeDrawable shape2 = new ShapeDrawable(new RectShape());
        shape2.getPaint().setColor(Color.WHITE);
        shape2.getPaint().setStyle(Paint.Style.STROKE);
        shape2.getPaint().setStrokeWidth(9);

        btnlogin.setBackground(shape2);*/


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onBackPressed();
        /*switch (item.getItemId())
        {
            case R.id.home:

                Log.e("Backinside","Insideitscoming");
                //NavUtils.navigateUpFromSameTask(this);

                return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        // Write your code here
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit Verification ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Verificatio_activity.this.finish();
                        Intent intent1 = new Intent(Verificatio_activity.this, MainActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);

                        /*Intent intent = new Intent(Verificatio_activity.this, MainActivity.class);
                        startActivity(intent);

                        Verificatio_activity.super.onBackPressed();
                        finish();*/
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }


}
