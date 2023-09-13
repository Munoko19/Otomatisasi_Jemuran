package com.example.aplikasijemuran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtomatisActivity extends AppCompatActivity {
    private TextView Kondisi;
    private TextView Pos;
    private Button JemuranON;
    private Button JemuranOFF;
    private Firebase refKondisi;
    private Firebase refPosisi;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otomatis);
        JemuranON = (Button) findViewById(R.id.JemuranON);
        JemuranOFF = (Button) findViewById(R.id.JemuranOFF);
        mdatabase = FirebaseDatabase.getInstance().getReference();

        Kondisi = (TextView) findViewById(R.id.kondisi);
        Pos = (TextView) findViewById(R.id.posisi);

        refKondisi = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/status/hujan");
        refPosisi = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/Posisi");

        refKondisi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hujan = dataSnapshot.getValue(String.class);
                Kondisi.setText(hujan);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        refPosisi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Posisi = dataSnapshot.getValue(String.class);
                Pos.setText(Posisi);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        JemuranON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OtomatisActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin menghidupkan Mode jemuran Otomatis");
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
                        mdatabase.child("Mode").setValue(1);
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
        JemuranOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OtomatisActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin Mematikan Mode jemuran Otomatis");
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
                        mdatabase.child("Mode").setValue(0);
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

    }
}