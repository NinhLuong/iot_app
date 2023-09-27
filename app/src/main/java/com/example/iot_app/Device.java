package com.example.iot_app;

public class Device {
    private int idDevice;
    private String device;
    private String detail;

    //    tạo constructor
    public Device(int idDevice, String device, String detail) {
        this.idDevice = idDevice;
        this.device = device;
        this.detail = detail;
    }

    // tạo get set tương ứng

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


