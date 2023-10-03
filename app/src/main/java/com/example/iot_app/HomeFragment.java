package com.example.iot_app;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    private RecyclerView rcvData;
    private RoomAdapter roomAdapter;
    private List<Room> listRoom;
//    public List<Room> list = new ArrayList<>();

    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvData = view.findViewById(R.id.rcv_data);
        rcvData.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getRooms().observe(getViewLifecycleOwner(), new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                roomAdapter.setRooms(rooms);
                roomAdapter.notifyDataSetChanged();
            }
        });

        // tao OnClickListener cho button
        FloatingActionButton btnAddRoom = view.findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.add_room_layout);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

                EditText edtNameRoom = dialog.findViewById(R.id.edtNameRoom);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        if(!edtNameRoom.getText().toString().equals("")){
                            name = edtNameRoom.getText().toString();
                            Room newRoom = new Room(R.drawable.barn, name , "0 device");
                            viewModel.addRoom(newRoom);
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), "Hãy nhập tên của phòng!", Toast.LENGTH_LONG).show();
                        }


                    }
                });

                dialog.show();
            }
        });

//    tao khoan cach giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

//        set data trong list len view
        roomAdapter = new RoomAdapter(listRoom);
        rcvData.setAdapter(roomAdapter);

        return view;
    }
    // Add a new room to the list of rooms
    public void addRoom(Room room) {
        listRoom.add(room);
        roomAdapter.notifyDataSetChanged();
    }
// them cac phong vao ArrayList
/*    public List<Room> getListRoom() {
//        list = new ArrayList<>();
        if(listRoom.size() == 0){
            listRoom.add(new Room(R.mipmap.ic_launcher, "Room 1", "4 device"));
            listRoom.add(new Room(R.mipmap.ic_launcher, "Room 2", "3 device"));
            listRoom.add(new Room(R.mipmap.ic_launcher, "Room 3", "5 device"));
        }

        return listRoom;
    }*/


}