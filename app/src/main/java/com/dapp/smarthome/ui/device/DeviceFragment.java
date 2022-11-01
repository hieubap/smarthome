package com.dapp.smarthome.ui.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dapp.smarthome.R;
import com.dapp.smarthome.databinding.FragmentDeviceBinding;
import com.dapp.smarthome.ui.device.component.Device;
import com.dapp.smarthome.ui.device.component.DeviceAdapter;

import java.util.ArrayList;

public class DeviceFragment extends Fragment {

    private FragmentDeviceBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        GridView gridView;
//        ArrayList<Device> arrayList = new ArrayList<>();
//        DeviceAdapter adapter;

//        gridView = getView().findViewById(R.id.device_list_device_card);
//        arrayList.add(new Device(R.drawable.ic_living_room,"Living Room",2));
//        arrayList.add(new Device(R.drawable.ic_kitchen,"Kitchen",2));
//        arrayList.add(new Device(R.drawable.ic_bedroom,"Bedroom",1));
//        arrayList.add(new Device(R.drawable.ic_garage,"Garage",1));
//        arrayList.add(new Device(R.drawable.ic_office,"Office",2));
//        arrayList.add(new Device(R.drawable.ic_kidroom,"Kids Room",3));
//        arrayList.add(new Device(R.drawable.ic_tv_room,"TV Room",3));
//        arrayList.add(new Room(R.drawable.ic_living_room,"IconC",222));
//        arrayList.add(new Room(R.drawable.ic_living_room,"IconC",222));

//        adapter = new DeviceAdapter(getView().getContext(),R.layout.home_card, arrayList);
//        gridView.setAdapter(adapter);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}