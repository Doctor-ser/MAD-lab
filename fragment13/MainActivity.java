package com.example.fragment13;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1, button2;
    private Fragment fragment1, fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Find the views from the layout file.
        button1 = findViewById(R.id.button_fragment1);
        button2 = findViewById(R.id.button_fragment2);
// Initialize the fragment objects.
        fragment1 = new FragmentOne();
        fragment2 = new FragmentTwo();
// Set up click listeners for the buttons.
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
// Initially load the first fragment into the container.
        loadFragment(fragment1);
    }
    @Override
    public void onClick(View v) {
// Use a switch statement to handle button clicks.
        int viewId = v.getId();
        if (viewId == R.id.button_fragment1) {
            loadFragment(fragment1); // Load the first fragment.
        } else if (viewId == R.id.button_fragment2) {
            loadFragment(fragment2); // Load the second fragment.
        }
    }
    // A helper method to handle the transaction of loading a fragment.
    private void loadFragment(Fragment fragment) {
// Get the FragmentManager to manage fragment transactions.
        FragmentManager fragmentManager = getSupportFragmentManager();
// Start a transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
// Replace the current fragment in the container with the new fragment.
        fragmentTransaction.replace(R.id.fragment_container, fragment);
// Commit the transaction to apply the changes.
        fragmentTransaction.commit();
    }
}
