package AppController;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.toolbox.Volley;
import com.example.chari.myapplication.DbHelper;

/**
 * Created by Chari on 8/29/2016.
 */
public class AppController extends Application
{
    public static final String TAG = AppController.class.getSimpleName();
    private com.android.volley.RequestQueue mRequestQueue;
    private static DbHelper dbHelper_ref;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        dbHelper_ref = new DbHelper(getApplicationContext());

    }

    public boolean haveNetworkConnection()
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public static synchronized DbHelper getDatabaseInstance(){ return dbHelper_ref; }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
    public com.android.volley.RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
            //mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


}
