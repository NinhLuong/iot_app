package com.example.iot_app.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
//                database = FirebaseDatabase.getInstance();
//                reference = database.getReference("users");
                String phoneNumber = signup_phone.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                Log.d("password", password);
                // Check if the inputs are valid
                if (!isValidEmail(email)) {
                    signupEmail.setError("Invalid email!");
                    return;
                }

                if (!isValidPassword(password)) {
                    signupPassword.setError("Invalid password! Password must have at least 8 characters including lowercase letters, uppercase letters, special characters and numbers.");
                    return;
                }

                if (!isValidPhoneNumber(phoneNumber)) {
                    signup_phone.setError("Invalid phone number! Phone number must have exactly 10 numbers starting with 0 with no characters.");
                    return;
                }

   /*             Intent intent = new Intent(SignUpActivity.this, OtpActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("password", password);
                startActivity(intent);*/

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
    private boolean isValidEmail(String email) {
        // Check if the email is valid
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Check if the password is valid
        // Password must have at least 8 characters including lowercase letters, uppercase letters, special characters and numbers
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number is valid
        // Phone number must have exactly 10 numbers starting with 0 with no characters
        String phoneNumberPattern = "^0[0-9]{9}$";
        return phoneNumber.matches(phoneNumberPattern);
    }
}
