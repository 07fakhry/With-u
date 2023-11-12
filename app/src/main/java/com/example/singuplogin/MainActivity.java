package com.example.singuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Button buttonMedicalDirectory,buttonMedicalReminder,buttonPeeps,buttonCallPeeps,buttonEmer, buttonsajel, buttonjeux;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMedicalDirectory =(Button) findViewById(R.id.buttonMedicalDirectory);
        buttonMedicalReminder =(Button) findViewById(R.id.buttonMedicalReminder);
        buttonPeeps =(Button) findViewById(R.id.buttonPeepx);
        buttonCallPeeps =(Button) findViewById(R.id.buttoncallPeepx);
        buttonEmer =(Button) findViewById(R.id.buttonEmergency);
        buttonsajel =(Button)findViewById(R.id.buttonsajel);
        buttonjeux =(Button)findViewById(R.id.jeux) ;
        buttonMedicalDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité du répertoire médical
                Intent intent = new Intent(MainActivity.this, MedicalDirectoryActivity.class);
                startActivity(intent);
            }
        });
        buttonMedicalReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité du Medical Reminder
                Intent intent = new Intent(MainActivity.this, MedicationRemindersActivity.class);
                startActivity(intent);
            }
        });
        buttonPeeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité du Peeps
                Intent intent = new Intent(MainActivity.this, PeepsActivity.class);
                startActivity(intent);
            }
        });
        buttonCallPeeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité du Call Peeps
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
        buttonEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité du Emergency
                Intent intent = new Intent(MainActivity.this, EmergencyAssistanceActivity.class);
                startActivity(intent);
            }
        });
        buttonsajel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Noter.class);
                startActivity(intent);

            }
        });


}}


