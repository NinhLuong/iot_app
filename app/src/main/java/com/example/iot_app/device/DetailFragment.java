package com.example.iot_app.device;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iot_app.R;
import com.example.iot_app.SharedViewModel;
import com.example.iot_app.home_page.Room;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    private RecyclerView rcvDetail;
    // A private variable for RecyclerView, which lets you display data in a scrolling list.
    private DeviceAdapter deviceAdapter;
    // A private variable for DeviceAdapter, which binds data to views that are displayed within a RecyclerView.
    private SharedViewModel viewModel;
    // A private variable for SharedViewModel,
    // which stores and manages UI-related data in a lifecycle conscious way.
    private Room room;
    // A private variable for a Room object.

    @Override
    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            showEditDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showEditDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.edit_room_layout);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        EditText edtNameRoom = dialog.findViewById(R.id.edtNameRoom);
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        int index = getArguments().getInt("index");
        String originalName = viewModel.getRooms().getValue().get(index).getRoom();
        edtNameRoom.setText(originalName);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = edtNameRoom.getText().toString();
                if (!newName.isEmpty()) {
                    // Update room name in SharedViewModel
                    Room room = viewModel.getRooms().getValue().get(index);
                    room.setRoom(newName);
                    viewModel.getRooms().getValue().set(index, room);

                    // Update toolbar title
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(newName);

                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Hãy nhập tên phòng mới!", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
// Find a view that was identified by 'rcv_detail' id attribute in XML layout file and assign it to 'rcvDetail'.
        rcvDetail = view.findViewById(R.id.rcv_detail);
        // Set 'rcvDetail' to use a linear layout manager (which arranges its children in a single column).
        rcvDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvDetail.addItemDecoration(itemDecoration);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Find a view that was identified by the 'toolbar' id attribute in XML layout file and assign it to 'toolbar'.
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // Set 'toolbar' to act as the ActionBar for this Activity window.
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enable the Up button in the action bar.
        String roomName = getArguments().getString("roomName");
        // Retrieve the value associated with the key "roomName" from the arguments supplied
        // when this fragment was instantiated and assign it to 'roomName'.
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(roomName);
// Set the title text for this activity's ActionBar represented by 'toolbar'.
        int index = getArguments().getInt("index");
        // Retrieve the value associated with the key "index" from the arguments supplied
        // when this fragment was instantiated and assign it to 'index'.

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // Get an instance of SharedViewModel associated with this activity.

        List<Device> devices = viewModel.getRooms().getValue().get(index).getDevices();
        // Get list of devices from room at position 'index' in SharedViewModel and assign it to 'devices'.

        if (devices == null) {
            devices = new ArrayList<>();
            // Initialize 'devices' as an empty ArrayList.
            viewModel.getRooms().getValue().get(index).setDevices((ArrayList<Device>) devices);
            // Set list of devices in room at position 'index' in SharedViewModel to be 'devices'.
        }

//        // Create a new DeviceAdapter with list of devices and assign it to 'deviceAdapter'.
        deviceAdapter = new DeviceAdapter(devices);
        rcvDetail.setAdapter(deviceAdapter);
        // Set the adapter for 'rcvDetail' to be 'deviceAdapter'.

        // Find a view that was identified by 'btnAddDevice' id attribute in XML layout file and assign it to 'btnAddDevice'.
        FloatingActionButton btnAddDevice = view.findViewById(R.id.btnAddDevice);
        // Set an OnClickListener on 'btnAddDevice'. This listener gets notified when 'btnAddDevice' is clicked or tapped.
        // This method is called when 'btnAddDevice' is clicked or tapped.
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                // Create a new dialog instance with current context.
                dialog.setContentView(R.layout.add_device_layout);
                // Set the content view of this dialog. The layout resource is 'add_device_layout'.
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
// Set the background of this dialog window using a drawable resource.
                EditText edtNameRoom = dialog.findViewById(R.id.edtNameDevice);
                // Find a view that was identified by 'edtNameDevice' id attribute in XML layout file and assign it to 'edtNameRoom'.
                EditText edtInfo = dialog.findViewById(R.id.edtInfo);
                // Find a view that was identified by 'edtInfo' id attribute in XML layout file and assign it to 'edtInfo'.
                Button btnAddDevice = dialog.findViewById(R.id.btnAddDevice);
                // Find a view that was identified by 'btnAddDevice' id attribute in XML layout file and assign it to 'btnAddDevice'.

                // Find a view that was identified by 'btnAddDevice' id attribute in XML layout file and assign it to 'btnAddDevice'.
                btnAddDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name_device = "", info = "";
                        // Initialize two string variables 'name_device' and 'info'.
                        if(!edtNameRoom.getText().toString().equals("") && !edtInfo.getText().toString().equals("")){
                            // If the text in 'edtNameRoom' and 'edtInfo' are not empty
                            // Assign the text in 'edtNameRoom' to 'name_device', the text in 'edtInfo' to 'info'.
                            name_device = edtNameRoom.getText().toString();
                            info = edtInfo.getText().toString();

                            Device newDevice = new Device(R.drawable.sensor, name_device, info);
                            // Create a new Device object with default image, name from 'edtNameRoom', and info from 'edtInfo'.

                            viewModel.addDeviceToRoom(index, newDevice);
                            // Create a new Device object with default image, name from 'edtNameRoom', and info from 'edtInfo'.
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