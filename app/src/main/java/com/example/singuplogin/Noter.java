package com.example.singuplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Noter extends AppCompatActivity {

    private EditText noteEditText;
    private TextView notesTextView;
    private Button sajelButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noter);

        noteEditText = findViewById(R.id.noteEditText);
        notesTextView = findViewById(R.id.notesTextView);
        sajelButton = findViewById(R.id.savButton);

        sajelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = noteEditText.getText().toString();
                addNoteToTextView(note);
                clearNoteEditText();
            }
        });
    }

    private void addNoteToTextView(String note) {
        String currentNotes = notesTextView.getText().toString();
        String updatedNotes = currentNotes + "\n- " + note;
        notesTextView.setText(updatedNotes);
    }

    private void clearNoteEditText() {
        noteEditText.setText("");
    }
}


