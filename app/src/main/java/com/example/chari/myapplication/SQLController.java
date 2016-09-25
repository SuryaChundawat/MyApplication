package com.example.chari.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                           String address1,
                           String address2,
                           String city,
                           String district
                         )
    {

        String one_row_checker_no = "";
        long long_insert_row_index;
        open();
        Cursor cursor = database.query(DbHelper.FORM_TABLE_DETAILS, null, null, null, null, null, null);
        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToLast();
            one_row_checker_no = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_UNIQEKEY));
            one_row_checker_no = "" + ((Integer.parseInt(one_row_checker_no) + 1));


        }else
        {
            one_row_checker_no = "1";

        }


        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_UNIQEKEY, one_row_checker_no);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO, fromno);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_FORM_NO, name);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_PROOF, idtype);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_NO, idno);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE, producttype);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE, prttypeflat);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS1, address1);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS2, address2);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_CITY, city);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_DISTRICT, district);

        database.insert(DbHelper.FORM_TABLE_DETAILS, null, contentValues);

    }



    public void insertDataCommun(String frntleftlat,
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


        Log.e("",frntleftlat);
        Log.e("",frntleftlong);
        Log.e("",frntleftalt);
        Log.e("",frntrightlat);
        Log.e("",frntrightlong);
        Log.e("",frntrightalt);
        Log.e("",backleftlat);
        Log.e("",backleftlong);
        Log.e("",backleftalt);
        Log.e("",backrightlat);
        Log.e("",backrightlong);
        Log.e("",backrightalt);
        Log.e("",imeino);
        Log.e("",agentName);
        Log.e("",agencycode);



        open();
        String one_row_checker_no = "";
        Cursor cursor = database.query(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS, null, null, null, null, null, null);

        if (cursor !=null&& cursor.getCount() > 0)
        {

            cursor.moveToLast();
            one_row_checker_no = cursor.getString(cursor.getColumnIndex(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS));
            one_row_checker_no = "" + ((Integer.parseInt(one_row_checker_no) + 1));
            cursor.close();
        }else
        {
            one_row_checker_no = "1";
        }

        Log.e("One Row Checker",one_row_checker_no);


        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS, one_row_checker_no);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLAT, frntleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLONG, frntleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTALTI, frntleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLAT, frntrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLONG, frntrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTALTI, frntrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLAT, backleftlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLONG, backleftlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTALT, backleftalt);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLAT ,backrightlat);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLONG, backrightlong);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTALT, backrightalt);
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_IMEI_NO, imeino);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_USERNASME, agentName);
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_AGENCYCODE, agencycode);

        database.insert(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS, null, contentValues);

    }


    public boolean DeleteTable()
    {

        open();
        database.delete(DbHelper.USER_REGISTER_TABLE,null,null);
        database.delete(DbHelper.LOGIN_TABLE,null,null);


        return true;

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
                                  String florno,
                                  String imeino,
                                  String username,
                                  String agencycode

                               )
    {

        Log.e("",frntleftlat);
        Log.e("",frntleftlong);
        Log.e("",frntleftalt);
        Log.e("",frntrightlat);
        Log.e("",frntrightlong);
        Log.e("",frntrightalt);
        Log.e("",backleftlat);
        Log.e("",backleftlong);
        Log.e("",backleftalt);
        Log.e("",backrightlat);
        Log.e("",backrightlong);
        Log.e("",backrightalt);
        Log.e("",imeino);
        Log.e("",username);
        Log.e("",agencycode);

        open();
        String one_row_checker_no = "";
        Cursor cursor = database.query(DbHelper.FORM_TABLE_GEOTAG_LAND_DETAILS, null, null, null, null, null, null);

        if (cursor !=null&& cursor.getCount() > 0)
        {

            cursor.moveToLast();
            one_row_checker_no = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_GEO_ID));
            one_row_checker_no = "" + ((Integer.parseInt(one_row_checker_no) + 1));
            cursor.close();
        }else
        {
            one_row_checker_no = "1";
        }

        Log.e("One Row Checker",one_row_checker_no);


        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_GEO_ID,one_row_checker_no);
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
        contentValues.put(DbHelper.LOCAL_FORM_DETAILS_TABLE_FLOR_NO,florno);
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

        database.insert(DbHelper.FORM_TABLE_FLAT, null, cv);



    }


    public String Passwordupload(String password)
    {
        open();
        Cursor cursor;
        cursor = database.query(DbHelper.USER_REGISTER_TABLE, null, DbHelper.LOCAL_FORM_USER_REGISTER_PASSWORD + "=? ", new String[]{password}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password1= cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_USER_REGISTER_PASSWORD));
       // Log.e("Verification password is",password1.toString());
        cursor.close();
        return password1;

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

    public void deleteLocalFormDetails()
    {
        open();
        int count;
        Log.e("Primary key","Insert to delete");

        final String MY_QUERY = "DELETE " +
                " FROM " + DbHelper.FORM_TABLE_DETAILS ;

        Cursor cursor = database.rawQuery(MY_QUERY, null);
        Log.e("Deposite Delete Query",MY_QUERY);


        Cursor cursor3 = database.query(DbHelper.FORM_TABLE_DETAILS, null, null, null, null, null, null);
        if (cursor.getCount() > 0)
        {
            count = cursor.getCount();
        }
        else
        {
            count = 0;
        }
        String Scount ="";
        Scount=String.valueOf(count);
        Log.e("Header rows Count",Scount);

        final String MY_QUERY2 = "DELETE " +
                " FROM " + DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS +
                " WHERE " + DbHelper.COLUMN_ID_GEO_BANGLOW  ;
        Cursor cursor2 = database.rawQuery(MY_QUERY2, null);
        Log.e("DepositeDetail Delete ",MY_QUERY2);

        Cursor cursor4 = database.query(DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS, null, null, null, null, null, null);
        if (cursor.getCount() > 0)
        {
            count = cursor.getCount();
        }
        else
        {
            count = 0;
        }
        String Scount2 ="";
        Scount2=String.valueOf(count);
        Log.e("Details rows count",Scount2);


        //database.delete(DbHelper.FORM_TABLE_DETAILS, null, null);
        close();
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

    public boolean checkLocalFormCount()
    {
        open();

        boolean flag = false;
        Cursor cursor = database.query(DbHelper.FORM_TABLE_DETAILS, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            flag = true;
        }

        close();

        return flag;

    }




    public String getFormDetailsData()
    {
        String jsonString = "[]";
        String row_index_to_delete="";
        String row_index_to_delete2="";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject_details = new JSONObject();

        String row_id = "";
        open();

        String uniqe_id="";
        String app_id="";
        String form_id="";
        String id_proof="";
        String id_proof_no="";
        String pr_type="";
        String prty_type="";
        String add1_id="";
        String add2_id="";
        String city_id="";
        String distric_id="";

        String clumn_id2="";
        String fr_lft_lat="";
        String fr_lft_long="";
        String fr_lft_alt="";
        String fr_rgt_lat="";
        String fr_rgt_long="";
        String fr_rgt_alt="";
        String bk_lft_lat="";
        String bk_lft_long="";
        String bk_lft_alt="";
        String bk_rg_lat="";
        String bk_rg_long="";
        String bk_rg_alt="";
        String flor_no="";
        String imei_no="";
        String user_name="";
        String agent_code="";





       // Cursor cursor = database.query(DbHelper.FORM_TABLE_DETAILS, null, null, null, null, null, null);

        Cursor cursor = database.query(DbHelper.FORM_TABLE_DETAILS, null, null, null, null, null, DbHelper.COLUMN_ID+ " DESC");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            row_index_to_delete = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ID));
            Log.e("Row index Delete", row_index_to_delete);

            uniqe_id = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ID));
            app_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO));
            form_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_FORM_NO));
            id_proof = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_PROOF));
            id_proof_no = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_ID_NO));
            pr_type = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE));
            prty_type = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE));
            add1_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS1));
            add2_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_ADDRESS2));
            city_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_CITY));
            distric_id = cursor.getString(cursor.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_DISTRICT));

            Log.e("All Sent Data 1", uniqe_id);
            Log.e("All Sent Data 2", app_id);
            Log.e("All Sent Data3", form_id);
            Log.e("All Sent Data4", id_proof);
            Log.e("All Sent Data6", id_proof_no);
            Log.e("All Sent Data7", pr_type);
            Log.e("All Sent Data8", prty_type);
            Log.e("All Sent Data9", add1_id);
            Log.e("All Sent Data10", add2_id);
            Log.e("All Sent Data11", city_id);
            Log.e("All Sent Data12", distric_id);






        }

        //Cursor cursor2 = database.query(DbHelper.FORM_TABLE_GEOTAG_LAND_DETAILS, null, null, null, null, null, null);

        Cursor cursor2 = database.query(DbHelper.FORM_TABLE_GEOTAG_LAND_DETAILS, null, null, null, null, null, DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_GEO_ID+ " DESC");

        if (cursor2 != null && cursor.getCount() > 0)
        {
            cursor2.moveToFirst();
            row_index_to_delete2 = cursor2.getString(cursor2.getColumnIndex(DbHelper.COLUMN_ID));
            Log.e("Row index Delete", row_index_to_delete2);

            clumn_id2= cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_GEO_ID));
            fr_lft_lat = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLAT));
            fr_lft_long = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLONG));
            fr_lft_alt = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTALTI));
            fr_rgt_lat = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLAT));
            fr_rgt_long = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLONG));
            fr_rgt_alt = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTALTI));
            bk_lft_lat = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLAT));
            bk_lft_long = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLONG));
            bk_lft_alt = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTALT));
            bk_rg_lat = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLAT));
            bk_rg_long = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLONG));
            bk_rg_alt = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTALT));
            flor_no=cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_FLOR_NO));
            imei_no = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_DETAILS_TABLE_IMEI_NO_LAND));
            user_name = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_USERNASME));
            agent_code = cursor2.getString(cursor2.getColumnIndex(DbHelper.LOCAL_FORM_GEO_DETAILS_TABLE_LAND_AGENCYCODE));

            Log.e("All Sent Data1*", clumn_id2);
            Log.e("All Sent Data13", fr_lft_lat);
            Log.e("All Sent Data14", fr_lft_alt);
            Log.e("All Sent Data15", fr_rgt_lat);
            Log.e("All Sent Data16", fr_rgt_long);
            Log.e("All Sent Data17", fr_rgt_alt);
            Log.e("All Sent Data18", bk_lft_lat);
            Log.e("All Sent Data19", bk_lft_long);
            Log.e("All Sent Data20", bk_lft_alt);
            Log.e("All Sent Data21", bk_rg_lat);
            Log.e("All Sent Data22", bk_rg_long);
            Log.e("All Sent Data23", bk_rg_alt);
            Log.e("All Sent Data24", imei_no);
            Log.e("All Sent Data25", user_name);
            Log.e("All Sent Data26", agent_code);







        }

        do {

                    jsonObject_details = new JSONObject();
                    try {

                        jsonObject_details.put("ApplicationNo", app_id);
                        jsonObject_details.put("ClientID", "213");
                        jsonObject_details.put("CustomerName", "CDE");
                        jsonObject_details.put("PANNo", "AGLER2345G");
                        jsonObject_details.put("IDType", id_proof);
                        jsonObject_details.put("IDNo", id_proof_no);
                        jsonObject_details.put("PropertyType", pr_type);
                        jsonObject_details.put("Product", prty_type);
                        jsonObject_details.put("Address1", add1_id);
                        jsonObject_details.put("Address2", add2_id);
                        jsonObject_details.put("Address2", "mno");
                        jsonObject_details.put("City", "193");
                        jsonObject_details.put("State", "6");
                        jsonObject_details.put("PinCode", "321002");
                        jsonObject_details.put("LatFrLeft", fr_lft_lat);
                        jsonObject_details.put("LongFrLeft", fr_lft_long);
                        jsonObject_details.put("AltFrLeft", fr_lft_alt);
                        jsonObject_details.put("LatFrRight", fr_rgt_lat);
                        jsonObject_details.put("LongFrRight", fr_rgt_long);
                        jsonObject_details.put("AltFrRight", fr_rgt_alt);
                        jsonObject_details.put("LatBkLeft", bk_lft_lat);
                        jsonObject_details.put("LongBkLeft", bk_lft_long);
                        jsonObject_details.put("AltBkLeft", bk_lft_alt);
                        jsonObject_details.put("LatBkRight", bk_rg_lat);
                        jsonObject_details.put("LongBkRight", bk_rg_long);
                        jsonObject_details.put("AltBkRight", bk_rg_alt);
                        jsonObject_details.put("FloorNo", flor_no);
                        jsonObject_details.put("OfficerName", user_name);
                        jsonObject_details.put("AgencyCode", agent_code);

                        jsonArray.put(jsonObject_details);
                        Log.e("All Json array", jsonArray.toString());

                        jsonObject1.put("jsonObject_Details",jsonArray);

                        Log.e("All Json Object",jsonObject1.toString());








                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }   while (cursor.moveToNext());

        cursor.close();
        cursor2.close();

        jsonString=jsonObject1.toString();













       /* if (cursor.getCount() > 0) {
            flag = true;
        }

        close();
*/
        return row_index_to_delete+","+jsonString;

    }



    public String Update_Deposit_Table_Details(String clomnId)
    {
        open();
        String sRetrun = "";
        final String MY_QUERY1 = " UPDATE " + DbHelper.FORM_TABLE_DETAILS +
                " WHERE " + DbHelper.COLUMN_ID + " = '" + clomnId + "'";
        Cursor cursor = database.rawQuery(MY_QUERY1, null);

        final String MY_QUERY2 = " UPDATE " + DbHelper.FORM_TABLE_GEOTAG_BANGLOW_DETAILS +
                " WHERE " + DbHelper.COLUMN_ID_GEO_BANGLOW + " = '" + clomnId + "'";
        Cursor cursor3 = database.rawQuery(MY_QUERY2, null);

        if (cursor3 != null && cursor3.getCount() > 0) {
            cursor3.moveToFirst();
            sRetrun = "Success";
        } else {
            sRetrun = "UnSuccess";
        }

        close();
        return sRetrun;

    }


    public String getResponceagency()
    {

        String resagencyname = "";

        open();

        final String MY_QUERY2 = " SELECT  " + DbHelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS +
            " FROM " + DbHelper.LOGIN_RESPONCE_TABLE ;
        Cursor cursor = database.rawQuery(MY_QUERY2, null);


       /* Cursor cursor = database.query(DbHelper.LOGIN_RESPONCE_TABLE, null, DbHelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS + "=?", null, null, null, null);*/



        if (cursor != null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                resagencyname=cursor.getString(cursor.getColumnIndex(dbhelper.LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS));


            }while (cursor.moveToNext());

            Log.e("Responce Database",resagencyname);
            cursor.close();
        }

        close();
        return resagencyname;


    }

    public String getUserName()
    {
        String UserName="";
        open();


        /*final String MY_QUERY2 = " SELECT  " + DbHelper.LOCAL_FORM_USER_REGISTER_NAME +
                " FROM " + DbHelper.USER_REGISTER_TABLE ;
        Cursor cursor = database.rawQuery(MY_QUERY2, null);*/

        Cursor cursor2 = database.query(dbhelper.USER_REGISTER_TABLE, new String[]{dbhelper.COLUMN_ID_USER_REGISTER, dbhelper.LOCAL_FORM_USER_REGISTER_NAME}, null, null, null, null, null);


        //Cursor cursor = database.query(dbhelper.USER_REGISTER_TABLE, null, dbhelper.LOCAL_FORM_USER_REGISTER_PASSWORD + "=?", new String[]{dbhelper.LOCAL_FORM_USER_REGISTER_MOBILENU}, null, null, null);


        if (cursor2 != null && cursor2.getCount()>0)
        {
            cursor2.moveToFirst();
            do {
                UserName=cursor2.getString(cursor2.getColumnIndex(dbhelper.LOCAL_FORM_USER_REGISTER_NAME));


            }while (cursor2.moveToNext());
            Log.e("USER NAME ",UserName);
            cursor2.close();
        }

        close();
        return UserName;
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
