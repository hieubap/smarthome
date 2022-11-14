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

import com.dapp.smarthome.AuthActivity;
import com.dapp.smarthome.MainActivity;
import com.dapp.smarthome.QuizActivity;
import com.dapp.smarthome.R;
import com.dapp.smarthome.dao.QuizDAO;
import com.dapp.smarthome.databinding.FragmentRoutineBinding;
import com.dapp.smarthome.ui.routine.component.Routine;
import com.dapp.smarthome.ui.routine.component.RoutineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;
    public static int current = 0;
    public TextView numText,questText,correctText, ansText;
    public DataSnapshot dataQuestion;
    public static String dapAn = "Đáp án", questStr = "Câu hỏi";
    public static int correctNum = 0,numAnsInt = 0,trueColor = 0xffffffff;
    public List<QuizDAO> quizDAOS = new ArrayList<>();

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
        numText.setText(numAnsInt+"");
        questText = getView().findViewById(R.id.question_play_text);
        questText.setText(questStr);
        correctText = getView().findViewById(R.id.correct_text);
        correctText.setText(correctNum+"");
        ansText = getView().findViewById(R.id.answer_text);
        ansText.setText(dapAn);
        ansText.setBackgroundColor(trueColor);

        Button buttonPlay = getView().findViewById(R.id.btn_start);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current == QuizActivity.size){
                    buttonPlay.setText("Start");
                    MainActivity.myRef.child("flag").child("status").setValue("finish");
                    return;
                }else if(current == 0){
                    MainActivity.myRef.child("flag").child("status").setValue("start");
                }else{
                    MainActivity.myRef.child("flag").child("status").setValue("playing");
                    if(current == QuizActivity.size){
                        buttonPlay.setText("Finish");
                    }
                }

                next();
                questStr = quizDAOS.get(current-1).getQuestion();
                correctNum = 0;
                dapAn = "Đáp án";
                trueColor = 0xffffffff;

                numText.setText("0");
                questText.setText(questStr);
                ansText.setText("Đáp án");
                ansText.setBackgroundColor(trueColor);
                correctText.setText("Đúng");
                MainActivity.myRef.child("flag").child("current").setValue(current);
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

        Button ansBtn = getView().findViewById(R.id.answer_btn);
        ansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dapAn = quizDAOS.get(current-1).getAns();
                trueColor = QuizActivity.color[dapAn.charAt(0) - 'A'];

                correctText.setText(correctNum+"");
                ansText.setText(dapAn);
                ansText.setBackgroundColor(trueColor);
            }
        });

        myRef.child("quiz").child(QuizActivity.keyQuiz).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;

                for (DataSnapshot d : snapshot.getChildren()){
                    count++;
                    QuizDAO q = new QuizDAO(count+"", d);
                    quizDAOS.add(q);
                }
                QuizActivity.size = count;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("answers").child(QuizActivity.keyQuiz).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numAnsInt = 0;
                correctNum = 0;
                for (DataSnapshot d : snapshot.child(current+"").getChildren()){
                    numAnsInt++;
                    if(!(d.child("point").getValue()+"").equals("0")){
                        correctNum++;
                    }
                }
                numText.setText(numAnsInt+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(!AuthActivity.isAdmin){
            ansBtn.setVisibility(View.INVISIBLE);
            buttonPlay.setVisibility(View.INVISIBLE);
            resetBtn.setVisibility(View.INVISIBLE);
            questText.setText("Bạn không phải là admin nên không có quyền bắt đầu trò chơi");
        }
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