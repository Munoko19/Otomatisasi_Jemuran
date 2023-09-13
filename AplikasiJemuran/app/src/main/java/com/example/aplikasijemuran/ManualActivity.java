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

public class ManualActivity extends AppCompatActivity {
    private Button JemuranMasuk;
    private Button JemuranKeluar;
    private Button JemuranOFF1;
    private Button Berhenti;
    private TextView Posisi1;
    private Firebase refPosisi1;
    private DatabaseReference manualdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        JemuranMasuk = (Button) findViewById(R.id.JemuranMasuk);
        JemuranKeluar = (Button) findViewById(R.id.JemuranKeluar);
        JemuranOFF1 = (Button) findViewById(R.id.JemuranOFF1);
        Berhenti = (Button) findViewById(R.id.Berhenti);
        manualdb = FirebaseDatabase.getInstance().getReference();

        Posisi1 = (TextView) findViewById(R.id.posisi1);
        refPosisi1 = new Firebase("https://jemuranpintar-e3ec0-default-rtdb.asia-southeast1.firebasedatabase.app/Posisi");

        refPosisi1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Posisi = dataSnapshot.getValue(String.class);
                Posisi1.setText(Posisi);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        JemuranOFF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManualActivity.this);
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
                        manualdb.child("Mode").setValue(0);
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
        JemuranMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManualActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin memasukkan jemuran");
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
                        manualdb.child("Jemuran").setValue(0);
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
        JemuranKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManualActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin mengeluarkan jemuran");
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
                        manualdb.child("Jemuran").setValue(1);
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
        Berhenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManualActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("apakah anda yakin ingin mematikan gerak Jemuran");
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
                        manualdb.child("Jemuran").setValue(5);
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