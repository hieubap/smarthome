package com.dapp.smarthome.ui.routine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dapp.smarthome.R;
import com.dapp.smarthome.databinding.FragmentRoutineBinding;
import com.dapp.smarthome.ui.routine.component.Routine;
import com.dapp.smarthome.ui.routine.component.RoutineAdapter;

import java.util.ArrayList;

public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridView;
        ArrayList<Routine> arrayList = new ArrayList<>();
        RoutineAdapter adapter;

        gridView = getView().findViewById(R.id.routine_list_home_card);
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));
        arrayList.add(new Routine("Good morning","7:00 am","Every day",3));

        adapter = new RoutineAdapter(getView().getContext(),R.layout.routine_card, arrayList);
        gridView.setAdapter(adapter);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRoutineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}