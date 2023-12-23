package com.example.singuplogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeepsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeepsFragment extends Fragment {
    private EditText nameEditText, phoneEditText;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    private static final int CALL_PERMISSION_REQUEST_CODE = 123;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PeepsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeepsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PeepsFragment newInstance(String param1, String param2) {
        PeepsFragment fragment = new PeepsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peeps, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        saveButton = view.findViewById(R.id.saveButton);
        dbHelper = new DatabaseHelper(requireContext());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phoneNumber = phoneEditText.getText().toString();

                // Save name and phone number in SQLite database
                saveContact(name, phoneNumber);
            }
        });

        return view;
    }
    private void saveContact(String name, String phoneNumber) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ContactEntry.COLUMN_NAME, name);
        values.put(DatabaseContract.ContactEntry.COLUMN_PHONE, phoneNumber);

        long newRowId = db.insert(DatabaseContract.ContactEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(requireContext(), "Contact saved successfully!", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
            phoneEditText.setText("");
        } else {
            Toast.makeText(requireContext(), "Failed to save contact.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void callPhoneNumber(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
        } else {
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber(phoneEditText.getText().toString());
            } else {
                Toast.makeText(requireContext(), "Call permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}