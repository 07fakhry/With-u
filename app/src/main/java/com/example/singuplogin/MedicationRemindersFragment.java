package com.example.singuplogin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationRemindersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicationRemindersFragment extends Fragment {
    private RecyclerView recyclerView;
    private MedicationAdapter medicationAdapter;
    private List<Medication> medicationList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedicationRemindersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicationRemindersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicationRemindersFragment newInstance(String param1, String param2) {
        MedicationRemindersFragment fragment = new MedicationRemindersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication_reminders, container, false);

        // Initialize the list of medications
        medicationList = new ArrayList<>();
        // Add example medications (replace with your own data)
        medicationList.add(new Medication("Aspirin", "3m", "6:55 PM"));
        medicationList.add(new Medication("Ibuprofen", "4m", "6:56 AM"));
        medicationList.add(new Medication("Paracetamol", "6m", "4:28 PM"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        medicationAdapter = new MedicationAdapter(medicationList);
        recyclerView.setAdapter(medicationAdapter);

        // Schedule alarms for each medication
        for (int i = 0; i < medicationList.size(); i++) {
            Medication medication = medicationList.get(i);
            scheduleAlarm(medication, i);
        }

        return view;
    }

    // Schedule an alarm for the given medication
    private void scheduleAlarm(Medication medication, int requestCode) {
        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        intent.putExtra("medication_name", medication.getName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

        try {
            AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, medication.getTimeInMillis(), pendingIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication, parent, false);
            return new MedicationViewHolder(itemView);
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