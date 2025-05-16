package com.mirea.iri.kt.whois835;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;

public class MapToRecycleAdapter extends RecyclerView.Adapter<MapToRecycleAdapter.ViewHolder> {

    private final LinkedHashMap<String, String> map;

    public MapToRecycleAdapter(LinkedHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinkedHashMap.Entry<String, String> entry = (LinkedHashMap.Entry<String, String>) map.entrySet().toArray()[position];
        holder.keyText.setText(entry.getKey());
        holder.valueText.setText(entry.getValue());
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
            keyText = view.findViewById(android.R.id.text1);
            valueText = view.findViewById(android.R.id.text2);
        }
    }
}