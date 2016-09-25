package com.example.chari.myapplication;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Chari on 8/4/2016.
 */
public class CommonClass  extends URLConnection
{
    protected CommonClass(URL url)
    {
        super(url);

    }



    @Override
    public void connect() throws IOException
    {



    }
}
