
package com.example.valuesconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ValuesAdapter valuesAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        List<Values> vals = Arrays.asList(Values.values());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                MainActivity.this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        OnMainClickListener onMainClickListener = new OnMainClickListener() {
            @Override
            public void OnMainClick(Values value) {
                final Intent intent = ConverterActivity.getStartIntent(MainActivity.this, value);
                startActivity(intent);
            }
        };
        valuesAdapter = new ValuesAdapter(onMainClickListener);
        valuesAdapter.setValues(vals);
        recyclerView.setAdapter(valuesAdapter);
    }

}
