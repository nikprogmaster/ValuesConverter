package com.example.valuesconverter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    List<String> units;

    SpinnerAdapter(List<String> units){
        this.units = units;
    }

    public void setUnits (List<String> units){
        this.units = units;
    }

    @Override
    public int getCount() {
        return units == null ? 0 : units.size();
    }

    @Override
    public String getItem(int i) {
        return units.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);
            view.setTag(new SpinnerHolder(view));
        }
        SpinnerHolder spinnerHolder = (SpinnerHolder) view.getTag();
        String unit = units.get(i);
        spinnerHolder.unitName.setText(unit);
        return view;
    }

    private static class SpinnerHolder{
        private final TextView unitName;

        private SpinnerHolder(View root){
            unitName = root.findViewById(android.R.id.text1);
        }
    }
}
