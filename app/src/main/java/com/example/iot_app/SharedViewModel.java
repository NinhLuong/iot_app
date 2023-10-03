package com.example.iot_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public String roomsToJson() {
        Gson gson = new Gson();
        return gson.toJson(rooms.getValue());
    }

    public void jsonToRooms(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Room>>() {}.getType();
        List<Room> roomList = gson.fromJson(json, type);
        rooms.setValue(roomList);
    }

}

