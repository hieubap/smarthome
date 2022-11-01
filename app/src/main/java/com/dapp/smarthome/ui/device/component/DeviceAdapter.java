package com.dapp.smarthome.ui.device.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dapp.smarthome.R;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Device> arrayList;

    public DeviceAdapter(Context context, int layout, List<Device> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout,null);

        Device device = arrayList.get(i);
        ImageView imageView = view.findViewById(R.id.roomIcon);
        TextView textRoomName =  view.findViewById(R.id.roomName);
        TextView textNumDevice =  view.findViewById(R.id.numDevice);

        imageView.setImageResource(device.getResourceImage());
        textRoomName.setText(device.getName());
        textNumDevice.setText(device.getNumDevice());

        return view;
    }
}
