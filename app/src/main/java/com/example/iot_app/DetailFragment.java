package com.example.iot_app;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    public DetailFragment() {
        // Required empty public constructor
    }

    private RecyclerView rcvDetail;
    private DeviceAdapter deviceAdapter;
//    private ArrayList<Device> listDevice;
    private SharedViewModel viewModel;
    private Room room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String roomName = getArguments().getString("roomName");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(roomName);

        int index = getArguments().getInt("index");

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        List<Device> devices = viewModel.getRooms().getValue().get(index).getDevices();
        if (devices == null) {
            devices = new ArrayList<>();
            viewModel.getRooms().getValue().get(index).setDevices((ArrayList<Device>) devices);
        }
        rcvDetail = view.findViewById(R.id.rcv_detail);
        rcvDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*listDevice = new ArrayList<>();
        // Load the devices from the selected room
        loadDevices();*/
        //    tao khoan cach giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvDetail.addItemDecoration(itemDecoration);

//        set data trong list len view
        deviceAdapter = new DeviceAdapter(devices);
        rcvDetail.setAdapter(deviceAdapter);

        // tao OnClickListener cho button
        FloatingActionButton btnAddDevice = view.findViewById(R.id.btnAddDevice);
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.add_device_layout);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

                EditText edtNameRoom = dialog.findViewById(R.id.edtNameDevice);
                EditText edtInfo = dialog.findViewById(R.id.edtInfo);
                Button btnAddDevice = dialog.findViewById(R.id.btnAddDevice);


                btnAddDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name_device = "", info = "";
                        if(!edtNameRoom.getText().toString().equals("") && !edtInfo.getText().toString().equals("")){
                            name_device = edtNameRoom.getText().toString();
                            info = edtInfo.getText().toString();
                            Device newDevice = new Device(R.drawable.sensor, name_device, info);
                            viewModel.addDeviceToRoom(index, newDevice);
                            deviceAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                        }


                    }
                });

                dialog.show();

            }
        });
        return view;
    }

}