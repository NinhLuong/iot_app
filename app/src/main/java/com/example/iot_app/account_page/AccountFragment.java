package com.example.iot_app.account_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.iot_app.R;
import com.example.iot_app.sign_in.LoginActivity;
import com.example.iot_app.sign_up.SignUpActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountFragment extends Fragment {

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnChangePW = view.findViewById(R.id.btnChangePass);
        TextView getUsername = view.findViewById(R.id.getUsername);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");


        // Add an action listener to the button

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the app with a normal exit code
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the app with a normal exit code
                Intent intent = new Intent(getActivity(), ChangePW.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        return view;
    }
}