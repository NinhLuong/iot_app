package com.example.iot_app.device_room;

public class DeviceRoom {
    private int resourceId;
    private String nameDevice;
    private String statusDevice;
    private Boolean statusSwitch;


    public DeviceRoom(int resourceId, String nameDevice, String statusDevice, Boolean statusSwitch) {
        this.resourceId = resourceId;
        this.nameDevice = nameDevice;
        this.statusDevice = statusDevice;
        this.statusSwitch = statusSwitch;
    }
    public DeviceRoom(int resourceId, String nameDevice){
        this.resourceId = resourceId;
        this.nameDevice = nameDevice;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public String getNameDevice() {
        return this.nameDevice;
    }

    public String getStatusDevice() {
        return this.statusDevice;
    }

    public Boolean getStatusSwitch() {
        return this.statusSwitch;
    }

    public void setResourceId(final int resourceId) {
        this.resourceId = resourceId;
    }

    public void setNameDevice(final String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public void setStatusDevice(final String statusDevice) {
        this.statusDevice = statusDevice;
    }

    public void setStatusSwitch(final Boolean statusSwitch) {
        this.statusSwitch = statusSwitch;
    }
}
