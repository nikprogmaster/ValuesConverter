package com.example.valuesconverter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ViewHolder> {

    private OnMainClickListener onMainClickListener;
    private List<Values> values;

    ValuesAdapter(OnMainClickListener onMainClickListener){
        this.onMainClickListener = onMainClickListener;
    }

    public void setValues (List<Values> values){
        this.values = values;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Values value = values.get(position);
        holder.value.setText(value.getTitleStringResourceId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMainClickListener.OnMainClick(value);
            }
        });
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
        }
    }
}
