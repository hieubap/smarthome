package com.dapp.smarthome.ui.device.component;

public class Device {
    private int resourceImage;
    private String name;
    private int numDevice;

    public Device(int resourceImage, String name, int numDevice) {
        this.resourceImage = resourceImage;
        this.name = name;
        this.numDevice = numDevice;
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
