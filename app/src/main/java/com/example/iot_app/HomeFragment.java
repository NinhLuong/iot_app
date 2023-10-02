package com.example.iot_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rcvData;
    private RoomAdapter roomAdapter;
    public List<Room> list = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvData = view.findViewById(R.id.rcv_data);
        rcvData.setLayoutManager(new LinearLayoutManager(getActivity()));

        // tao OnClickListener cho button
        Button btnAddRoom = view.findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tao mot doi tuong phong khac va them vao list
                Room newRoom = new Room(R.mipmap.ic_launcher, "New Room", "0 device");
                list.add(newRoom);

                // thong bao adapter biet dua lieu da bi thay doi
                roomAdapter.notifyDataSetChanged();
            }
        });

//    tao khoan cach giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

//        set data trong list len view
        roomAdapter = new RoomAdapter(getListRoom());
        rcvData.setAdapter(roomAdapter);



        return view;
    }
// them cac phong vao ArrayList
    public List<Room> getListRoom() {
//        list = new ArrayList<>();
        if(list.size() == 0){
            list.add(new Room(R.mipmap.ic_launcher, "Room 1", "4 device"));
            list.add(new Room(R.mipmap.ic_launcher, "Room 2", "3 device"));
            list.add(new Room(R.mipmap.ic_launcher, "Room 3", "5 device"));
        }

        return list;
    }


}