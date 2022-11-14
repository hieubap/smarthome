package com.dapp.smarthome.adapter;

public class Score {
    private String sdt;
    private String name;
    private long point;

    public Score(String sdt,String name, long numDevice) {
        this.name = name;
        this.sdt = sdt;
        this.point = numDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String name) {
        this.sdt = name;
    }

    public String getPoint() {
        return ""+ point;
    }

    public long getPointInt() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }
}
