package com.dapp.smarthome.research;

import static com.dapp.smarthome.MainActivity.myRef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dapp.smarthome.AuthActivity;
import com.dapp.smarthome.databinding.InputInfoLayoutBinding;
import com.google.firebase.database.DatabaseReference;

public class InputInfoActivity extends AppCompatActivity {
    private InputInfoLayoutBinding inputInfoLayoutBinding;
    public static int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        index = savedInstanceState.getInt("location");


        inputInfoLayoutBinding = InputInfoLayoutBinding.inflate(getLayoutInflater());
        setContentView(inputInfoLayoutBinding.getRoot());

        Button btn = inputInfoLayoutBinding.save;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fullname = inputInfoLayoutBinding.fullname;
                EditText name = inputInfoLayoutBinding.name;
                EditText mssv = inputInfoLayoutBinding.mssv;

                DatabaseReference d = myRef.child("student")
                        .child(index + "");

                d.child("fullname").setValue(String.valueOf(fullname.getText()));
                d.child("name").setValue(String.valueOf(name.getText()));
                d.child("mssv").setValue(String.valueOf(mssv.getText()));

                Toast.makeText(getApplicationContext(),"Cập nhật thông tin thành công",Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(InputInfoActivity.this, DiagramClassActivity.class);
                startActivity(myIntent);

            }
        });

    }


}