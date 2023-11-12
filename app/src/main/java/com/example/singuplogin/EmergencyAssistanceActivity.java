package com.example.singuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyAssistanceActivity extends AppCompatActivity {
    private Button buttonCallEmergency1,buttonCallEmergency2,buttonCallEmergency3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_assistance);

        buttonCallEmergency1 = findViewById(R.id.buttonCallEmergency1);
        buttonCallEmergency2 = findViewById(R.id.buttonCallEmergency2);
        buttonCallEmergency3 = findViewById(R.id.buttonCallEmergency3);

        buttonCallEmergency1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:197"));
                startActivity(intent);
            }
        });
        buttonCallEmergency2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:198"));
                startActivity(intent);
            }
        });
        buttonCallEmergency3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:199"));
                startActivity(intent);
            }
        });
    }
}