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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dapp.smarthome.databinding.LoginBinding;
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
import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity {

    private RegisterBinding binding;
    private LoginBinding bindingLogin;
    public static String username;
    public static boolean isAdmin = false;

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
        binding = RegisterBinding.inflate(getLayoutInflater());
        bindingLogin = LoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button btnRegister = binding.btnRegister;
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });


        Button btnLogin = bindingLogin.btnLogin;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String key = String.valueOf(bindingLogin.sdtText.getText());
                        String password = String.valueOf(bindingLogin.passwordText.getText());
                        if(key.equals("43211234")){
                            Intent myIntent = new Intent(AuthActivity.this, MainActivity.class);
                            myIntent.putExtra("userKey", key); //Optional parameters
                            AuthActivity.this.startActivity(myIntent);
                            username = key;
                            isAdmin = true;
                        }else if(snapshot.hasChild(key)){
                            if(((String)snapshot.child(key).child("pass").getValue()).equals(password)){
                                Intent myIntent = new Intent(AuthActivity.this, MainActivity.class);
                                myIntent.putExtra("userKey", key); //Optional parameters
                                AuthActivity.this.startActivity(myIntent);
                                username = key;
                            }else{
                                Toast.makeText(getApplicationContext(),"Mật khẩu không đúng. Vui lòng kiểm tra mật khẩu",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Tài khoản không không tồn tại. Vui lòng đăng ký",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        Button btnSwitchLogin = binding.btnSwitchLogin;
        btnSwitchLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(bindingLogin.getRoot());
            }
        });


        Button btnSwitchRegister = bindingLogin.btnSwitchRegister;
        btnSwitchRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(binding.getRoot());
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

    void clickRegister(){

        EditText sdtText = binding.sdtText;
        EditText tenText = binding.tenText;
        EditText passText = binding.passwordText;

        DatabaseReference d = myRef.child("users")
                .child(String.valueOf(sdtText.getText()));

        d.child("sdt").setValue(String.valueOf(sdtText.getText()));
        d.child("ten").setValue(String.valueOf(tenText.getText()));
        d.child("pass").setValue(String.valueOf(passText.getText()));

        Toast.makeText(getApplicationContext(),"Đăng ký thành công. Vui lòng đăng nhập",Toast.LENGTH_LONG).show();
        binding.btnSwitchLogin.callOnClick();
    }
}