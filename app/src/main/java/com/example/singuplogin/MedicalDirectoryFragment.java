package com.example.singuplogin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicalDirectoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalDirectoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private MedicalDirectoryAdapter MedicalDirectoryAdapter;
    private List<Contact> contactList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedicalDirectoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicalDirectoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalDirectoryFragment newInstance(String param1, String param2) {
        MedicalDirectoryFragment fragment = new MedicalDirectoryFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_directory, container, false);

        // Initialize the list of medical contacts
        contactList = new ArrayList<>();
        // Add examples of contacts (replace this logic with your own data)
        contactList.add(new Contact("Doctor John Doe", "Cardiologist", "27522770"));
        contactList.add(new Contact("Doctor Jane Smith", "Dermatologist", "28882160"));
        contactList.add(new Contact("Pharmacy ABC", "Pharmacy", "25455695"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MedicalDirectoryAdapter = new MedicalDirectoryAdapter(contactList);
        recyclerView.setAdapter(MedicalDirectoryAdapter);

        return view;
    }
    private class MedicalDirectoryAdapter extends RecyclerView.Adapter<MedicalDirectoryFragment.MedicalDirectoryAdapter.ContactViewHolder> {
        private List<Contact> contactList;

        public MedicalDirectoryAdapter(List<Contact> contactList) {
            this.contactList = contactList;
        }

        @NonNull
        @Override
        public MedicalDirectoryFragment.MedicalDirectoryAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
            return new MedicalDirectoryFragment.MedicalDirectoryAdapter.ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MedicalDirectoryFragment.MedicalDirectoryAdapter.ContactViewHolder holder, int position) {
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
                    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
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