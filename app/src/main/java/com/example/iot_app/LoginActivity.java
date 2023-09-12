package com.example.iot_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt, passwordEdt;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt = findViewById(R.id.editUser);
        passwordEdt = findViewById(R.id.editPassword);
        loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill your user and password",Toast.LENGTH_LONG).show();
                } else if (userEdt.getText().toString().equals("haininh") && passwordEdt.getText().toString().equals("03122002")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else{
                    Toast.makeText(LoginActivity.this, "Password or username is wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}