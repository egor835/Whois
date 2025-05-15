package com.mirea.iri.kt.whois835.ApiFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirea.iri.kt.whois835.GETRunnable;
import com.mirea.iri.kt.whois835.POSTRunnable;
import com.mirea.iri.kt.whois835.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class api1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api1, container, false);
        TextView txt = view.findViewById(R.id.textView111);
        Bundle tabsargs = getArguments();
        String ipaddr = tabsargs.getString("ip");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        //new thread
        executor.execute(() -> {
            GETRunnable getRunnable = new GETRunnable("http://ip-api.com/json/" + ipaddr + "?fields=66846719");
            Thread th = new Thread(getRunnable);
            th.start();
            try {
                th.join();
            } catch (Exception ex) {
                Log.e("WhoIs", ex.getMessage());
            } finally {
                //back to main thread
                handler.post(() -> {
                    txt.setText(getRunnable.getResponseBody());
                });

            }
        });
        return view;
    }
}