package com.dapp.smarthome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dapp.smarthome.MainActivity;
import com.dapp.smarthome.R;
import com.dapp.smarthome.databinding.FragmentHomeBinding;
import com.dapp.smarthome.ui.home.component.Room;
import com.dapp.smarthome.ui.home.component.RoomAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridView;
        ArrayList<Room> arrayList = new ArrayList<>();
        RoomAdapter adapter;

        gridView = getView().findViewById(R.id.home_list_home_card);
        arrayList.add(new Room(R.drawable.ic_living_room,"Living Room",2));
        arrayList.add(new Room(R.drawable.ic_kitchen,"Kitchen",2));
        arrayList.add(new Room(R.drawable.ic_bedroom,"Bedroom",1));
        arrayList.add(new Room(R.drawable.ic_garage,"Garage",1));
        arrayList.add(new Room(R.drawable.ic_office,"Office",2));
        arrayList.add(new Room(R.drawable.ic_kidroom,"Kids Room",3));
        arrayList.add(new Room(R.drawable.ic_tv_room,"TV Room",3));
//        arrayList.add(new Room(R.drawable.ic_living_room,"IconC",222));
//        arrayList.add(new Room(R.drawable.ic_living_room,"IconC",222));

        adapter = new RoomAdapter(getView().getContext(),R.layout.home_card, arrayList);
        gridView.setAdapter(adapter);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}