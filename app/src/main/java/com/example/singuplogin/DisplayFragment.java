package com.example.singuplogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {
    private ListView contactsListView;
    private List<CPeeps> contactList;
    private CPeepsAdapter contactsAdapter;
    private DatabaseHelper dbHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(String param1, String param2) {
        DisplayFragment fragment = new DisplayFragment();
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
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        contactsListView = view.findViewById(R.id.contactsListView);
        contactList = new ArrayList<>();
        contactsAdapter = new CPeepsAdapter(requireContext(), contactList);
        contactsListView.setAdapter(contactsAdapter);
        dbHelper = new DatabaseHelper(requireContext());

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = contactList.get(position).getPhoneNumber();
                makePhoneCall(phoneNumber);
            }
        });

        loadContacts();

        return view;
    }
    private void loadContacts() {
        contactList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.ContactEntry._ID,
                DatabaseContract.ContactEntry.COLUMN_NAME,
                DatabaseContract.ContactEntry.COLUMN_PHONE
        };

        Cursor cursor = db.query(
                DatabaseContract.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ContactEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContactEntry.COLUMN_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ContactEntry.COLUMN_PHONE));
            CPeeps cpeeps = new CPeeps(id, name, phoneNumber);
            contactList.add(cpeeps);
        }

        cursor.close();
        db.close();

        contactsAdapter.notifyDataSetChanged();
    }

    private void makePhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}