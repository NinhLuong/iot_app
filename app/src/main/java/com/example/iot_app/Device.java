package com.example.iot_app;

public class Device {
    // This is a public class named Device.
    private int idDevice;
    private String device;
    private String detail;

    // This is a public constructor for Device.
    // It initializes a new Device object with device ID, device name, and device detail.
    public Device(int idDevice, String device, String detail) {
        this.idDevice = idDevice;
        this.device = device;
        this.detail = detail;
    }

    // create getter setter

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
}


