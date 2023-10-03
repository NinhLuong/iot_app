package com.example.iot_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Room>> rooms = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Room>> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        List<Room> currentRooms = rooms.getValue();
        currentRooms.add(room);
        rooms.setValue(currentRooms);
    }

    public void addDeviceToRoom(int index, Device device) {
        List<Room> currentRooms = rooms.getValue();
        Room room = currentRooms.get(index);
        room.getDevices().add(device);
        rooms.setValue(currentRooms);
    }
}

