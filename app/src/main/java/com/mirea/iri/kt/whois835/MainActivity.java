package com.mirea.iri.kt.whois835;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new loginFragment())
                .commit();
    }

    public void LoginClick(View view) throws JSONException {
        EditText loginInput = findViewById(R.id.loginField);
        EditText passwdInput = findViewById(R.id.passwordField);
        var lgn = loginInput.getText().toString();
        var pwd = passwdInput.getText().toString();
        if (lgn.trim().isEmpty() || pwd.trim().isEmpty()) {
            Toast.makeText(view.getContext(), getString(R.string.emptyFields), Toast.LENGTH_LONG).show();
        } else {
            Button loginButton = findViewById(R.id.loginButton);
            loginButton.setEnabled(false);
            HashMap<String, String> map = new HashMap();
            map.put("lgn", lgn);
            map.put("pwd", pwd);
            map.put("g", getString(R.string.group));
            POSTRunnable POSTRunnable = new POSTRunnable(getString(R.string.taskAddress), map);
            Thread th = new Thread(POSTRunnable);
            th.start();
            try {
                th.join();
            } catch (Exception ex) {
                Log.e("WhoIs", ex.getMessage());
            } finally {
                var outp = POSTRunnable.getResponseBody();
                if (outp != null && !outp.trim().isEmpty()) {
                    JSONObject jsobj = new JSONObject(outp);
                    int result = jsobj.getInt("result_code");
                    switch (result) {
                        case 1:
                            DataFragment fragment = new DataFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("title", jsobj.getString("title"));
                            bundle.putString("task", jsobj.getString("task"));
                            bundle.putInt("var", jsobj.getInt("variant"));
                            fragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case -1:
                            Toast.makeText(view.getContext(), getString(R.string.errorOcurred) + jsobj.getString("error"),
                                    Toast.LENGTH_LONG).show();
                            Log.e("WhoIs", jsobj.getString("error"));
                            break;
                        default:
                            Toast.makeText(view.getContext(), getString(R.string.weirdAnswer), Toast.LENGTH_LONG).show();
                            Log.e("WhoIs", getString(R.string.weirdAnswer));
                            break;
                    }
                } else {
                    Toast.makeText(view.getContext(), getString(R.string.internetCheck), Toast.LENGTH_LONG).show();
                    Log.e("WhoIs", getString(R.string.internetCheck));
                }
            }
            loginButton.setEnabled(true);
        }
    }
    public void hopClick(View view) {
        Intent intent = new Intent(MainActivity.this, ip.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}