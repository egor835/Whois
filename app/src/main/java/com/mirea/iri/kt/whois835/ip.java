package com.mirea.iri.kt.whois835;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ipFragmentContainer, new ipReqFragment())
                .commit();

    }

    public void useDevIp(View view) {
        Switch ipSwitch = findViewById(R.id.useThisIp);
        EditText ipInput = findViewById(R.id.ipTextBox);
        Button getInfoBtn = findViewById(R.id.getInfoButton);
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
}