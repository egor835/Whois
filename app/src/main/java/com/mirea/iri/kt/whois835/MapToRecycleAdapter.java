package com.mirea.iri.kt.whois835;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;

public class MapToRecycleAdapter extends RecyclerView.Adapter<MapToRecycleAdapter.ViewHolder> {

    private final LinkedHashMap<String, String> map;
    private Context context;

    public MapToRecycleAdapter(LinkedHashMap<String, String> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinkedHashMap.Entry<String, String> entry = (LinkedHashMap.Entry<String, String>) map.entrySet().toArray()[position];
        holder.keyText.setText(entry.getKey());
        holder.valueText.setText(entry.getValue());
        if (entry.getKey().trim().equals("exact_location")) {
            holder.valueText.setClickable(true);
            holder.valueText.setFocusable(true);
            holder.valueText.setTextColor(Color.parseColor("#0000ff"));
            View.OnClickListener goToMaps = new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:"+entry.getValue()));
                    try {
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Log.e("Whois", e.toString());
                        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            };
            holder.valueText.setOnClickListener(goToMaps);
        } else {
            //goofy aah patch
            holder.valueText.setClickable(false);
            holder.valueText.setFocusable(false);
            holder.valueText.setTextColor(Color.parseColor("#000000"));
            holder.valueText.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView keyText;
        public final TextView valueText;

        public ViewHolder(View view) {
            super(view);
            keyText = view.findViewById(R.id.itemLabel);
            valueText = view.findViewById(R.id.itemValue);
        }
    }
}