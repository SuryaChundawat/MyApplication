package com.example.chari.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chari on 7/24/2016.
 */
public class DbHelper extends SQLiteOpenHelper
{

   //DAta base Information
    public static final String DATABASE_NAME = "demo_database";
    public static final int DATABASE_VERSION = 1;

    //table Information
    public static final String LOGIN_TABLE = "login_table";
    public static final String FORM_TABLE_FLAT = "form_geotag_details_table_flat";
    public static final String USER_REGISTER_TABLE = "user_register";
    public static final String FORM_TABLE_DETAILS = "form_details_table";
    public static final String FORM_TABLE_GEOTAG_BANGLOW_DETAILS = "form_geotag_details_table_banlglow";
    public static final String FORM_TABLE_GEOTAG_LAND_DETAILS = "form_geotag_details_table_land";
    public static final String FORM_TABLE_GEOTAG_OTHER_DETAILS = "form_geotag_details_table_other";
    public static final String LOGIN_RESPONCE_TABLE = "login_responce_table";



    public static final String COLUMN_ID_RESPONCE= "_id";
    public static final String LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS ="agency_name";

    //column for flat table
    public static final String COLUMN_ID_GEO_FLAT= "_id";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LAT ="latitude";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LONG ="longitude";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_ALTITUDE ="altitude";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_STATE ="state";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_USERNAME ="username";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_AGENCYCODE ="agencycode";




    //column for geo tag oTHER
    public static final String COLUMN_ID_GEO_OTHER= "_id";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLAT = "front_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLONG= "front_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTALTI= "front_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLAT = "front_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLONG= "front_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTALTI= "front_right_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLAT = "Back_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLONG= "Back_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTALT= "Back_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLAT= "Back_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLONG= "Back_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTALT= "Back_right_alt";
    public static final String LOCAL_FORM_DETAILS_TABLE_IMEI_NO_OTHER = "moblie_imei";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_USERNASME ="username";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_AGENCYCODE="agencycode";

       //column for geo tag lAND
    public static final String COLUMN_ID_GEO_LAND = "_id";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLAT = "front_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLONG= "front_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTALTI= "front_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLAT = "front_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLONG= "front_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTALTI= "front_right_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLAT = "Back_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLONG= "Back_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTALT= "Back_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLAT= "Back_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLONG= "Back_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTALT= "Back_right_alt";
    public static final String LOCAL_FORM_DETAILS_TABLE_IMEI_NO_LAND = "moblie_imei_land";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_USERNASME ="username";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_LAND_AGENCYCODE="agencycode";

     //column for geo tag BANGLOW
    public static final String COLUMN_ID_GEO_BANGLOW = "_id";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLAT = "front_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLONG= "front_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTALTI= "front_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLAT = "front_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLONG= "front_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTALTI= "front_right_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLAT = "Back_left_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLONG= "Back_left_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTALT= "Back_left_alt";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLAT= "Back_right_lat";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLONG= "Back_right_long";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTALT= "Back_right_alt";
    public static final String LOCAL_FORM_DETAILS_TABLE_IMEI_NO= "moblie_imei_banglow";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_USERNASME ="username";
    public static final String LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_AGENCYCODE="agencycode";


    //column id for form
    public static final String COLUMN_ID = "_id";
    public static final String LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO = "form_application_id";
    public static final String LOCAL_FORM_DETAILS_TABLE_FORM_NO = "form_no";
    public static final String LOCAL_FORM_DETAILS_TABLE_ID_PROOF = "form_id_proof";
    public static final String LOCAL_FORM_DETAILS_TABLE_ID_NO = "form_table_id_no";
    public static final String LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE = "form_product_type";
    public static final String LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE = "form_flat_details";
   /* public static final String LOCAL_FORM_DETAILS_TABLE_IS_LAND = "form_land_details";
    public static final String LOCAL_FORM_DETAILS_TABLE_IS_BANGLOW = "form_banglows_details";
    public static final String LOCAL_FORM_DETAILS_TABLE_IS_OTHER = "form_other_details";*/
    public static final String LOCAL_FORM_DETAILS_TABLE_ADDRESS1 = "form_address1_details";
    public static final String LOCAL_FORM_DETAILS_TABLE_ADDRESS2 = "form_address2_details";
    public static final String LOCAL_FORM_DETAILS_TABLE_CITY = "form_city_details";
    public static final String LOCAL_FORM_DETAILS_TABLE_DISTRICT = "form_district_details";
   /* public static final String LOCAL_FORM_DETAILS_TABLE_STATE = "form_state_details";*/
   /* public static final String LOCAL_FORM_DETAILS_TABLE_AGENTCODE = "form_agentcode_details";*/



    //Login Activity
    public static final String COLUMN_ID_LOGIN= "_id";
    public static final String LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID = "login_id";
    public static final String LOCAL_FORM_DETAILS_TABLE_SIGNUP_PASSWORD= "password";


    //User Register

    public static final String COLUMN_ID_USER_REGISTER= "_id";
    public static final String LOCAL_FORM_USER_REGISTER_NAME = "name";
    public static final String LOCAL_FORM_USER_REGISTER_PASSWORD= "password";
    public static final String LOCAL_FORM_USER_REGISTER_EMAIL= "email_id";
    public static final String LOCAL_FORM_USER_REGISTER_AGE= "age";
    public static final String LOCAL_FORM_USER_REGISTER_MOBILENU= "phone_no";
    public static final String LOCAL_FORM_USER_REGISTER_IMEINO= "imei_no";







    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //FOR CREATING TABLE QUERY






