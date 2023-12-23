package com.example.singuplogin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigListener);

        // Set the initial fragment when the activity is created
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.addplaceholer, new MedicalDirectoryFragment())
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.rmed) {
                        selectedFragment = new MedicalDirectoryFragment();
                    }
                    if (item.getItemId() == R.id.addpeep) {
                        selectedFragment = new PeepsFragment();
                    }
                    if (item.getItemId() == R.id.listpeep) {
                        selectedFragment = new DisplayFragment();
                    }
                    if (item.getItemId() == R.id.remind) {
                        selectedFragment = new MedicationRemindersFragment();
                    }
                    if (item.getItemId() == R.id.emer) {
                        selectedFragment = new EmergencyAssistanceFragment();
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.addplaceholer, selectedFragment)
                                .commit();
                        return true;
                    } else {
                        return false;
                    }
                }
            };
}
