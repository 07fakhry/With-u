package com.example.singuplogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyAssistanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyAssistanceFragment extends Fragment {
    private Button buttonCallEmergency1,buttonCallEmergency2,buttonCallEmergency3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmergencyAssistanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmergencyAssistanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergencyAssistanceFragment newInstance(String param1, String param2) {
        EmergencyAssistanceFragment fragment = new EmergencyAssistanceFragment();
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
        View view = inflater.inflate(R.layout.fragment_emergency_assistance, container, false);

        buttonCallEmergency1 = view.findViewById(R.id.buttonCallEmergency1);
        buttonCallEmergency2 = view.findViewById(R.id.buttonCallEmergency2);
        buttonCallEmergency3 = view.findViewById(R.id.buttonCallEmergency3);

        buttonCallEmergency1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialEmergencyNumber("197");
            }
        });

        buttonCallEmergency2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialEmergencyNumber("198");
            }
        });

        buttonCallEmergency3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialEmergencyNumber("199");
            }
        });

        return view;
    }

    private void dialEmergencyNumber(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
    }
}