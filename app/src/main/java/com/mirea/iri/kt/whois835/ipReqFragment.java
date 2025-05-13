package com.mirea.iri.kt.whois835;

import android.net.InetAddresses;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ipReqFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ipreq_fragment, container, false);
        EditText ipInput = view.findViewById(R.id.ipTextBox);
        Button getInfoBtn = view.findViewById(R.id.getInfoButton);
        TextWatcher watcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                var str = ipInput.getText().toString();

                if (!Patterns.IP_ADDRESS.matcher(str).matches() && !str.isEmpty()) {
                    ipInput.setError(getString(R.string.notAValidIpErr));
                    getInfoBtn.setEnabled(false);
                } else {
                    ipInput.setError(null);
                    getInfoBtn.setEnabled(true);
                }

                if (str.isEmpty()) {
                    ipInput.setError(null);
                    getInfoBtn.setEnabled(false);}
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        ipInput.addTextChangedListener(watcher);
        return view;
    }
}