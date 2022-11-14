package com.dapp.smarthome.ui.home.component;

public class Room {
    private int resourceImage;
    private String name;
    private String str;
    private int numDevice;

    public Room(int resourceImage, String name, int numDevice) {
        this.resourceImage = resourceImage;
        this.name = name;
        this.numDevice = numDevice;
    }

    public Room(int resourceImage, String name, String numDevice) {
        this.resourceImage = resourceImage;
        this.name = name;
        this.str = numDevice;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String name) {
        this.str = str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumDevice() {
        return "x"+numDevice + " Devices";
    }

    public void setNumDevice(int numDevice) {
        this.numDevice = numDevice;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }
}
