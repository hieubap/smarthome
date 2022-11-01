package com.dapp.smarthome.ui.home.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dapp.smarthome.R;

import java.util.List;

public class RoomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Room> arrayList;

    public RoomAdapter(Context context, int layout, List<Room> arrayList) {
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

        Room room = arrayList.get(i);
        ImageView imageView = view.findViewById(R.id.roomIcon);
        TextView textRoomName =  view.findViewById(R.id.roomName);
        TextView textNumDevice =  view.findViewById(R.id.numDevice);

        imageView.setImageResource(room.getResourceImage());
        textRoomName.setText(room.getName());
        textNumDevice.setText(room.getNumDevice());

        return view;
    }
}
