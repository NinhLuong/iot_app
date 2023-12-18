package com.example.iot_app.home_page;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_app.MainActivity;
import com.example.iot_app.R;
import com.example.iot_app.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    private RecyclerView rcvData;
    // A private variable for RecyclerView, which lets you display data in a scrolling list.
    private RoomAdapter roomAdapter;

    // A private variable for RoomAdapter, which binds data to views that are displayed within a RecyclerView.
    private List<Room> listRoom;
    // A private variable for a list of Room objects.

    private MainActivity mainActivity;

    private SharedViewModel viewModel;
    private TextView userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainActivity = (MainActivity) getActivity();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("rooms");

        userName = view.findViewById(R.id.userName);
        rcvData = view.findViewById(R.id.rcv_data);
//        userName.setText("Hi, " + username);
        userName.setText("Hi, " + mainActivity.getGusername());
        // Find a view that was identified by the 'rcv_data' id attribute in XML layout file and assign it to 'rcvData'.

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvData.setLayoutManager(gridLayoutManager);
        // Set 'rcvData' to use a linear layout manager (which arranges its children in a single column).
        //    Create a new DividerItemDecoration with current context and vertical orientation.

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        viewModel.loadData(getContext());
        // Get an instance of SharedViewModel associated with this activity.

        // Observe changes to list of rooms in SharedViewModel.
        // The second parameter is an observer that gets notified when list of rooms changes.
        viewModel.getRooms().observe(getViewLifecycleOwner(), new Observer<List<Room>>() {
            // This method is called when list of rooms changes.
            @Override
            public void onChanged(List<Room> rooms) {
                roomAdapter.setRooms(rooms);
                // Update rooms in 'roomAdapter'.
                roomAdapter.notifyDataSetChanged();
                // Notify 'roomAdapter' that underlying data has changed and it should refresh itself.
            }
        });

        // Find a view that was identified by 'btnAddRoom' id attribute in XML layout file and assign it to 'btnAddRoom'.
        FloatingActionButton btnAddRoom = view.findViewById(R.id.btnAddRoom);
        // Set an OnClickListener on 'btnAddRoom'. This listener gets notified when 'btnAddRoom' is clicked or tapped.
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            // This method is called when 'btnAddRoom' is clicked or tapped.
            @Override
            public void onClick(View v) {
                // Create a new dialog instance with current context.
                Dialog dialog = new Dialog(getContext());
                // Set the content view of this dialog. The layout resource is 'add_room_layout'.
                dialog.setContentView(R.layout.add_area_layout);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
                // Set the background of this dialog window using a drawable resource.

                EditText edtNameRoom = dialog.findViewById(R.id.edtNameArea);
                Button btnAdd = dialog.findViewById(R.id.btnAddArea);

// Set an OnClickListener on 'btnAdd'. This listener gets notified when 'btnAdd' is clicked or tapped.
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    // This method is called when 'btnAdd' is clicked or tapped.
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        if(!edtNameRoom.getText().toString().equals("")){
                            name = edtNameRoom.getText().toString();
                            //Assign the text in 'edtNameRoom' to 'name'.
                            Room newRoom = new Room(R.drawable.cold_storage, name , "0 device");
                            // Create a new Room object with default image, name from 'edtNameRoom', and "0 device".
                            myRef.child(name).child("Temp").setValue("");
                            myRef.child(name).child("Hum").setValue("");
                            viewModel.addRoom(newRoom);
                            // Add the new room to SharedViewModel.
//                            viewModel.saveData(getContext());
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

        // Create a new RoomAdapter with list of rooms and assign it to 'roomAdapter'.
        roomAdapter = new RoomAdapter(listRoom);
        // Set the adapter for 'rcvData' to be 'roomAdapter'.
        rcvData.setAdapter(roomAdapter);

        return view;
    }


}