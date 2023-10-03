package com.example.iot_app;

import java.util.ArrayList;

public class Room {
    private int resourceId;
    private String room;
    private String device;

    public String getName_room() {
        return name_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    private String name_room;

    private ArrayList<Device> devices;

//    tạo constructor
    public Room(int resourceId, String room, String device) {
        this.resourceId = resourceId;
        this.room = room;
        this.device = device;
        this.devices = new ArrayList<>();
    }
// tạo get set tương ứng
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public int getDeviceCount() {
        return devices.size();
    }
}
