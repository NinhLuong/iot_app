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
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rcvDetail;
    private DeviceAdapter deviceAdapter;
    public List<Device> list = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        rcvDetail = view.findViewById(R.id.rcv_detail);
        rcvDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

        //    tao khoan cach giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvDetail.addItemDecoration(itemDecoration);

//        set data trong list len view
        deviceAdapter = new DeviceAdapter(getListDevice());
        rcvDetail.setAdapter(deviceAdapter);

        // tao OnClickListener cho button
        Button btnAddRoom = view.findViewById(R.id.btnAddDevice);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tao mot doi tuong phong khac va them vao list
                Device newDevice = new Device(R.mipmap.ic_launcher, "New Device", "Info");
                list.add(newDevice);

                // thong bao adapter biet dua lieu da bi thay doi
                deviceAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    // them cac thiet bi vao ArrayList
    private List<Device> getListDevice() {
//         list = new ArrayList<>();
        if(list.size() == 0){
            list.add(new Device(R.mipmap.ic_launcher, "Nhiệt độ ", "30c"));
            list.add(new Device(R.mipmap.ic_launcher, "Độ ẩm", "89"));
            list.add(new Device(R.mipmap.ic_launcher, "Quạt", "50"));
        }

        return list;
    }
}