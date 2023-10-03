package com.example.iot_app;

import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    public List<Room> mListRoom;

    public  RoomAdapter(List<Room> mListRoom) {
        this.mListRoom = mListRoom;
    }

    public void setRooms(List<Room> rooms) {
        this.mListRoom = rooms;
    }
    /*private OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }*/


    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        truyen item_room vao bien view co kieu View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = mListRoom.get(position);
        if (room == null){
            return ;
        }
        holder.imageAvatar.setImageResource(room.getResourceId());
        holder.txtRoom.setText(room.getRoom());
        // Display the number of devices
        String deviceText = room.getDeviceCount() + " device(s)";
        holder.txtDevice.setText(deviceText);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            detailFragment.setArguments(bundle);

            FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
//        kiem tra neu list phong khong rong thi tra ve so luong phan tu
        if (mListRoom != null) {
            return mListRoom.size();
        }
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        //khoi tao bien
        private ImageView imageAvatar;
        private TextView txtRoom;
        private TextView txtDevice;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
//            anh xa bien
            imageAvatar = itemView.findViewById(R.id.img_avatar);
            txtRoom = itemView.findViewById(R.id.txt_room);
            txtDevice = itemView.findViewById(R.id.txt_device);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}
