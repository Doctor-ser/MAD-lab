package com.example.localdata13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    private TextView storedDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        storedDataText = findViewById(R.id.storedDataText);

        // Get SharedPreferences
        SharedPreferences preferences =
                getSharedPreferences("MyPref", MODE_PRIVATE);

        // Read stored data
        String username =
                preferences.getString("username", "No username found");

        String email =
                preferences.getString("email", "No email found");

        String password =
                preferences.getString("password", "No password found");

        // Display data
        storedDataText.setText(
                "Stored Data\n\n" +
                        "Username: " + username + "\n\n" +
                        "Email: " + email + "\n\n" +
                        "Password: " + password
        );
    }
}
