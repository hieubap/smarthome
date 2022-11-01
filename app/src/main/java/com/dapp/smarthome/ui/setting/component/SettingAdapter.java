package com.dapp.smarthome.ui.setting.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.dapp.smarthome.R;
import com.dapp.smarthome.ui.home.component.Room;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SettingItem> arrayList;

    public SettingAdapter(Context context, int layout, ArrayList<SettingItem> arrayList) {
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

        SettingItem room = arrayList.get(i);
        TextView textRoomName =  view.findViewById(R.id.setting_item_content);
        textRoomName.setText(room.getName());
        if(i+1 == getCount()){
            textRoomName.setTextColor(ContextCompat.getColor(context,R.color.red_1));
        }
        if((i+1)%3 == 0){
            ConstraintLayout borderConstraint = view.findViewById(R.id.setting_item_border);
            borderConstraint.getLayoutParams().height = 15;
        }

        return view;
    }
}
