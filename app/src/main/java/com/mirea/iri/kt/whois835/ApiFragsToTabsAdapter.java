package com.mirea.iri.kt.whois835;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mirea.iri.kt.whois835.ApiFragments.api1;
import com.mirea.iri.kt.whois835.ApiFragments.api2;
import com.mirea.iri.kt.whois835.ApiFragments.api3;
import com.mirea.iri.kt.whois835.ApiFragments.api4;

public class ApiFragsToTabsAdapter extends FragmentStateAdapter {

    private String ipaddress;

    public ApiFragsToTabsAdapter(TabsFragment fm, String ipaddress) {
        super(fm);
        this.ipaddress = ipaddress;
    }

    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("ip", ipaddress);
        switch (position){
            case 1: {
                api2 api = new api2();
                api.setArguments(bundle);
                return api;
            }
            case 2: {
                api3 api = new api3();
                api.setArguments(bundle);
                return api;
            }
            case 3: {
                api4 api = new api4();
                api.setArguments(bundle);
                return api;
            }
            default: {
                api1 api = new api1();
                api.setArguments(bundle);
                return api;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