     private static String CREATE_TABLE_GEOTAG_LAND_DETAILS = "CREATE TABLE " +FORM_TABLE_GEOTAG_LAND_DETAILS
         +"(" +COLUMN_ID_GEO_LAND+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
         +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTLEFTALTI+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_FRNTRIGHTALTI+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKLEFTALT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_BACKRIGHTALT+
         " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_IMEI_NO_LAND+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_USERNASME+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_LAND_AGENCYCODE+
         " TEXT NOT NULL);";

       private static String CREATE_TABLE_GEOTAG_OTHER_DETAILS = "CREATE TABLE " +FORM_TABLE_GEOTAG_OTHER_DETAILS
         +"(" +COLUMN_ID_GEO_OTHER+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
         +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTLEFTALTI+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_FRNTRIGHTALTI+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKLEFTALT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLAT+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTLONG+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_BACKRIGHTALT+
         " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_IMEI_NO_OTHER+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_USERNASME+
         " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__OTHER_AGENCYCODE+
         " TEXT NOT NULL);";



      private static String CREATE_TABLE_GEOTAG_BANGLOW_DETAILS = "CREATE TABLE " +FORM_TABLE_GEOTAG_BANGLOW_DETAILS
            +"(" +COLUMN_ID_GEO_BANGLOW+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLAT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTLONG+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTLEFTALTI+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLAT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTLONG+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_FRNTRIGHTALTI+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLAT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTLONG+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKLEFTALT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLAT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTLONG+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE__BANGLOW_BACKRIGHTALT+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_IMEI_NO+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_USERNASME+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_BANGLOW_AGENCYCODE+
            " TEXT NOT NULL);";


     private static String CREATE_TABLE_FORM_DETAILS = "CREATE TABLE " +FORM_TABLE_DETAILS
             +"(" +COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_DETAILS_TABLE_APPLICATION_NO+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_FORM_NO+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_ID_PROOF+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_ID_NO+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_PRODUCT_TYPE+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_IS_PRTYPE+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_ADDRESS1+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_ADDRESS2+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_CITY+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_DISTRICT+
            " TEXT NOT NULL);";



    //For Flat Table
    private static String CREATE_TABLE_GEOTAG_FLAT_DETAILS = "CREATE TABLE " +FORM_TABLE_FLAT
            +"(" +COLUMN_ID_GEO_FLAT+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LAT+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_LONG+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_ALTITUDE+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_STATE+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_USERNAME+
            " TEXT NOT NULL , " +LOCAL_FORM_GEO_DETAILS_TABLE_FLAT_AGENCYCODE+
            " TEXT NOT NULL);";


    //For Login Table

    private static String CREATE_TABLE_LOGIN = "CREATE TABLE " +LOGIN_TABLE
            +"(" +COLUMN_ID_LOGIN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_DETAILS_TABLE_LOGIN_SIGNUP_ID+
            " TEXT NOT NULL , " +LOCAL_FORM_DETAILS_TABLE_SIGNUP_PASSWORD+
            " TEXT NOT NULL);";


    //For Login Responce
    private static String CREATE_TABLE_LOGIN_RESPONCE = "CREATE TABLE " +LOGIN_RESPONCE_TABLE
            +"(" +COLUMN_ID_RESPONCE+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_LOGIN_RESOPNCE_USERNAMEPASS+
            " TEXT NOT NULL);";

    //For Login Table

    private static String CREATE_TABLE_USER_REGISTER = "CREATE TABLE " +USER_REGISTER_TABLE
            +"(" +COLUMN_ID_USER_REGISTER+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOCAL_FORM_USER_REGISTER_NAME+
            " TEXT NOT NULL , " +LOCAL_FORM_USER_REGISTER_PASSWORD+
            " TEXT NOT NULL , " +LOCAL_FORM_USER_REGISTER_EMAIL+
            " TEXT NOT NULL , " +LOCAL_FORM_USER_REGISTER_AGE+
            " TEXT NOT NULL , " +LOCAL_FORM_USER_REGISTER_MOBILENU+
            " TEXT NOT NULL , " +LOCAL_FORM_USER_REGISTER_IMEINO+
            " TEXT NOT NULL);";



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_GEOTAG_FLAT_DETAILS);
        db.execSQL(CREATE_TABLE_USER_REGISTER);
        db.execSQL(CREATE_TABLE_FORM_DETAILS);
        db.execSQL(CREATE_TABLE_LOGIN_RESPONCE);
        db.execSQL(CREATE_TABLE_GEOTAG_BANGLOW_DETAILS );
        db.execSQL(CREATE_TABLE_GEOTAG_LAND_DETAILS );
        db.execSQL(CREATE_TABLE_GEOTAG_OTHER_DETAILS );
        Log.e("DataBase table","Create Successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        if (newVersion > oldVersion)
        {
            String CREATE_TABLE_LOGIN = "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_LOGIN);

            String CREATE_TABLE_FORM_DETAILS = "DROP TABLE IF EXISTS form_no";
            db.execSQL(CREATE_TABLE_FORM_DETAILS);


            String CREATE_TABLE_GEOTAG_BANGLOW_DETAILS = "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_GEOTAG_BANGLOW_DETAILS);

            String CREATE_TABLE_GEOTAG_LAND_DETAILS = "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_GEOTAG_LAND_DETAILS);

            String CREATE_TABLE_GEOTAG_OTHER_DETAILS = "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_GEOTAG_OTHER_DETAILS);

            String CREATE_TABLE_USER_REGISTER = "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_GEOTAG_OTHER_DETAILS);

            String CREATE_TABLE_LOGIN_RESPONCE= "DROP TABLE IF EXISTS ";
            db.execSQL(CREATE_TABLE_GEOTAG_OTHER_DETAILS);


            onCreate(db);
        }

    }


}
