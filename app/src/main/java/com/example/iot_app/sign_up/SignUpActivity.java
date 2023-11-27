package com.example.iot_app.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_app.R;
import com.example.iot_app.sign_in.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText signupUsername, signupEmail, signupPassword, signup_phone;
    TextView loginRedirectText;
    Button signupButton, signinButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupEmail = findViewById(R.id.editEmail);
        signupUsername = findViewById(R.id.editUser);
        signupPassword = findViewById(R.id.editPassword);
//        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.btnSignup);
        signinButton = findViewById(R.id.btnlogin);
        signup_phone = findViewById(R.id.editPhone);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String phoneNumber = signup_phone.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass( username, email, phoneNumber, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(SignUpActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}