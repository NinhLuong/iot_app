package com.example.iot_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    public List<Room> mListRoom;

    public RoomAdapter(List<Room> mListRoom) {
        this.mListRoom = mListRoom;
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
        holder.txtDevice.setText(room.getDevice());

        // Handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondFragment
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new DetailFragment())
                        .addToBackStack(null)
                        .commit();
            }
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
