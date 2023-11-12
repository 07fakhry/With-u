package com.example.singuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class LoginActivity extends AppCompatActivity {
        private FirebaseAuth auth;
        private EditText LoginEmail ,LoginPassword ;
        private Button loginButton;
        private TextView signupRedirectText ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_up);

            auth = FirebaseAuth.getInstance();
            LoginEmail = findViewById(R.id.Login_email);
            LoginPassword = findViewById(R.id.Login_Password);
            loginButton= findViewById(R.id.Login_Button);
            signupRedirectText = findViewById(R.id.LoginRedirectText);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = LoginEmail.getText().toString();
                    String pass = LoginPassword.getText().toString();
                    if (!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        if (!email.isEmpty()){
                            auth.signInWithEmailAndPassword(email , pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(LoginActivity.this, "login Succssful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, "Login Failed  ", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {LoginPassword.setError("Password cannot be ematy ");
                        }   if (email.isEmpty()) {
                            LoginEmail.setError(("Email cannout  be emty "));
                        }
                    }else {LoginEmail.setError("Pmease enter valid email");
                    }
                }
            });
            signupRedirectText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, SignUpActivty.class));
                }
            });
        }
    }


