package com.dapp.smarthome;

import static com.dapp.smarthome.MainActivity.myRef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dapp.smarthome.databinding.LoginBinding;
import com.dapp.smarthome.databinding.QuizBinding;
import com.dapp.smarthome.databinding.RegisterBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private QuizBinding binding;
    private TextView questionText;
    private Button btn1,btn2,btn3,btn4;
    public static String keyQuiz = "0002";
    public static String keyQuestion = "0";
    private List<Button> listBtn = new ArrayList<>();
    private boolean isClick = false;
    public static int size = 100;
    int[] color = new int[]{0xffff3758,0xff3793df,0xffffc108,0xff66be38};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        binding = QuizBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.child("flag").hasChild("current")
                        && !keyQuestion.equals(""+snapshot.child("flag").child("current").getValue())){
                    keyQuestion = snapshot.child("flag").child("current").getValue().toString();

                    for(int i=0;i<4;i++){
                        listBtn.get(i).setBackgroundColor(color[i]);
                    }
                    btn1.setText(""+snapshot.child("quiz").child(keyQuiz).child(keyQuestion).child("A").getValue());
                    btn2.setText(""+snapshot.child("quiz").child(keyQuiz).child(keyQuestion).child("B").getValue());
                    btn3.setText(""+snapshot.child("quiz").child(keyQuiz).child(keyQuestion).child("C").getValue());
                    btn4.setText(""+snapshot.child("quiz").child(keyQuiz).child(keyQuestion).child("D").getValue());
                    questionText.setText((String)snapshot.child("quiz").child(keyQuiz).child(keyQuestion).child("question").getValue());
                    isClick = false;
                }
                if(MainActivity.isAnswer && "init".equals(""+snapshot.child("flag").child("status").getValue())){
                    Intent myIntent = new Intent(QuizActivity.this, MainActivity.class);
                    QuizActivity.this.startActivity(myIntent);
                    MainActivity.isAnswer = false;
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        questionText = binding.questionText;
        btn1 = binding.buttonAnswer1;
        btn2 = binding.buttonAnswer2;
        btn3 = binding.buttonAnswer3;
        btn4 = binding.buttonAnswer4;
        listBtn.add(btn1);
        listBtn.add(btn2);
        listBtn.add(btn3);
        listBtn.add(btn4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(4);
            }
        });
    }

    private void clickAnswer(int number){
        if(isClick) return;
        isClick = true;
        String ans = ""+(char)('A'+number-1);

        myRef.child("quiz").child(keyQuiz).child(keyQuestion).child("answer").addListenerForSingleValueEvent(
                new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DatabaseReference d = myRef.child("answers").child(keyQuiz).child(keyQuestion);

                    myRef.child("answers").child(keyQuiz).child(keyQuestion).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    d.child(AuthActivity.username).child("ans").setValue(ans);

                                    if(ans.equals(snapshot.getValue())) {
                                        d.child(AuthActivity.username).child("point").setValue(100 - snapshot.getChildrenCount());
                                    } else {
                                        d.child(AuthActivity.username).child("point").setValue(0);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            }
                    );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for(int i=0;i<4;i++){
            if(i != number-1){
                listBtn.get(i).setBackgroundColor(0xff888888);
            }
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
//
//    void clickRegister(){
//
//        EditText sdtText = binding.sdtText;
//        EditText tenText = binding.tenText;
//        EditText passText = binding.passwordText;
//
//        DatabaseReference d = myRef.child("users")
//                .child(String.valueOf(sdtText.getText()));
//
//        d.child("sdt").setValue(String.valueOf(sdtText.getText()));
//        d.child("ten").setValue(String.valueOf(tenText.getText()));
//        d.child("pass").setValue(String.valueOf(passText.getText()));
//
//        Toast.makeText(getApplicationContext(),"Đăng ký thành công. Vui lòng đăng nhập",Toast.LENGTH_LONG).show();
//        binding.btnSwitchLogin.callOnClick();
//    }
}