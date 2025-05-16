package com.mirea.iri.kt.whois835;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

public class                  ipReqFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ipreq_fragment, container, false);
        EditText ipInput = view.findViewById(R.id.ipTextBox);
        Button getInfoBtn = view.findViewById(R.id.getInfoButton);
        Switch ipSwitch = view.findViewById(R.id.useThisIp);
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
        View.OnClickListener ipswitch = new View.OnClickListener() {
            public void onClick(View v) {
                if (ipSwitch.isChecked()) {
                    GetIpAddrRunnable getip = new GetIpAddrRunnable();
                    Thread th = new Thread(getip);
                    th.start();
                    try {
                        th.join();
                    } catch (Exception ex) {
                        Log.e("WhoIs", ex.getMessage());
                    } finally {
                        var outp = getip.getAddress();
                        if (outp.trim().isEmpty()){
                            ipSwitch.setChecked(false);
                            Snackbar.make(view, R.string.cannotReadIp, Snackbar.LENGTH_LONG).show();
                        } else {
                            ipInput.setText(outp);
                            ipInput.setEnabled(false);
                            ipInput.setError(null);
                        }
                    }
                } else {
                    ipInput.setText("");
                    ipInput.setEnabled(true);
                }
            }
        };
        ipSwitch.setOnClickListener(ipswitch);
        ipInput.addTextChangedListener(watcher);
        return view;
    }
}