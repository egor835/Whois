package com.mirea.iri.kt.whois835;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GETRunnable implements Runnable{

    private String address;
    private String responseBody;

    public GETRunnable (String address){
        this.address = address;
    }

    public String getResponseBody () {
        return responseBody;
    }

    @Override
    public void run() {
        if(this.address != null && !this.address.isEmpty()) {
            try {
                URL url = new URL(this.address);
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConnecton = (HttpURLConnection)connection;
                httpConnecton.setRequestMethod("GET");
                InputStreamReader isr = new InputStreamReader(httpConnecton.getInputStream()) ;
                BufferedReader br = new BufferedReader(isr);
                String currentLine;
                StringBuilder sbResponse = new StringBuilder();
                while ((currentLine = br.readLine()) != null) {
                    sbResponse.append (currentLine);
                }
                responseBody = sbResponse.toString();
            } catch (IOException ex) {
                Log.e("WhoIs", ex.getMessage());
            }
        }
    }
}

