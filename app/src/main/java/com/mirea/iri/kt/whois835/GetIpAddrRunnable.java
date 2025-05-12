package com.mirea.iri.kt.whois835;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;

public class GetIpAddrRunnable implements Runnable{
    private String ipee;
    public GetIpAddrRunnable(){}
    public String getAddress () {
        return ipee;
    }

    @Override
    public void run() {
        ipee = "";
        try
        {
            URL url_name = new URL("http://checkip.amazonaws.com/");
            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));
            ipee = sc.readLine().trim();
            Log.i("WhoIs", "Got ip: "+ipee);
        }
        catch (Exception ex)
        {
            Log.e("WhoIs", ex.getMessage());
        }

    }

}
