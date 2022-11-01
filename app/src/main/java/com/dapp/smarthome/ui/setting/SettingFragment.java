package com.dapp.smarthome.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dapp.smarthome.R;
import com.dapp.smarthome.databinding.FragmentSettingBinding;
import com.dapp.smarthome.ui.setting.component.SettingAdapter;
import com.dapp.smarthome.ui.setting.component.SettingItem;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = getView().findViewById(R.id.setting_list_item);

        ArrayList<SettingItem> arrayList = new ArrayList<>();
        arrayList.add(new SettingItem("My Home",""));
        arrayList.add(new SettingItem("Message",""));
        arrayList.add(new SettingItem("Family Access",""));
        arrayList.add(new SettingItem("Change Password",""));
        arrayList.add(new SettingItem("Support",""));
        arrayList.add(new SettingItem("Sign out",""));

        listView.setAdapter(new SettingAdapter(getView().getContext(),R.layout.setting_item,arrayList));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}