package com.example.iot_app.webview_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.iot_app.R;
import com.example.iot_app.device.AirFragment;
import com.example.iot_app.device.LampFragment;

public class WebFragment extends Fragment {

    private Button changeLampButton;
    private Button changeAirButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web, container, false);

        changeLampButton = rootView.findViewById(R.id.change_lamp);
        changeAirButton = rootView.findViewById(R.id.change_ari);

        changeLampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang fragment_lamp và đè hoàn toàn lên fragment cũ
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LampFragment fragmentLamp = new LampFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragmentLamp);
                fragmentTransaction.commit();

                // Ẩn hai button
                changeLampButton.setVisibility(View.GONE);
                changeAirButton.setVisibility(View.GONE);
            }
        });

        changeAirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang fragment_air và đè hoàn toàn lên fragment cũ
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AirFragment fragmentAir = new AirFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragmentAir);
                fragmentTransaction.commit();

                // Ẩn hai button
                changeLampButton.setVisibility(View.GONE);
                changeAirButton.setVisibility(View.GONE);
            }
        });

        return rootView;
    }
}