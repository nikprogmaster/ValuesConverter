package com.example.valuesconverter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    private static String ITEM_EXTRA_KEY = "item_key";
    private TextView to_tw, from_tw;
    private Spinner from, to;
    private List<String> vals;
    private SpinnerAdapter fromAdapter, toAdapter;
    private String from_str_selected, to_str_selected, convertion_result, reverConvertion_result;
    private EditText editText_from, editText_to;
    private Values value;
    TextWatcher tw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        from_tw = findViewById(R.id.textView_from);
        to_tw = findViewById(R.id.textView_to);
        editText_from = findViewById(R.id.editText_from);
        editText_to = findViewById(R.id.editText_to);
        initSpinner();


        editText_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        editText_to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //calculateRever();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    private void initSpinner (){
        from = findViewById(R.id.spinner_from);
        to = findViewById(R.id.spinner_to);
        value = (Values) getIntent().getSerializableExtra(ITEM_EXTRA_KEY);
        vals = new ArrayList<>();
        if (value.getUnits() != null){
            List<Unit> list = value.getUnits();
            for (int i=0; i<list.size(); i++) {
                vals.add(getString(list.get(i).getLabelRecourse()));
            }
        }
        fromAdapter = new SpinnerAdapter(vals);
        from.setAdapter(fromAdapter);
        toAdapter = new SpinnerAdapter(vals);
        to.setAdapter(toAdapter);
        from_str_selected = fromAdapter.getItem(0);
        to_str_selected = toAdapter.getItem(0);

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from_tw.setText(fromAdapter.getItem(i));
                from_str_selected = fromAdapter.getItem(i);
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_tw.setText(vals.get(i));
                to_str_selected = toAdapter.getItem(i);
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public static Intent getStartIntent(Context context, Values value){
        final Intent intent = new Intent(context, ConverterActivity.class);
        intent.putExtra(ITEM_EXTRA_KEY, value);
        return intent;
    }

    private void calculate(){
        if (editText_from.getText().toString().length()!=0) {
            double val = Double.valueOf(editText_from.getText().toString());
            double result = value.calculate(ConverterActivity.this, from_str_selected, to_str_selected, val);
            convertion_result = String.valueOf(result);
            editText_to.setText(convertion_result);
        }
    }

    private void calculateRever() {
        if (editText_to.getText().toString().length()!=0){
            double val = Double.valueOf(editText_from.getText().toString());
            double result = value.calculate(ConverterActivity.this, from_str_selected, to_str_selected, val);
            reverConvertion_result = String.valueOf(result);
            editText_to.setText(reverConvertion_result);
        }
    }

}
