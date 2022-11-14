package com.dapp.smarthome.ui.home;

import static com.dapp.smarthome.MainActivity.myRef;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    GridView gridView;
    RoomAdapter adapter;

    void onChangeData(DataSnapshot snapshot){
        ArrayList<Room> arrayList = new ArrayList<>();

        for(DataSnapshot d : snapshot.getChildren()){
            arrayList.add(new Room(R.drawable.ic_living_room,(String)d.child("sdt").getValue(),(String)d.child("ten").getValue()));

        }

        adapter.setArr(arrayList);
        if(getView() != null && getView().getContext() != null){
            adapter = new RoomAdapter(getView().getContext(),R.layout.home_card, arrayList);
            gridView.setAdapter(adapter);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Room> arrayList = new ArrayList<>();

        gridView = getView().findViewById(R.id.home_list_home_card);

        adapter = new RoomAdapter(getView().getContext(),R.layout.home_card, arrayList);
        gridView.setAdapter(adapter);

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onChangeData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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