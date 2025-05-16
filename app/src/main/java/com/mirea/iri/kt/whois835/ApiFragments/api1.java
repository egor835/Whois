package com.mirea.iri.kt.whois835.ApiFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirea.iri.kt.whois835.GETRunnable;
import com.mirea.iri.kt.whois835.MapToRecycleAdapter;
import com.mirea.iri.kt.whois835.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class api1 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api1, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle tabsargs = getArguments();
        String ipaddr = tabsargs.getString("ip");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        LinkedHashMap<String, String> data = new LinkedHashMap<>();

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
                    try {
                        JSONObject jsobj = new JSONObject(getRunnable.getResponseBody());
                        Iterator<String> keys = jsobj.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            String value = jsobj.get(key).toString();
                            data.put(key, value);
                        }
                        MapToRecycleAdapter adapter = new MapToRecycleAdapter(data);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        //ERROR PLzZ
                        throw new RuntimeException(e);
                    }

                });

            }
        });
        return view;
    }
}