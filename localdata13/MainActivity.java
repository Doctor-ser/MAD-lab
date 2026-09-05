package com.example.localdata13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usrname, email, passwd;
    private Button regbtn, viewDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrname = findViewById(R.id.usernameText);
        email = findViewById(R.id.emailText);
        passwd = findViewById(R.id.passwordText);
        regbtn = findViewById(R.id.registerButton);
        viewDataButton = findViewById(R.id.viewDataButton);

        // Register button
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = usrname.getText().toString().trim();
                String em = email.getText().toString().trim();
                String pwd = passwd.getText().toString();

                if (uname.isEmpty()) {
                    usrname.setError("Enter username");
                    usrname.requestFocus();
                    return;
                }

                if (em.isEmpty()) {
                    email.setError("Enter email");
                    email.requestFocus();
                    return;
                }

                if (pwd.isEmpty()) {
                    passwd.setError("Enter password");
                    passwd.requestFocus();
                    return;
                }

                // Save data
                SharedPreferences preferences =
                        getSharedPreferences("MyPref", MODE_PRIVATE);

                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putString("username", uname);
                editor.putString("email", em);
                editor.putString("password", pwd);

                editor.apply();

                Toast.makeText(
                        MainActivity.this,
                        "Registration successful",
                        Toast.LENGTH_SHORT
                ).show();

                usrname.setText("");
                email.setText("");
                passwd.setText("");
            }
        });

        // View Data button
        viewDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =
                        new Intent(MainActivity.this, DataActivity.class);

                startActivity(intent);
            }
        });
    }
}
