package com.dapp.smarthome.ui.routine.component;

public class Routine {
    private String title;
    private String time;
    private String frequency;
    private int numDevice;

    public Routine(String title, String time, String frequency, int numDevice) {
        this.title = title;
        this.time = time;
        this.frequency = frequency;
        this.numDevice = numDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getNumDevice() {
        return "x" + numDevice + " devices";
    }

    public void setNumDevice(int numDevice) {
        this.numDevice = numDevice;
    }
}
