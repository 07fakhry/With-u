package com.example.singuplogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ListView contactsListView;
    private List<CPeeps> contactList;
    private CPeepsAdapter contactsAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        contactsListView = findViewById(R.id.contactsListView);
        contactList = new ArrayList<>();
        contactsAdapter = new CPeepsAdapter(this, contactList);
        contactsListView.setAdapter(contactsAdapter);
        dbHelper = new DatabaseHelper(this);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = contactList.get(position).getPhoneNumber();
                makePhoneCall(phoneNumber);
            }
        });

        loadContacts();
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
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}