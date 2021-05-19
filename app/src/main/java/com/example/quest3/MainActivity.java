package com.example.quest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            button = findViewById(R.id.buttongame);
            button.setOnClickListener(view -> {
                final Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            });
        } catch (Exception e) {
            Log.e(e.toString(), "Error!!!");
        }
    }
}