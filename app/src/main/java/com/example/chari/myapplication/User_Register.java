package com.example.chari.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chari on 8/24/2016.
 */
public class User_Register extends AppCompatActivity
{
    EditText edit_UserName,edit_UserPassword,edit_UserConfPassword,edit_UserEmail,edit_UserAge,edit_UserPhoneNo;
    Button btn_ver_reg;
    SQLController sqlcon;
    String User_Name,User_Email,User_Password,User_ConfimPass,User_Age,User_MoblieNu;
    private String ImEINo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        sqlcon = new SQLController(this);
        sqlcon=sqlcon.open();
        findView();
        IMEI();
        Shap();


    }

    public void GetText()
    {
        User_Name=edit_UserName.getText().toString();
        User_Email=edit_UserEmail.getText().toString();
        User_Password=edit_UserPassword.getText().toString();
        User_ConfimPass=edit_UserConfPassword.getText().toString();
        User_Age=edit_UserAge.getText().toString();
        User_MoblieNu=edit_UserPhoneNo.getText().toString();
    }

    public void RegisterUser(View v)
    {
        GetText();

        if(User_Name.equals("")||User_Email.equals("")||User_Password.equals("")||User_ConfimPass.equals("")||User_Age.equals("")||User_MoblieNu.equals(""))
        {

            Snackbar snackbar = Snackbar
                    .make(v, "Empty Please Fill", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            return;
        }else if (!(User_Password.equals(User_ConfimPass)))
        {

            Snackbar snackbar = Snackbar
                    .make(v, "Password Not Match", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            return;
        }else
        {

            sqlcon.InsertUserRegister(User_Name, User_Password, User_Email, User_Age, User_MoblieNu, ImEINo);
            Snackbar snackbar = Snackbar
                    .make(v, "User Register SuccessFully", Snackbar.LENGTH_LONG);
            Login_Sign_Up_Activity.info(snackbar).show();
            CllearAllfield();
            return;
        }
    }

    private String IMEI()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();
        return ImEINo;
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

        //Button
        btn_ver_reg=(Button)findViewById(R.id.btn_ver_reg);

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
