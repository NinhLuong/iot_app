package com.example.iot_app.device;

import com.example.iot_app.R;
import com.example.iot_app.device_room.DeviceRoom;

import java.util.ArrayList;

public class Device {
    private int idDevice;
    private String device;
    private String detail;
    private String category;
    private String nameRoom;
    private boolean swithStatus;



    private ArrayList<DeviceRoom> deviceRoomArrayList;
    public Device(int idDevice, String device, String detail, boolean swithStatus, String category, String nameRoom) {
        this.idDevice = idDevice;
        this.device = device;
        this.detail = detail;
        this.swithStatus = swithStatus;
        this.nameRoom = nameRoom;
        this.category = category;

        this.deviceRoomArrayList = new ArrayList<>();
    }
    public boolean isSwithStatus() {
        return this.swithStatus;
    }

    public void setSwithStatus(final boolean swithStatus) {
        this.swithStatus = swithStatus;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<DeviceRoom> getDeviceRoomArrayList() {
        return deviceRoomArrayList;
    }

    public void setDeviceRoomArrayList(final ArrayList<DeviceRoom> deviceRoomArrayList) {
        this.deviceRoomArrayList = deviceRoomArrayList;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public int getDeviceCount() {
        return deviceRoomArrayList.size();
    }

    public String getNameRoom() {
        return this.nameRoom;
    }

    public void setNameRoom(final String nameRoom) {
        this.nameRoom = nameRoom;
    }
}


