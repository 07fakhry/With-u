package com.example.singuplogin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CPeepsAdapter extends ArrayAdapter<CPeeps>{
    public CPeepsAdapter(Context context, List<CPeeps> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CPeeps contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView phoneTextView = convertView.findViewById(R.id.phoneTextView);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhoneNumber());

        return convertView;
    }
}