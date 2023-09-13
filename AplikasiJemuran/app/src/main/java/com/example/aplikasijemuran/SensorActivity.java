package com.example.aplikasijemuran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SensorActivity extends AppCompatActivity {
    private Button ModeOn;
    private Button ModeOff;
    private TextView hujan;
    private TextView cahaya;
    private TextView suhu;
    private DatabaseReference sensordb;
    private Firebase refcahaya;
    private Firebase refsuhu;
    private Firebase refhujan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ModeOn = (Button) findViewById(R.id.ModeON);
        ModeOff = (Button) findViewById(R.id.ModeOFF);
        sensordb = FirebaseDatabase.getInstance().getReference();
        hujan = (TextView) findViewById(R.id.hujan);
        cahaya = (TextView) findViewById(R.id.cahaya);
        suhu = (TextView) findViewById(R.id.suhu);

        refcahaya = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/sensor/ldr");
        refsuhu = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/sensor/temperature");
        refhujan = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/sensor/rainValue");

        ModeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SensorActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin menghidupkan Mode Baca");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","NO");
                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","YES");
                        sensordb.child("ReadOnly").setValue(1);
                    }
                });
                builder.setNeutralButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","BATAL");
                    }
                });
                builder.show();
            }
        });
        ModeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SensorActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin mematikan ModeBaca");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","NO");
                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","YES");
                        sensordb.child("ReadOnly").setValue(0);
                    }
                });
                builder.setNeutralButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("info","BATAL");
                    }
                });
                builder.show();
            }
        });
        refcahaya.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String ldr = dataSnapshot.getValue(String.class);
                cahaya.setText(ldr);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        refsuhu.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String temperature = dataSnapshot.getValue(String.class);
                suhu.setText(temperature);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        refhujan.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String rainValue = dataSnapshot.getValue(String.class);
                hujan.setText(rainValue);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}