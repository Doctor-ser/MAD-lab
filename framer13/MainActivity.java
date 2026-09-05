package com.example.framer13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView i1;
    ImageView i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1 = findViewById(R.id.img1);
        i2 = findViewById(R.id.img2);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i1.setVisibility(View.GONE);
                i2.setVisibility(View.VISIBLE);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i2.setVisibility(View.GONE);
                i1.setVisibility(View.VISIBLE);
            }
        });
    }
}