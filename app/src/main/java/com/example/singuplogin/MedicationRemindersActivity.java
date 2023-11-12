package com.example.singuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MedicationRemindersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MedicationAdapter medicationAdapter;
    private List<Medication> medicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_reminders);

        // Initialize the list of medications
        medicationList = new ArrayList<>();
        // Add example medications (replace with your own data)
        medicationList.add(new Medication("Aspirin", "3m", "6:55 PM"));
        medicationList.add(new Medication("Ibuprofen", "4m", "6:56 AM"));
        medicationList.add(new Medication("Paracetamol", "6m", "4:28 PM"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicationAdapter = new MedicationAdapter(medicationList);
        recyclerView.setAdapter(medicationAdapter);


        // Schedule alarms for each medication
        for (int i = 0; i < medicationList.size(); i++) {
            Medication medication = medicationList.get(i);
            scheduleAlarm(medication, i);
        }

    }

    // Schedule an alarm for the given medication
    private void scheduleAlarm(Medication medication, int requestCode) {
        Intent intent = new Intent(MedicationRemindersActivity.this, AlarmReceiver.class);
        intent.putExtra("medication_name", medication.getName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MedicationRemindersActivity.this, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

        try {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, medication.getTimeInMillis(), pendingIntent);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }

    }


    // Inner class for the RecyclerView adapter
    private class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {
        private List<Medication> medicationList;

        public MedicationAdapter(List<Medication> medicationList) {
            this.medicationList = medicationList;
        }

        @NonNull
        @Override
        public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication, parent, false);
            return new MedicationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
            Medication medication = medicationList.get(position);
            holder.textViewMedicationName.setText(medication.getName());
            holder.textViewMedicationTime.setText(medication.getTime());
        }

        @Override
        public int getItemCount() {
            return medicationList.size();
        }

        // Inner class for the ViewHolder
        public class MedicationViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewMedicationName;
            private TextView textViewMedicationTime;

            public MedicationViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewMedicationName = itemView.findViewById(R.id.textViewMedicationName);
                textViewMedicationTime = itemView.findViewById(R.id.textViewMedicationTime);
            }
        }
    }
}
