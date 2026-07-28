package com.example.activityplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button onCreateButton, onStartButton, onPauseButton,
            onStopButton, onRestartButton, onDestroyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("Activity Created");

        onCreateButton = findViewById(R.id.onCreateButton);
        onStartButton = findViewById(R.id.onStartButton);
        onPauseButton = findViewById(R.id.onPauseButton);
        onStopButton = findViewById(R.id.onStopButton);
        onRestartButton = findViewById(R.id.onRestartButton);
        onDestroyButton = findViewById(R.id.onDestroyButton);

        onCreateButton.setOnClickListener(v ->
                showToast("onCreate() is called when the Activity is created."));

        onStartButton.setOnClickListener(v ->
                showToast("onStart() is called when the Activity becomes visible."));

        onPauseButton.setOnClickListener(v ->
                showToast("onPause() is called when the Activity loses focus."));

        onStopButton.setOnClickListener(v ->
                showToast("onStop() is called when the Activity is no longer visible."));

        onRestartButton.setOnClickListener(v ->
                showToast("onRestart() is called before restarting the Activity."));

        onDestroyButton.setOnClickListener(v ->
                showToast("onDestroy() is called before the Activity is destroyed."));
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("Activity Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("Activity Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("Activity Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("Activity Stopped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("Activity Restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("Activity Destroyed");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
