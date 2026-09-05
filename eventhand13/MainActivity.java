package com.example.eventhand13;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Spinner
        Spinner spinner = findViewById(R.id.languageSpinner);

        // Create adapter using string array
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.languages_array,
                        android.R.layout.simple_spinner_item
                );

        // Dropdown layout
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        // Set adapter
        spinner.setAdapter(adapter);

        // Handle selection
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        String selectedItem =
                                parent.getItemAtPosition(position).toString();

                        Toast.makeText(
                                MainActivity.this,
                                "Selected: " + selectedItem,
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Nothing selected
                    }
                }
        );
    }
}
