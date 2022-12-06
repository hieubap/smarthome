package com.dapp.smarthome.research;

import static com.dapp.smarthome.MainActivity.myRef;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.dapp.smarthome.AuthActivity;
import com.dapp.smarthome.MainActivity;
import com.dapp.smarthome.QuizActivity;
import com.dapp.smarthome.R;
import com.dapp.smarthome.adapter.Score;
import com.dapp.smarthome.databinding.DiagramClassLayoutBinding;
import com.dapp.smarthome.databinding.VideoLayoutBinding;
import com.dapp.smarthome.ui.home.component.Room;
import com.dapp.smarthome.ui.home.component.RoomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiagramClassActivity extends AppCompatActivity {
    public static int W_BTN = 100;
    public static int H_BTN = 200;
    public static boolean THAY_TUAN = false;

    private DiagramClassLayoutBinding diagramClassLayoutBinding;
    private List<Boolean> booleans;
    private List<TextView> textViews;
    private List<Button> buttonList;
    private List<ImageView> imageViews;
//    private ImageView doremonImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        booleans = new ArrayList<>();
        textViews = new ArrayList<>();
        imageViews = new ArrayList<>();
        buttonList = new ArrayList<>();

        for (int i = 0;i< 30;i++){
            booleans.add(Boolean.FALSE);
        }

        diagramClassLayoutBinding = DiagramClassLayoutBinding.inflate(getLayoutInflater());
        setContentView(diagramClassLayoutBinding.getRoot());

        Button button = diagramClassLayoutBinding.loginAdmin;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = diagramClassLayoutBinding.adminCodeInput;
//                System.out.println(editText.getText()=="1234");
                if((editText.getText().toString()).equals("0934660554") && !THAY_TUAN){
                    THAY_TUAN = true;
                    diagramClassLayoutBinding.title.setText("!!! Chào mừng thầy Tuấn !!!");
                    button.setText("USER");
                }else{
                    THAY_TUAN = false;
                    diagramClassLayoutBinding.title.setText("Sơ đồ lớp ET4710 - 137364");
                    button.setText("ADMIN");
                }

            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        W_BTN = (width - 10) / 6;


        for (int i = 0;i<30;i++){
            ImageView doremonImage = new ImageView(diagramClassLayoutBinding.container.getContext());
            doremonImage.setZ(10);
            doremonImage.setImageResource(R.drawable.doraemon_walk);

            diagramClassLayoutBinding.container.addView(doremonImage);

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) doremonImage.getLayoutParams();
            params.topToTop = diagramClassLayoutBinding.constraintLayoutDiagram.getId();
            params.leftToLeft = diagramClassLayoutBinding.container.getId();
            params.topMargin = -150;

            doremonImage.setLayoutParams(params);
            doremonImage.getLayoutParams().width = W_BTN;
            doremonImage.getLayoutParams().height = W_BTN;

            imageViews.add(doremonImage);
//            diagramClassLayoutBinding.constraintLayoutDiagram.addView(doremonImage);
        }

