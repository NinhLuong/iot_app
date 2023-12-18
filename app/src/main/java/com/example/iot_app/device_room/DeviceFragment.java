package com.example.iot_app.device_room;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.iot_app.device.Device;
import com.example.iot_app.home_page.Room;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rcvDevice;
    private DeviceRoomAdapter deviceRoomAdapter;
    private SharedViewModel viewModel;
    private List<DeviceRoom> deviceRoomList;

/*
    public DeviceFragment() {
        // Required empty public constructor
    }
*/

    public static DeviceFragment newInstance(String param1, String param2) {
        DeviceFragment fragment = new DeviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        String originalName = viewModel.getRoomArea().getValue().get(index).getDevice();
        edtNameRoom.setText(originalName);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = edtNameRoom.getText().toString();
                if (!newName.isEmpty()) {
                    // Update room name in SharedViewModel
                    Device device = viewModel.getRoomArea().getValue().get(index);
                    device.setDevice(newName);
                    viewModel.getRoomArea().getValue().set(index, device);

                    // Update toolbar title
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(newName);

                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Hãy nhập tên thiết bị mới!", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        rcvDevice = view.findViewById(R.id.rcv_deviceRoom);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvDevice.setLayoutManager(gridLayoutManager);

        Toolbar toolbar = view.findViewById(R.id.toolbar_device);
        // Find a view that was identified by the 'toolbar' id attribute in XML layout file and assign it to 'toolbar'.
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // Set 'toolbar' to act as the ActionBar for this Activity window.
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enable the Up button in the action bar.
        String roomName = getArguments().getString("deviceName");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(roomName);

        int indexRoom = getArguments().getInt("indexRoom");
        int indexArea = getArguments().getInt("indexArea");
        Log.d("indexRoom", String.valueOf(indexRoom));
        Log.d("indexRoom", String.valueOf(indexArea));
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        viewModel.loadData(getContext());
        deviceRoomList = viewModel.getRoomArea().getValue().get(indexRoom).getDeviceRoomArrayList();
//        List<DeviceRoom> devicesRoom = new ArrayList<>();

        if (deviceRoomList == null) {
            deviceRoomList = new ArrayList<>();
            // Initialize 'devices' as an empty ArrayList.
            viewModel.getRoomArea().getValue().get(indexRoom).setDeviceRoomArrayList((ArrayList<DeviceRoom>) deviceRoomList);
            // Set list of devices in room at position 'index' in SharedViewModel to be 'devices'.
        }

        FloatingActionButton btnAddDevice = view.findViewById(R.id.btnAddDeviceRoom);
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                // Create a new dialog instance with current context.
                dialog.setContentView(R.layout.add_device_room_layout);
                // Set the content view of this dialog. The layout resource is 'add_device_layout'.
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
// Set the background of this dialog window using a drawable resource.
                EditText edtNameRoom = dialog.findViewById(R.id.edtNameDevice);

                // Find a view that was identified by 'edtInfo' id attribute in XML layout file and assign it to 'edtInfo'.
                Button btnAddDevice = dialog.findViewById(R.id.btnAddDeviceRoom);
                // Find a view that was identified by 'btnAddDevice' id attribute in XML layout file and assign it to 'btnAddDevice'.

                // Find a view that was identified by 'btnAddDevice' id attribute in XML layout file and assign it to 'btnAddDevice'.
                btnAddDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name_device = "";
                        // Initialize two string variables 'name_device' and 'info'.
                        if(!edtNameRoom.getText().toString().equals("") ){
                            // If the text in 'edtNameRoom' and 'edtInfo' are not empty
                            // Assign the text in 'edtNameRoom' to 'name_device', the text in 'edtInfo' to 'info'.
                            name_device = edtNameRoom.getText().toString();

                            DeviceRoom newDeviceRoom = new DeviceRoom(R.drawable.sensor, name_device);
                            // Create a new Device object with default image, name from 'edtNameRoom', and info from 'edtInfo'.

                            viewModel.adDevicesToRooms(indexArea,indexRoom, newDeviceRoom);
                            // Create a new Device object with default image, name from 'edtNameRoom', and info from 'edtInfo'.
                            deviceRoomAdapter.notifyDataSetChanged();
//                            viewModel.saveData(getContext());
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

        deviceRoomAdapter = new DeviceRoomAdapter(deviceRoomList);
        rcvDevice.setAdapter(deviceRoomAdapter);
        return view;
    }
}