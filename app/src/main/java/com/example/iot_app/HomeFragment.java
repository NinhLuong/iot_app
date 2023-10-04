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
    // A private variable for RecyclerView, which lets you display data in a scrolling list.
    private RoomAdapter roomAdapter;
    // A private variable for RoomAdapter, which binds data to views that are displayed within a RecyclerView.
    private List<Room> listRoom;
    // A private variable for a list of Room objects.

    private SharedViewModel viewModel;
    // A private variable for SharedViewModel, which stores and manages UI-related data in a lifecycle conscious way.

    // This method is called to have the fragment instantiate its user interface view.
    // It returns a View that is the root of your fragment's layout. It has three parameters:
    // LayoutInflater (which can be used to inflate any views in the fragment),
    // ViewGroup (which is the parent view that the fragment's UI should be attached to)
    // Bundle (which contains this fragment's previous saved state, if any).
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvData = view.findViewById(R.id.rcv_data);
        // Find a view that was identified by the 'rcv_data' id attribute in XML layout file and assign it to 'rcvData'.
        rcvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Set 'rcvData' to use a linear layout manager (which arranges its children in a single column).
        //    Create a new DividerItemDecoration with current context and vertical orientation.
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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
                dialog.setContentView(R.layout.add_room_layout);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
                // Set the background of this dialog window using a drawable resource.

                EditText edtNameRoom = dialog.findViewById(R.id.edtNameRoom);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);

// Set an OnClickListener on 'btnAdd'. This listener gets notified when 'btnAdd' is clicked or tapped.
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    // This method is called when 'btnAdd' is clicked or tapped.
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        if(!edtNameRoom.getText().toString().equals("")){
                            name = edtNameRoom.getText().toString();
                            //Assign the text in 'edtNameRoom' to 'name'.
                            Room newRoom = new Room(R.drawable.barn, name , "0 device");
                            // Create a new Room object with default image, name from 'edtNameRoom', and "0 device".

                            viewModel.addRoom(newRoom);
                            // Add the new room to SharedViewModel.
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