package com.example.iot_app.device;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.iot_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LampFragment extends Fragment {
     Switch swLed;
     TextView valueInten, txtIntensity ;
     RadioGroup radioGroup;
     Boolean ledStatus;
     ImageView imageView;
    AppCompatRadioButton radioButton;
    AppCompatSeekBar seekBarLamp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String roomName = getArguments().getString("roomName");
        String deviceName = getArguments().getString("deviceName");
        View rootView = inflater.inflate(R.layout.fragment_lamp, container, false);
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


        swLed = rootView.findViewById(R.id.switchLamp);
        imageView = rootView.findViewById(R.id.imageView);
        imageView = rootView.findViewById(R.id.imageView);
        txtIntensity = rootView.findViewById(R.id.txtIntensity);
        seekBarLamp = rootView.findViewById(R.id.seekBarLamp);
        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("rooms");
        DatabaseReference switchStatus = myRef.child(roomName).child("devices").child(deviceName).child("swithStatus");
        DatabaseReference detailRef = myRef.child(roomName).child("devices").child(deviceName).child("detail");
        DatabaseReference intensityRef = myRef.child(roomName).child("devices").child(deviceName).child("Intensity");
        switchStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the data from the snapshot
                ledStatus = dataSnapshot.getValue(Boolean.class);
                Log.d("ledStatus", "Value is: " + ledStatus);

                if(ledStatus != null ){

                    swLed.setChecked(ledStatus);
                }

                if (!ledStatus ) {
                    imageView.setVisibility(View.INVISIBLE);
                    seekBarLamp.setEnabled(false);
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(false);
                    }
                    Log.d("complete setup false", "Value is: " + ledStatus);
                }else{
                    imageView.setVisibility(View.VISIBLE);
                    seekBarLamp.setEnabled(true);
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(true);
                    }
                    Log.d("complete setup true", "Value is: " + ledStatus);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("ledStatus error: ", String.valueOf(error));
            }
        });

        swLed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myRef.child(roomName).child("devices").child(deviceName).child("swithStatus").setValue(isChecked);

            }
        });

        AppCompatRadioButton radioButtonGreen = rootView.findViewById(R.id.green);
        AppCompatRadioButton radioButtonBlue = rootView.findViewById(R.id.blue);
        AppCompatRadioButton radioButtonPurple = rootView.findViewById(R.id.purple);
        AppCompatRadioButton radioButtonYellow = rootView.findViewById(R.id.yellow);
        AppCompatRadioButton radioButtonOrange = rootView.findViewById(R.id.orange);
        AppCompatRadioButton radioButtonRed = rootView.findViewById(R.id.red);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = rootView.findViewById(checkedId);
                String color = radioButton.getId() == R.id.green ? "green" :
                        radioButton.getId() == R.id.blue ? "blue" :
                                radioButton.getId() == R.id.purple ? "purple" :
                                        radioButton.getId() == R.id.yellow ? "yellow" :
                                                radioButton.getId() == R.id.orange ? "orange" :
                                                        radioButton.getId() == R.id.red ? "red" :
                                                                "yellow";
                myRef.child(roomName).child("devices").child(deviceName).child("detail").setValue(color);
            }
        });


        detailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the data from the snapshot
                String detail = dataSnapshot.getValue(String.class);
                Log.d("detail", "Value is: " + detail);

                if(detail != null && !detail.equals("") && ledStatus != false){
                    switch (detail){
                        case "green":
                            imageView.setImageResource(R.drawable.green);
                            radioButtonGreen.setChecked(true);
                            break;
                        case "blue":
                            imageView.setImageResource(R.drawable.blue);
                            radioButtonBlue.setChecked(true);
                            break;
                        case "purple":
                            imageView.setImageResource(R.drawable.purple);
                            radioButtonPurple.setChecked(true);
                            break;
                        case "yellow":
                            imageView.setImageResource(R.drawable.yellow);
                            radioButtonYellow.setChecked(true);
                            break;
                        case "orange":
                            imageView.setImageResource(R.drawable.orange);
                            radioButtonOrange.setChecked(true);
                            break;
                        case "red":
                            imageView.setImageResource(R.drawable.red);
                            radioButtonRed.setChecked(true);
                            break;
                        default:
                            imageView.setImageResource(R.drawable.yellow);
                            break;
                    }
                }else{
                    imageView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("error detail in firebase: ", String.valueOf(error));
            }
        });

        intensityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer intensity = dataSnapshot.getValue(Integer.class);
                if (intensity == null) {
                    intensity = 100;
                    intensityRef.setValue(intensity);
                }
                float opacity = intensity / 100f;
                imageView.setAlpha(opacity);
                txtIntensity.setText(intensity + "%");
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
                float opacity = progress / 100f;
                imageView.setAlpha(opacity);
                txtIntensity.setText(progress + "%");
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