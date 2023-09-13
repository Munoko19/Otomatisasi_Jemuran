package com.example.aplikasijemuran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton Otomatis;
    private ImageButton Manual;
    private ImageButton Sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Otomatis = (ImageButton) findViewById(R.id.Otomatis);
        Manual = (ImageButton) findViewById(R.id.Manual);
        Sensor = (ImageButton) findViewById(R.id.Sensor);

        Otomatis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOtomatis();
            }
        });

        Manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openManual();
            }
        });
        Sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSensor();
            }
        });
    }
    public void openOtomatis(){
        Intent intent = new Intent(this, OtomatisActivity.class);
        startActivity(intent);
    }
    public void openManual(){
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
    }
    public void openSensor(){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }
}