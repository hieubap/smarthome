package com.dapp.smarthome.ui.device;

import static com.dapp.smarthome.MainActivity.myRef;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dapp.smarthome.QuizActivity;
import com.dapp.smarthome.R;
import com.dapp.smarthome.adapter.Score;
import com.dapp.smarthome.adapter.ScoreAdapter;
import com.dapp.smarthome.databinding.AdminBinding;
import com.dapp.smarthome.ui.home.component.Room;
import com.dapp.smarthome.ui.home.component.RoomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeviceFragment extends Fragment {

    private AdminBinding binding;
    ListView listViewRank;
    ScoreAdapter adapter;
    ArrayList<Score> arrayList;

    void clickRegister(){

        EditText sdtText = getView().findViewById(R.id.sdt_text);
        EditText tenText = getView().findViewById(R.id.ten_text);
        EditText passText = getView().findViewById(R.id.password_text);

        DatabaseReference d = myRef.child("users")
                .child(String.valueOf(sdtText.getText()));

        d.child("sdt").setValue(String.valueOf(sdtText.getText()));
        d.child("ten").setValue(String.valueOf(tenText.getText()));
        d.child("pass").setValue(String.valueOf(passText.getText()));

    }

    void onChangeData(DataSnapshot snapshot){
        ArrayList<Score> arrayList = new ArrayList<>();

        for(DataSnapshot d : snapshot.getChildren()){
            arrayList.add(new Score( (String)d.child("sdt").getValue(),(String)d.child("ten").getValue(),0));

        }
        this.arrayList = arrayList;

        adapter.setList(arrayList);
        if(getView() != null && getView().getContext() != null){
            adapter = new ScoreAdapter(getView().getContext(),R.layout.score_card, arrayList);
            listViewRank.setAdapter(adapter);
        }
    }


    void onChangeDataScore(DataSnapshot snapshot){
        for(Score s : this.arrayList){
            s.setPoint(0);
            for(DataSnapshot d : snapshot.getChildren()){
                if(d.child(s.getSdt()).getValue() != null && d.child(s.getSdt()).child("point").getValue() != null){
                    s.setPoint(s.getPointInt() + (Long)d.child(s.getSdt()).child("point").getValue() );
                }
            }
        }

        arrayList.sort((a,b) -> (int) (b.getPointInt() - a.getPointInt()));
        adapter.setList(arrayList);
        if(getView() != null && getView().getContext() != null){
            adapter = new ScoreAdapter(getView().getContext(),R.layout.score_card, arrayList);
            listViewRank.setAdapter(adapter);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Button btnRegister = getView().findViewById(R.id.btn_register);
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickRegister();
//            }
//        });


        arrayList = new ArrayList<>();
        listViewRank = getView().findViewById(R.id.list_rank);

        adapter = new ScoreAdapter(getView().getContext(),R.layout.score_card, arrayList);
        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onChangeData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

                myRef.child("answers").child(QuizActivity.keyQuiz).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapShotAnswer) {
                        onChangeDataScore(snapShotAnswer);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


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
        binding = AdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}