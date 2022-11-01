package com.dapp.smarthome.ui.routine.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dapp.smarthome.R;

import java.util.List;

public class RoutineAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Routine> arrayList;

    public RoutineAdapter(Context context, int layout, List<Routine> arrayList) {
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

        Routine routine = arrayList.get(i);
        TextView textTitle =  view.findViewById(R.id.routine_title);
        TextView textTime =  view.findViewById(R.id.routine_time);
        TextView textFrequency =  view.findViewById(R.id.routine_frequency);
        TextView textNumDevice =  view.findViewById(R.id.routine_num_device);

        textTitle.setText(routine.getTitle());
        textTime.setText(routine.getTime());
        textFrequency.setText(routine.getFrequency());
        textNumDevice.setText(routine.getNumDevice());

        return view;
    }
}
