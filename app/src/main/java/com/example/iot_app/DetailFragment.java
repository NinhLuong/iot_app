package com.example.iot_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
/*    public void setRoom(Room room) {
        this.room = room;
        loadDevices();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
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
        Button btnAddRoom = view.findViewById(R.id.btnAddDevice);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Device newDevice = new Device(R.mipmap.ic_launcher, "New Device", "Info");
                viewModel.addDeviceToRoom(index, newDevice);
                deviceAdapter.notifyDataSetChanged();
                /*// tao mot doi tuong phong khac va them vao list
                Device newDevice = new Device(R.mipmap.ic_launcher, "New Device", "Info");
                listDevice.add(newDevice);

                // Save the devices in the list to the room
                room.setDevices(listDevice);

                // thong bao adapter biet dua lieu da bi thay doi
                deviceAdapter.notifyDataSetChanged();*/
            }
        });
        return view;
    }
/*    private void loadDevices() {
        // Load the devices from the selected room
        listDevice = room.getDevices();
    }*/
    // them cac thiet bi vao ArrayList
    /*private List<Device> getListDevice() {
//         list = new ArrayList<>();
        if(list.size() == 0){
            list.add(new Device(R.mipmap.ic_launcher, "Nhiệt độ ", "30c"));
            list.add(new Device(R.mipmap.ic_launcher, "Độ ẩm", "89"));
            list.add(new Device(R.mipmap.ic_launcher, "Quạt", "50"));
        }

        return list;
    }*/
}