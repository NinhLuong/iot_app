package com.example.iot_app.device;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.iot_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AirFragment extends Fragment {

    Boolean ledStatus;
    TextView valueInten, txtIntensity ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_air, container, false);
        String roomName = getArguments().getString("roomName");
        String deviceName = getArguments().getString("deviceName");

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(deviceName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        Switch switchAir = rootView.findViewById(R.id.switchAir);
        Switch switchAuto = rootView.findViewById(R.id.switchAuto);
        TextView textTemp = rootView.findViewById(R.id.textTemp);
        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);
        AppCompatSeekBar seekBarLamp = rootView.findViewById(R.id.seekBarAir);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("rooms");
        DatabaseReference switchStatus = myRef.child(roomName).child("devices").child(deviceName).child("swithStatus");
        DatabaseReference intensityRef = myRef.child(roomName).child("devices").child(deviceName).child("detail");
        DatabaseReference modeRef = myRef.child(roomName).child("devices").child(deviceName).child("mode");
        switchStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the data from the snapshot
                ledStatus = dataSnapshot.getValue(Boolean.class);
                Log.d("ledStatus", "Value is: " + ledStatus);

                if(ledStatus != null ){

                    switchAir.setChecked(ledStatus);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("ledStatus error: ", String.valueOf(error));
            }
        });

        intensityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer intensity = dataSnapshot.getValue(Integer.class);
                if (intensity == null) {
                    intensity = 25;
                    intensityRef.setValue(intensity);
                }

                textTemp.setText(intensity);
                seekBarLamp.setProgress(intensity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value intensityRef.", databaseError.toException());
            }
        });

        seekBarLamp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textTemp.setText(progress);
                if (fromUser) {
                    intensityRef.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return rootView;
    }
}