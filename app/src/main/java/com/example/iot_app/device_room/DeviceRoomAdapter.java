package com.example.iot_app.device_room;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_app.R;
import com.example.iot_app.home_page.RoomAdapter;

import java.util.List;

public class DeviceRoomAdapter  extends RecyclerView.Adapter<DeviceRoomAdapter.DevicRoomViewHolder>{
    private List<DeviceRoom> mLDeviceRoom;
    public DeviceRoomAdapter(List<DeviceRoom> mLDeviceRoom){this.mLDeviceRoom = mLDeviceRoom;}
    @NonNull
    @Override
    public DevicRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DevicRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicRoomViewHolder holder, int position) {
        DeviceRoom deviceRoom = mLDeviceRoom.get(position);
        if(deviceRoom == null){
            return ;
        }
        // Set the resource
        holder.imageDevice.setImageResource(deviceRoom.getResourceId());
        holder.txtNameDvice.setText(deviceRoom.getNameDevice());
//        holder.txtDetail.setText(deviceRoom.getStatusDevice());

        holder.itemView.setOnLongClickListener(v -> {

            int currentPosition = holder.getBindingAdapterPosition();
            // Get adapter position of ViewHolder in RecyclerView and assign it to 'currentPosition'.
            // Create a builder for an alert dialog that uses default alert dialog theme.
            new AlertDialog.Builder(v.getContext())
                    // Set title text for dialog.
                    .setTitle("Delete Device")
                    // Set message text for dialog.
                    .setMessage("Are you sure you want to delete this device?")
                    // Add positive button to dialog with text "OK" and click listener.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        // Add positive button to dialog with text "OK" and click listener.
                        public void onClick(DialogInterface dialog, int which) {

                            if (currentPosition != RecyclerView.NO_POSITION) {
                                //  If 'currentPosition' is a valid position Remove the device at 'currentPosition' from mListDevice.
                                mLDeviceRoom.remove(currentPosition);
                                // Notify DeviceAdapter that underlying data has changed and it should refresh itself.
                                notifyDataSetChanged();
                            }
                        }
                    })
                    // Add negative button to dialog with text "Cancel" and null click listener.
                    .setNegativeButton(android.R.string.cancel, null)
                    // Set icon for dialog using a drawable resource.
                    .setIcon(android.R.drawable.ic_menu_delete)
                    // Show this dialog, adding it to the screen.
                    .show();

            return true;
            // Return true because we've handled this long click event.
        });
    }

    @Override
    public int getItemCount() {
        if (mLDeviceRoom != null) {
            return mLDeviceRoom.size();
        }
        return 0;
    }

    public class DevicRoomViewHolder extends RecyclerView.ViewHolder {
        //khoi tao bien
        private ImageView imageDevice;
        private TextView txtNameDvice;
//        private TextView txtDetail;
        // This is a public constructor for RoomViewHolder.
        // It initializes a new RoomViewHolder object with a view.
        public DevicRoomViewHolder(@NonNull View itemView) {
            super(itemView);
//            anh xa bien
            imageDevice = itemView.findViewById(R.id.img_device);
            txtNameDvice = itemView.findViewById(R.id.txt_deviceRoom);
//            txtDetail = itemView.findViewById(R.id.txt_detail);
        }
    }
}