//        doremonImg = diagramClassLayoutBinding.imgDoremon;
//        doremonImg.setZ(10);

        for(int j=0;j<5;j++){
            for(int i=0;i<6;i++){
                Button btn = new Button(diagramClassLayoutBinding.constraintLayoutDiagram.getContext());
                btn.setZ(1);
                diagramClassLayoutBinding.constraintLayoutDiagram.addView(btn);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) btn.getLayoutParams();
                params.topMargin = j*(H_BTN + 90) + 55;
                params.topToTop = diagramClassLayoutBinding.constraintLayoutDiagram.getId();
                params.width = W_BTN;
                params.height = H_BTN;

                TextView name = new TextView(diagramClassLayoutBinding.constraintLayoutDiagram.getContext());
//                name.setText((char)('A'+j) + "" + (i+1));
                name.setTextColor(0xffaaaaaa);
                name.setTextSize(16);
                name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                diagramClassLayoutBinding.constraintLayoutDiagram.addView(name);
                textViews.add(name);

                ConstraintLayout.LayoutParams paramName = (ConstraintLayout.LayoutParams) name.getLayoutParams();
                paramName.topMargin = j*(H_BTN + 90);
                paramName.topToTop = diagramClassLayoutBinding.constraintLayoutDiagram.getId();
                paramName.width = W_BTN;

                if(i<3){
                    params.leftMargin = i*(W_BTN + 3);
                    params.leftToLeft = diagramClassLayoutBinding.constraintLayoutDiagram.getId();

                    paramName.leftMargin = i*(W_BTN + 3);
                    paramName.leftToLeft = diagramClassLayoutBinding.constraintLayoutDiagram.getId();
                }else{
                    params.rightMargin = (5-i)*W_BTN + 5*(5-i);
                    params.rightToRight = diagramClassLayoutBinding.constraintLayoutDiagram.getId();

                    paramName.rightMargin = (5-i)*W_BTN + 5*(5-i);
                    paramName.rightToRight = diagramClassLayoutBinding.constraintLayoutDiagram.getId();
                }
                btn.setLayoutParams(params);
                buttonList.add(btn);
                name.setLayoutParams(paramName);
                int finalJ = j;
                int finalI = i;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(THAY_TUAN){
                            if(textViews.get(finalJ*6 + finalI).getText() != null && !textViews.get(finalJ*6 + finalI).getText().equals("")){
                                if(booleans.get(finalJ*6 + finalI).equals(Boolean.TRUE)){
                                    checkOut(finalI,finalJ);
                                }else{
                                    checkIn(finalI,finalJ);
                                }
                            }
                        }else{
                            Intent infoIntent = new Intent(DiagramClassActivity.this, InputInfoActivity.class);
                            InputInfoActivity.index = finalJ*6+finalI;
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("location",finalJ*6+finalI);
//                            infoIntent.putExtras(bundle);
                            startActivity(infoIntent);
                        }

                    }
                });

            }
        }

        myRef.child("student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    System.out.println("student");
                    for(DataSnapshot d : snapshot.getChildren()){
                        TextView text = textViews.get(Integer.valueOf(d.getKey()));
                        text.setText((String)d.child("name").getValue());
                        text.setTextColor(0xff000000);
                        Button btn = buttonList.get(Integer.valueOf(d.getKey()));
                        btn.setBackgroundColor(0xffD30000);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("checkIn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    System.out.println("student");
                    for(DataSnapshot d : snapshot.getChildren()){
                        int index = Integer.valueOf(d.getKey());
                        System.out.println(index + ": " + booleans.get(index));
                        if((Boolean) d.getValue()){
                            booleans.set(index,Boolean.TRUE);
                            checkIn(index%6,index/6);
                        }else{
                            booleans.set(index,Boolean.FALSE);
                            checkOut(index%6,index/6);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void checkIn(int finalI, int finalJ){
        DatabaseReference d = myRef.child("checkIn")
                .child((finalJ*6 + finalI) + "");
        d.setValue(true);
        booleans.set(finalJ*6 + finalI, Boolean.TRUE);
        ImageView selectImage = imageViews.get(finalJ*6 + finalI);
        Button selectBtn = buttonList.get(finalJ*6 + finalI);
        selectBtn.setBackgroundColor(0xff3cb043);

        SpringAnimation springAnimX = new SpringAnimation(selectImage, DynamicAnimation.TRANSLATION_X,0);
        SpringAnimation springAnimY = new SpringAnimation(selectImage, DynamicAnimation.TRANSLATION_Y,0);
        springAnimX.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimX.getSpring().setFinalPosition((float)(finalI *W_BTN + 30 + (finalI < 3 ? -30 : 0)));
        springAnimX.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY);

        springAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimY.getSpring().setFinalPosition((float)(finalJ *(H_BTN + 90) + 200));
        springAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY);

        springAnimX.start();
        springAnimY.start();
    }

    public void checkOut(int finalI, int finalJ){
        DatabaseReference d = myRef.child("checkIn")
                .child((finalJ*6 + finalI) + "");
        d.setValue(false);

        booleans.set(finalJ*6 + finalI, Boolean.FALSE);
        ImageView selectImage = imageViews.get(finalJ*6 + finalI);
        Button selectBtn = buttonList.get(finalJ*6 + finalI);
        selectBtn.setBackgroundColor(0xffD30000);

        SpringAnimation springAnimX = new SpringAnimation(selectImage, DynamicAnimation.TRANSLATION_X,0);
        SpringAnimation springAnimY = new SpringAnimation(selectImage, DynamicAnimation.TRANSLATION_Y,0);
        springAnimX.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimX.getSpring().setFinalPosition(0);
        springAnimX.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY);

        springAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimY.getSpring().setFinalPosition(0);
        springAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY);

        springAnimX.start();
        springAnimY.start();
    }

}