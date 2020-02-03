package com.example.smartlamp;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView lightOff;
    private ImageView lightOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initTab();
        initView();
    }



    private void initView() {
        lightOff = findViewById(R.id.light_off);
        lightOn = findViewById(R.id.light_on);
        ImageView setImg = findViewById(R.id.main_setting);
        ImageView setTimeImg = findViewById(R.id.main_set_time);

        if (lightOff != null && lightOn != null && setImg != null && setTimeImg != null) {
            lightOff.setOnClickListener(this);
            lightOn.setOnClickListener(this);
            setImg.setOnClickListener(this);
            setTimeImg.setOnClickListener(this);
        }

    }

    private void initTab() {
        final List<Fragment> fragments = new ArrayList<>();


        fragments.add(new BrightnessFragment());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_off:
                lightOff.setVisibility(View.INVISIBLE);
                lightOn.setVisibility(View.VISIBLE);

                break;
            case R.id.light_on:
                lightOff.setVisibility(View.VISIBLE);
                lightOn.setVisibility(View.INVISIBLE);

                break;
            case R.id.main_setting: {

            }

            break;
            case R.id.main_set_time: {
                Intent intent = new Intent(HomeActivity.this, TimingActivity.class);
                startActivity(intent);
            }
            break;
        }

    }

}
