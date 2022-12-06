package com.dapp.smarthome.research;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dapp.smarthome.R;
import com.dapp.smarthome.ui.home.component.Room;

import java.util.List;

public class SheetAdapter extends BaseAdapter {
    private List<String> strings;
    private Context context;
    private int layout;

    public SheetAdapter(List<String> strings, Context context, int layout) {
        this.strings = strings;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return 0;
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
//
        view = inflater.inflate(layout,null);

        String room = strings.get(i);
        Button btn = new Button(context);
        btn.setText("room");
//        btn.setcontext


        return btn;
    }
}
