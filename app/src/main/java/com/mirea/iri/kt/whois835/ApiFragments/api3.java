package com.mirea.iri.kt.whois835.ApiFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mirea.iri.kt.whois835.R;

public class api3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentview = getActivity().findViewById(R.id.tabsFrgmnt);

        ProgressBar prgrsbar = parentview.findViewById(R.id.ipProgressBar);

        prgrsbar.setMax(prgrsbar.getMax()+5);
        prgrsbar.setIndeterminate(false);
        return inflater.inflate(R.layout.fragment_api3, container, false);
    }
}