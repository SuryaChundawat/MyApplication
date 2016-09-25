package com.example.chari.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static EditText editAgencycode,editAgentPass;
    Button btnlogin,signUp;
    SQLController sqlcon;
    private CoordinatorLayout coordinatorLayout;
    private static final int blue = 0xff0000ff;
    public static String AgentCode;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlcon = new SQLController(this);
        sqlcon=sqlcon.open();

        findviewbyid();
        Shap();
        signUp.setPaintFlags(signUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AgentCode=editAgencycode.getText().toString();
                String Password=editAgentPass.getText().toString();

                String storedPassword=sqlcon.getSinlgeEntry(AgentCode);

                if(AgentCode.equals(storedPassword))
                {


                   /* View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.BLUE);

                    snackbar.show();*/


                    //Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Verificatio_activity.class);
                    startActivity(intent);
                    finish();





                }else
                {

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Agency Code does not match", Snackbar.LENGTH_LONG);

                    MainActivity.info(snackbar).show();
                   /* View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.BLUE);*/

                   // snackbar.show();
                    //Toast.makeText(MainActivity.this, "Agency Code does not match", Toast.LENGTH_LONG).show();
                }


            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,Login_Sign_Up_Activity.class);
                startActivity(intent);
                finish();

            }
        });




    }

    public void Shap() {


        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.WHITE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(9);

        editAgencycode.setBackground(shape);



       /* ShapeDrawable shape2 = new ShapeDrawable(new RectShape());
        shape2.getPaint().setColor(Color.WHITE);
        shape2.getPaint().setStyle(Paint.Style.STROKE);
        shape2.getPaint().setStrokeWidth(9);

        btnlogin.setBackground(shape2);*/


    }

    private static View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static Snackbar colorSnackBar(Snackbar snackbar, int colorId) {
        View snackBarView = getSnackBarLayout(snackbar);
        if (snackBarView != null)
        {
            snackBarView.setBackgroundColor(colorId);

        }

        return snackbar;
    }

    public static Snackbar info(Snackbar snackbar) {
        return colorSnackBar(snackbar, blue);
    }



    public  void findviewbyid()
    {
        //EditText
        editAgencycode=(EditText)findViewById(R.id.editAgencycode);
        editAgentPass=(EditText)findViewById(R.id.editAgentPass);

        //Button
        btnlogin=(Button)findViewById(R.id.btnlogin);
        signUp=(Button)findViewById(R.id.signUp);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

    }
}
