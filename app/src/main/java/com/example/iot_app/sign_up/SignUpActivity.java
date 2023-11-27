package com.example.iot_app.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.iot_app.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstName, lastName;
    private EditText userEdt, passwordEdt;
    private Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
    }
}