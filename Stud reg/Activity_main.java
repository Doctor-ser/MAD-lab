package com.example.studentreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty()) {
                etUsername.setError("Enter Username");
                return;
            }

            if (email.isEmpty()) {
                etEmail.setError("Enter Email");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Invalid Email");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter Password");
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                return;
            }

            Toast.makeText(MainActivity.this,
                    "Login Successful",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
