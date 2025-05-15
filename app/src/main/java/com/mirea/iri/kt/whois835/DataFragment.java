package com.mirea.iri.kt.whois835;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.data_fragment, container, false);

        String title = "";
        String task = "hahahahahha";
        int variant = 0;

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString("title");
            task = args.getString("task");
            variant = args.getInt("var");
        }

        TextView txt = view.findViewById(R.id.taskBody);
        TextView label = view.findViewById(R.id.tabsLabel);
        label.setText(getString(R.string.taskLabel) + title + ", " + variant);
        txt.setText(task);
        txt.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}