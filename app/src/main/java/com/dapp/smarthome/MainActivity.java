package com.dapp.smarthome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dapp.smarthome.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static boolean isAnswer = false;

    FirebaseFirestore firestore;
    public static DatabaseReference myRef = FirebaseDatabase
            .getInstance()
            .getReferenceFromUrl("https://smarthome-5df4f-default-rtdb.firebaseio.com/");;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        setActionBar(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_device, R.id.navigation_routine, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        myRef.child("flag").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!AuthActivity.isAdmin && !isAnswer
                        && snapshot.hasChild("status")
                        && ((String)snapshot.child("status").getValue()).equals("start")){
                    Intent myIntent = new Intent(MainActivity.this, QuizActivity.class);
//                    myIntent.putExtra("userKey", ); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                    isAnswer = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    public void firebaseStore(){

        firestore = FirebaseFirestore.getInstance();

        Map<String,Object> user = new HashMap<>();
        user.put("firstName","DEV");
        user.put("lastName","HIEU");
        user.put("timestamp", DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(ZonedDateTime.now()));

        firestore.collection("users").add(user).addOnSuccessListener(
                new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("SUCCESS ..... ...");
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                }
        );

        myRef = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://smarthome-5df4f-default-rtdb.firebaseio.com/");

//        myRef.child("users")
//                .child(DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss").format(ZonedDateTime.now()))
//                .setValue(1);

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("on change ........");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}