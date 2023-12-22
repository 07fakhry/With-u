package com.example.singuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivty extends AppCompatActivity {

private FirebaseAuth auth;
private EditText signupEmail, signupPassword;
private Button signupButton;
private TextView loginRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activty);

        auth = FirebaseAuth.getInstance();
        signupEmail=findViewById(R.id.Signup_email);
        signupPassword = findViewById(R.id.Signup_Password);
        signupButton =findViewById(R.id.Sigup_Button);
        loginRedirectText = findViewById(R.id.LoginRedirectText);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                if (user.isEmpty()) {
                    signupEmail.setError(("Password cannot be empty "));
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivty.this, "SignUp Successful (sahhhyt ay odekhl dar dark)", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivty.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivty.this, "SignUp Faild (fama haja ghalta zyed t2ked)" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivty.this , LoginActivity.class));
            }
        });
    }
}