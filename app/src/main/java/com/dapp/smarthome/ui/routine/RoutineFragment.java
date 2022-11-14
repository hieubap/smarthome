package com.dapp.smarthome.ui.routine;

import static com.dapp.smarthome.MainActivity.myRef;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dapp.smarthome.MainActivity;
import com.dapp.smarthome.QuizActivity;
import com.dapp.smarthome.R;
import com.dapp.smarthome.databinding.FragmentRoutineBinding;
import com.dapp.smarthome.ui.routine.component.Routine;
import com.dapp.smarthome.ui.routine.component.RoutineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;
    public static int current = 0;
    public TextView numText,questText;
    public DataSnapshot dataQuestion;

    public void next(){
        current = current + 1;
    }

    public void reset(){
        current = 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numText = getView().findViewById(R.id.number_ans);
        questText = getView().findViewById(R.id.question_play_text);

        Button buttonPlay = getView().findViewById(R.id.btn_start);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current == QuizActivity.size){
                    buttonPlay.setText("Start");
                    MainActivity.myRef.child("flag").child("status").setValue("finish");
                }else if(current == 0){
                    MainActivity.myRef.child("flag").child("status").setValue("start");
                    next();
                    MainActivity.myRef.child("flag").child("current").setValue(current);
                }else{
                    MainActivity.myRef.child("flag").child("status").setValue("playing");
                    next();
                    MainActivity.myRef.child("flag").child("current").setValue(current);
                    if(current == QuizActivity.size){
                        buttonPlay.setText("Finish");
                    }
                }

                if(dataQuestion != null){
                    questText.setText(dataQuestion.child(QuizActivity.keyQuiz).child(current+"").child("question").getValue() + "");
                }
            }
        });

        Button resetBtn = getView().findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                MainActivity.myRef.child("flag").child("status").setValue("init");
                MainActivity.myRef.child("flag").child("current").setValue(0);
                MainActivity.myRef.child("answers").removeValue();

            }
        });

        myRef.child("quiz").child(QuizActivity.keyQuiz).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;

                for (DataSnapshot d : snapshot.getChildren()){
                    count++;
                }
                QuizActivity.size = count;
                dataQuestion = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("answers").child(QuizActivity.keyQuiz).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot d : snapshot.child(current+"").getChildren()){
                    count++;
                }
                numText.setText(count+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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