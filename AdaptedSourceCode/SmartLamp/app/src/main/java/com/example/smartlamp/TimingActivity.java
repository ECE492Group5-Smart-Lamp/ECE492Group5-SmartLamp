package com.example.smartlamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


public class TimingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView startAPM;
    private TextView endAPM;
    private TextView startTimeTv;
    private TextView endTimeTv;
    private Boolean switchOpen = false;
    private String[] time = {"0", "0", "0", "0"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        initView();
    }

    private void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("timing", MODE_PRIVATE);

        ImageView back = findViewById(R.id.setting_back);
        TextView save = findViewById(R.id.save);
        Switch aSwitch = findViewById(R.id.switch_timing);
        startAPM = findViewById(R.id.start_apm);
        endAPM = findViewById(R.id.end_apm);
        startTimeTv = findViewById(R.id.start_time_tv);
        endTimeTv = findViewById(R.id.end_time_tv);
        LinearLayout startTime = findViewById(R.id.start_time);
        LinearLayout endTime = findViewById(R.id.end_time);

        if (back == null || save == null || startTime == null || endTime == null || aSwitch == null) {
            return;
        }

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);

        if (sharedPreferences.getBoolean("checkState", false)) {
            aSwitch.setChecked(true);
            switchOpen = true;
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchOpen = isChecked;
            }
        });


        setStartTimeTv(Integer.parseInt(sharedPreferences.getString("startH", "00")), Integer.parseInt(sharedPreferences.getString("startM", "00")));
        setEndTimeTv(Integer.parseInt(sharedPreferences.getString("endH", "00")), Integer.parseInt(sharedPreferences.getString("endM", "00")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_back: {

            }
            break;

            case R.id.save: {

            }
            break;

            case R.id.start_time: {
                showStartTimePickerDialog();
            }
            break;

            case R.id.end_time: {
                showEndTimePickerDialog();
            }
            break;
        }

    }

    private void showStartTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setStartTimeTv(hourOfDay, minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }


    private void showEndTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                setEndTimeTv(hourOfDay, minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

    }

    private void setStartTimeTv(int hourOfDay, int minute) {

        if (hourOfDay >= 12) {
            startAPM.setText("PM");
        } else {
            startAPM.setText("AM");
        }

        String newH = null;
        String newM = null;
        if (hourOfDay < 10) {
            newH = "0" + hourOfDay;
        } else {
            newH = hourOfDay + "";
        }
        if (minute < 10) {
            newM = "0" + minute;
        } else {
            newM = minute + "";
        }

        String tvH = null;
        if (hourOfDay > 12) {
            tvH = (hourOfDay - 12) + "";
            if ((hourOfDay - 12) < 10) {
                tvH = "0" + (hourOfDay - 12) + "";
            }

        } else {
            tvH = (hourOfDay) + "";
            if (hourOfDay < 10) {
                tvH = "0" + (hourOfDay) + "";
            }
        }

        startTimeTv.setText(tvH + ":" + newM);
        time[0] = newH;
        time[1] = newM;
    }


    private void setEndTimeTv(int hourOfDay, int minute) {
        if (hourOfDay >= 12) {
            endAPM.setText("PM");
        } else {
            endAPM.setText("AM");
        }

        String newH = null;
        String newM = null;
        if (hourOfDay < 10) {
            newH = "0" + hourOfDay;
        } else {
            newH = hourOfDay + "";
        }
        if (minute < 10) {
            newM = "0" + minute;
        } else {
            newM = minute + "";
        }

        String tvH = null;
        if (hourOfDay > 12) {
            tvH = (hourOfDay - 12) + "";
            if ((hourOfDay - 12) < 10) {
                tvH = "0" + (hourOfDay - 12) + "";
            }

        } else {
            tvH = (hourOfDay) + "";
            if (hourOfDay < 10) {
                tvH = "0" + (hourOfDay) + "";
            }
        }

        endTimeTv.setText(tvH + ":" + newM);
        time[2] = newH;
        time[3] = newM;
    }


}