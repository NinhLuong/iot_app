package com.example.iot_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewholder> {

    private List<Device> mListDevice;

    public DeviceAdapter(List<Device> mListDevice) {
        this.mListDevice = mListDevice;
    }

    @NonNull
    @Override
    public DeviceViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_detail, parent, false);
        return new DeviceAdapter.DeviceViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewholder holder, int position) {
        Device device = mListDevice.get(position);
        if(device == null){
            return ;
        }
        holder.imageDevice.setImageResource(device.getIdDevice());
        holder.txtName.setText(device.getDevice());
        holder.txtDetail.setText(device.getDetail());
    }

    @Override
    public int getItemCount() {
        if (mListDevice != null) {
            return mListDevice.size();
        }
        return 0;
    }

    public class DeviceViewholder extends RecyclerView.ViewHolder{

        private ImageView imageDevice;
        private TextView txtName;
        private TextView txtDetail;
        public DeviceViewholder(@NonNull View itemView) {
            super(itemView);
//            anh xa bien
            imageDevice = itemView.findViewById(R.id.img_device);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDetail = itemView.findViewById(R.id.txt_detail);
        }
    }
}
