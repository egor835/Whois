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
import android.widget.ProgressBar;

import com.mirea.iri.kt.whois835.GETRunnable;
import com.mirea.iri.kt.whois835.MapToRecycleAdapter;
import com.mirea.iri.kt.whois835.R;

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
        View parentview = getActivity().findViewById(R.id.tabsFrgmnt);

        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        ProgressBar prgrsbar = parentview.findViewById(R.id.ipProgressBar);

        prgrsbar.setMax(prgrsbar.getMax()+1);
        prgrsbar.setIndeterminate(true);

        //init recycler
        RecyclerView recyclerView = view.findViewById(R.id.recyclerApi1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //get ip
        Bundle tabsargs = getArguments();
        String ipaddr = tabsargs.getString("ip");

        //create bg thread
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        //new thread
        executor.execute(() -> {
            //sent api req
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
                        //fun hackery with jsons and maps
                        JSONObject jsobj = new JSONObject(getRunnable.getResponseBody());
                        Iterator<String> keys = jsobj.keys();
                        String lat = "";
                        while (keys.hasNext()) {
                            String key = keys.next();
                            String value = jsobj.get(key).toString();
                            if (key.contains("lat")){
                                lat = value;
                            } else if (key.contains("lon")) {
                                data.put("exact_location",lat + "," +value);
                            } else {
                                data.put(key, value);
                            }
                        }
                    } catch (Exception ex) {
                        Log.e("WhoIs", ex.getMessage());
                        data.put(getString(R.string.errorTranslateJSLabel),getString(R.string.errorTranslateJSBody));
                    }
                    MapToRecycleAdapter adapter = new MapToRecycleAdapter(data, view.getContext());
                    recyclerView.setAdapter(adapter);
                    prgrsbar.setMax(prgrsbar.getMax()-1);
                    if (prgrsbar.getMax() == 0){
                        prgrsbar.setIndeterminate(false);
                    }
                });

            }
        });
        return view;
    }
}