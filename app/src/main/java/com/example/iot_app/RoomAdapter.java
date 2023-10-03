package com.example.iot_app;

import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        // Handle item long click
        holder.itemView.setOnLongClickListener(v -> {
            int currentPosition = holder.getBindingAdapterPosition();
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Room")
                    .setMessage("Are you sure you want to delete this room?")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Delete the room
                            if (currentPosition != RecyclerView.NO_POSITION) {
                                mListRoom.remove(currentPosition);
                                notifyDataSetChanged();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .setIcon(android.R.drawable.ic_menu_delete)
                    .show();

            return true;
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

}
