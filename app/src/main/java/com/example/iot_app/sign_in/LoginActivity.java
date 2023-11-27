package com.example.iot_app.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iot_app.MainActivity;
import com.example.iot_app.R;
import com.example.iot_app.sign_up.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
//    khai báo kiểu class của các biến
    private EditText userEdt, passwordEdt;
    private Button loginBtn, signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hiển thị activity_login lên mang hình
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
//       ánh xạ các phân tử trong layout
        userEdt = findViewById(R.id.editUser);
        passwordEdt = findViewById(R.id.editPassword);
        loginBtn = findViewById(R.id.btnLogin);
        signupBtn = findViewById(R.id.btnSignup);
//        xử lý xự kiện khi nhấn nút button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                khi nhập mật khẩu và tên đúng
                if(userEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill your user and password",Toast.LENGTH_LONG).show();
                } else if (userEdt.getText().toString().equals("haininh") && passwordEdt.getText().toString().equals("03122002")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else{
                    Toast.makeText(LoginActivity.this, "Password or username is wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }
}