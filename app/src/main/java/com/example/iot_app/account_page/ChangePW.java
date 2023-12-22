package com.example.iot_app.account_page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.iot_app.MainActivity;
import com.example.iot_app.R;
import com.example.iot_app.sign_in.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ChangePW extends AppCompatActivity {
    EditText loginPassword, loginPasswordAgain, confirmPassword;
    AppCompatButton resetPassword;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loginPassword = findViewById(R.id.editPassword);
        loginPasswordAgain = findViewById(R.id.editNewPassword);
        confirmPassword = findViewById(R.id.editConfirmPassword);
        resetPassword = findViewById(R.id.btnResetPasswordOne);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            Log.d("username: ", username);
            // Do something with the value
        }else {
            Log.d("username: ", "not found username");
        }

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePasswordAgain() | !validatePassword() | !validateNewPassword()) {

                } else {
                    checkUser();
                }
            }
        });
    }

    private void checkUser() {
        String userconfirmPassword= confirmPassword.getText().toString().trim();
        String userPasswordAgain = loginPasswordAgain.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginPassword.setError(null);
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        loginPassword.setError(null);
                        if (userPasswordAgain.equals(userconfirmPassword)){
                            reference.child(username).child("password").setValue(userconfirmPassword);
                            Toast.makeText(ChangePW.this, "Change password successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ChangePW.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            loginPasswordAgain.setError("Password does not match the new password");
                        }


                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginPassword.setError("User does not exist");
                    loginPassword.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChangePW.this, "Error connecting to firebase!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    private boolean validatePasswordAgain() {
        String val = loginPasswordAgain.getText().toString();
        if (val.isEmpty()) {
            loginPasswordAgain.setError("Password cannot be empty");
            return false;
        } else {
            loginPasswordAgain.setError(null);
            return true;
        }
    }

    private boolean validateNewPassword() {
        String val = resetPassword.getText().toString();
        if (val.isEmpty()) {
            resetPassword.setError("New password cannot be empty");
            return false;
        } else {
            resetPassword.setError(null);
            return true;
        }
    }
}