package com.example.chari.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Chari on 7/25/2016.
 */
public class SQLController
{
    private DbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
   /* Editable GetAgencyCode= MainActivity.editAgencycode.getText();*/

    public SQLController(Context c)
    {
        ourcontext = c;
    }

    public SQLController open() throws SQLException
    {
        dbhelper = new DbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;

    }


    public void close() {
        dbhelper.close();
    }

    public void insertData(String fromno,
                           String name,
                           String idtype,
                           String idno,
                           String producttype,
                           String prttypeflat,
                          /* String prttypeland,
                           String prttypebanglow,
                           String prttypeother,*/
                           String address1,
                           String address2,
                           String city,
                           String district
                         )/*  String state,
                           String agentcode*/
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO,fromno);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_FORM_NO,name);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_PROOF,idtype);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_NO,idno);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE,producttype);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE,prttypeflat);
        /*contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_LAND,prttypeland);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_BANGLOW,prttypebanglow);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_OTHER,prttypeother);*/
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS1,address1);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS2,address2);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_CITY,city);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_DISTRICT,district);
       /* contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_STATE,state);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_AGENTCODE,agentcode);*/

        database.insert(DbHelper.FORM_TABLE_DETAILS, null, contentValues);

    }



    public void insertDataBanglow(String frntleftlat,
                           String frntleftlong,
                           String frntleftalt,
                           String frntrightlat,
                           String frntrightlong,
                           String frntrightalt,
                           String backleftlat,
                           String backleftlong,
                           String backleftalt,
                           String backrightlat,
                           String backrightlong,
                           String backrightalt,
                           String imeino,
                           String agentName,
                           String agencycode
                                  )
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLAT,frntleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLONG,frntleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTALTI,frntleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLAT,frntrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLONG,frntrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTALTI,frntrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLAT,backleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLONG,backleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTALT,backleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLAT,backrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLONG,backrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTALT,backrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IMEI_NO,imeino);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_USERNASME,agentName);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_AGENCYCODE,agencycode);

        database.insert(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS, null, contentValues);

    }

    public void insertDataLand(String frntleftlat,
                                  String frntleftlong,
                                  String frntleftalt,
                                  String frntrightlat,
                                  String frntrightlong,
                                  String frntrightalt,
                                  String backleftlat,
                                  String backleftlong,
                                  String backleftalt,
                                  String backrightlat,
                                  String backrightlong,
                                  String backrightalt,
                                  String imeino,
                                  String username,
                                  String agencycode

                               )
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLAT,frntleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLONG,frntleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTALTI,frntleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLAT,frntrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLONG,frntrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTALTI,frntrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLAT,backleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLONG,backleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTALT,backleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLAT,backrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLONG,backrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTALT,backrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IMEI_NO_LAND,imeino);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_USERNASME,username);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_AGENCYCODE,agencycode);

        database.insert(DbHelper.FORM_TABLE_GEOTAG_LAND_DETAILS, null, contentValues);

    }

    public void insertDataOther(String frntleftlat,
                                  String frntleftlong,
                                  String frntleftalt,
                                  String frntrightlat,
                                  String frntrightlong,
                                  String frntrightalt,
                                  String backleftlat,
                                  String backleftlong,
                                  String backleftalt,
                                  String backrightlat,
                                  String backrightlong,
                                  String backrightalt,
                                  String imeino,
                                  String username,
                                  String agencycode
                                )
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLAT,frntleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLONG,frntleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTALTI,frntleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLAT,frntrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLONG,frntrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTALTI,frntrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLAT,backleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLONG,backleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTALT,backleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLAT,backrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLONG,backrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTALT,backrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IMEI_NO_OTHER,imeino);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_USERNASME,username);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_AGENCYCODE,agencycode);


        database.insert(DbHelper.FORM_TABLE_GEOTAG_OTHER_DETAILS, null, contentValues);

    }


    public void insertResponeLogin(String Agency_Name)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS,Agency_Name);

        database.insert(DbHelper.LOGIN_RESPONCE_TABLE, null ,contentValues);


    }





    public Cursor readEntry()
    {

        String[] allColumns = new String[]
                {DbHelper.COLUMN_ID,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_FORM_NO,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_PROOF,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_NO,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE,
                /*DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_LAND,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_BANGLOW,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_OTHER,*/
                DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS1,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS2,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_CITY,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_DISTRICT
                };
                /*DbHelper.LOCAL_FORM_DETAILS_TABLE_STATE,
                DbHelper.LOCAL_FORM_DETAILS_TABLE_AGENTCODE*/

       /* final String my_count = " SELECT COUNT(*) FROM " + DbHelper.FORM_TABLE_DETAILS + " WHERE " +
                DbHelper.LOCAL_FORM_DETAILS_TABLE_AGENTCODE  + " = '" + Agentcode + "' ";

        Cursor cursor2 = database.rawQuery(my_count, null);*/

        /*Log.e("Get all Row from",my_count);*/






        Cursor c = database.query(DbHelper.FORM_TABLE_DETAILS, allColumns, null, null, null,
                null, null);



       /* Cursor c = database.query(DbHelper.FORM_TABLE_DETAILS, allColumns, null, null, null,
                null, null);*/

        if (c != null)
        {
            c.moveToFirst();
        }
        return c;

    }

    //For LoginActivity

    public void InsertLoginEntry(String AgentCode,String Password)
    {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID, AgentCode);
        cv.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_SIGNUP_PASSWORD,Password);

        database.insert(DbHelper.LOGIN_TABLE, null, cv);



    }

    //For LoginActivity

    public void InsertUserRegister(String Name,String Password,String Email,String Age,String MoblieNu,String ImeiNo)
    {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_NAME, Name);
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_PASSWORD,Password);
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_EMAIL,Email);
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_AGE,Age);
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_MOBILENU,MoblieNu);
        cv.put(DbHelper.LOCAL_FORM_USER_REGISTER_IMEINO,ImeiNo);

        database.insert(DbHelper.USER_REGISTER_TABLE, null, cv);



    }

    //For fLAT

    public void InsertForFlat(Double Lat,Double Long,Double Altitude,String State,String UserName,String AgencyCode)
    {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LAT, Lat);
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LONG,Long);
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_ALTITUDE,Altitude);
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_STATE,State);
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_USERNAME,UserName);
        cv.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_AGENCYCODE,AgencyCode);

        database.insert(DbHelper.FORM_TABLE_DETAILS, null, cv);



    }




    public String getSinlgeEntry(String AgentCode)
    {
        open();

        Cursor cursor;
        cursor = database.query(DbHelper.LOGIN_TABLE, null, DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID + "=? " , new String[]{AgentCode}, null, null, null);
        //cursor=database.query(DbHelper.LOGIN_TABLE, null, DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID, new String[]{AgentCode}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID));
        cursor.close();
        return password;
    }


    public boolean checkUserLogin(String username,String password)
    {
        boolean flag;
        open();
        Cursor cursor;

        cursor = database.query(DbHelper.LOGIN_TABLE, null, DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID + "=? AND " + DbHelper.LOCAL_FORM_DETAILS_TABLE_SIGNUP_PASSWORD + " =?", new String[]{username, password}, null, null, null);
        if (cursor != null && cursor.getCount() > 0)
        {
            flag = true;
        } else {
            flag = false;
        }
        close();

        return flag;

    }

    public String getResponceagency()
    {
        ArrayList<String> list = new ArrayList<String>();
        String resagencyname = "";

        open();

        final String MY_QUERY2 = " SELECT  " + DbHelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS +
            " FROM " + DbHelper.LOGIN_RESPONCE_TABLE ;
        Cursor cursor = database.rawQuery(MY_QUERY2, null);


       /* Cursor cursor = database.query(DbHelper.LOGIN_RESPONCE_TABLE, null, DbHelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS + "=?", null, null, null, null);*/

        Log.e("cursor Value",cursor.toString());

        if (cursor != null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                resagencyname=cursor.getString(cursor.getColumnIndex(dbhelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS));

            }while (cursor.moveToNext());
            cursor.close();
        }

        close();
        return resagencyname;


    }

    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID, userName);
        updatedValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_SIGNUP_PASSWORD,password);

        String where="LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID=?";
        database.update(DbHelper.LOGIN_TABLE,updatedValues, where, new String[]{userName});
    }

    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID=?";
        int numberOFEntriesDeleted= database.delete(DbHelper.LOGIN_TABLE, where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }



}
