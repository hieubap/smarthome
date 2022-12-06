package com.dapp.smarthome.research;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.dapp.smarthome.databinding.VideoLayoutBinding;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VideoActivity extends AppCompatActivity {

    private VideoLayoutBinding videoLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("..... "+System.getProperty("os.version"));
        System.out.println(Build.DEVICE);

        videoLayoutBinding = VideoLayoutBinding.inflate(getLayoutInflater());
        setContentView(videoLayoutBinding.getRoot());


//        Button b = R.id.button_play_youtube;
        Button buttonPlay = videoLayoutBinding.buttonPlayYoutube;
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = videoLayoutBinding.inputUri;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(input.getText().toString()));
                startActivity(intent);

            }
        });
    }


}