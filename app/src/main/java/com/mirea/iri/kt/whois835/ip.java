package com.mirea.iri.kt.whois835;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class ip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ipFragmentContainer, new ipReqFragment())
                .commit();
    }

    public void IpCheckClick(View view) {
        EditText ipInput = findViewById(R.id.ipTextBox);
        var ipaddr = ipInput.getText().toString();
        TabsFragment tabsFragment = new TabsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ip", ipaddr);
        tabsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ipFragmentContainer, tabsFragment)
                .commit();
    }
}