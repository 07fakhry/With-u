package com.example.singuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.provider.Settings;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalDirectoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MedicalDirectoryAdapter medicalDirectoryAdapter;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_directory);

        // Initialisez la liste des contacts médicaux
        contactList = new ArrayList<>();
        // Ajoutez des exemples de contacts (vous pouvez remplacer cette logique par vos propres données)
        contactList.add(new Contact("Docteur John Doe", "Cardiologue", "27522770"));
        contactList.add(new Contact("Docteur Jane Smith", "Dermatologue", "28882160"));
        contactList.add(new Contact("Pharmacie ABC", "Pharmacie", "25455695"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicalDirectoryAdapter = new MedicalDirectoryAdapter(contactList);
        recyclerView.setAdapter(medicalDirectoryAdapter);
    }

    // Classe interne pour l'adaptateur du RecyclerView
    private class MedicalDirectoryAdapter extends RecyclerView.Adapter<MedicalDirectoryAdapter.ContactViewHolder> {
        private List<Contact> contactList;

        public MedicalDirectoryAdapter(List<Contact> contactList) {
            this.contactList = contactList;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact contact = contactList.get(position);
            holder.textViewContactName.setText(contact.getName());
            holder.textViewContactSpecialty.setText(contact.getSpecialty());
            holder.textViewContactNumber.setText(contact.getNumber());

            holder.textViewContactNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = contact.getNumber();
                    // Make a phone call
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" +phoneNumber));
                    if (ActivityCompat.checkSelfPermission(MedicalDirectoryActivity.this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        // Classe interne pour le ViewHolder
        public class ContactViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewContactName;
            private TextView textViewContactSpecialty;
            private TextView textViewContactNumber;

            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewContactName = itemView.findViewById(R.id.textViewContactName);
                textViewContactSpecialty = itemView.findViewById(R.id.textViewContactSpecialty);
                textViewContactNumber = itemView.findViewById(R.id.textViewContactNumber);
            }
        }
    }
}