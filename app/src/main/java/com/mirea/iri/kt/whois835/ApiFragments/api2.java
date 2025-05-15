package com.mirea.iri.kt.whois835.ApiFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mirea.iri.kt.whois835.R;

public class api2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api2, container, false);

        View parentview = getActivity().findViewById(R.id.tabsFrgmnt);

        ProgressBar prgrsbar = parentview.findViewById(R.id.ipProgressBar);

        prgrsbar.setIndeterminate(true);
        return view;
    }
}