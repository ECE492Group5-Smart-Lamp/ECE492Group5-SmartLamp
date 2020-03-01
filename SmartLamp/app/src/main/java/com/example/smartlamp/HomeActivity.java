package com.example.smartlamp;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // Name of the connected device
    private String mConnectedDeviceName = null;
    private BluetoothAdapter mBluetoothAdapter = null;

    private static BluetoothSerialService mSerialService = null;

    private static InputMethodManager mInputManager;
    private boolean mEnablingBT;
    private boolean flagBT = false;

    /**
     * The tag we use when logging, so that our messages can be distinguished
     * from other messages in the log. Public because it's used by several
     * classes.
     */
    public static final String LOG_TAG = "BlueTooth";
    // Message types sent from the BluetoothReadService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    /**
     * Set to true to add debugging code and logging.
     */
    public static final boolean DEBUG = true;
    private MenuItem mMenuItemConnect;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    /**
     * Our main view. Displays the emulated terminal screen.
     */
    private View homeView;


    private ImageView lightOff;
    private ImageView lightOn;
    private SeekBar brightnessSeekBar;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            finishDialogNoBluetooth();
            return;
        }

        setContentView(R.layout.activity_home);
        initView();

        homeView = (View) findViewById(R.id.home);
        // initiate  views
        brightnessSeekBar =(SeekBar)findViewById(R.id.seekBar_bright);
        redSeekBar =(SeekBar)findViewById(R.id.seekBar_red);
        greenSeekBar =(SeekBar)findViewById(R.id.seekBar_green);
        blueSeekBar =(SeekBar)findViewById(R.id.seekBar_blue);


        mSerialService = new BluetoothSerialService(this, mHandlerBT, homeView);




        // perform seek bar change listener event used for getting the progress value
        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                progressChangedValue = (int) progressChangedValue/10;
                String str0 = String.valueOf(progressChangedValue);
                String str = "brightness:" + str0;
                byte[] byteArr = new byte[0];
                try {
                    byteArr = str.getBytes("UTF-8");
                    send(byteArr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });


        // perform seek bar change listener event used for getting the progress value
        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String str0 = String.valueOf(progressChangedValue);
                String str = "red:" + str0;
                byte[] byteArr = new byte[0];
                try {
                    byteArr = str.getBytes("UTF-8");
                    send(byteArr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


        // perform seek bar change listener event used for getting the progress value
        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String str0 = String.valueOf(progressChangedValue);
                String str = "green:" + str0;
                byte[] byteArr = new byte[0];
                try {
                    byteArr = str.getBytes("UTF-8");
                    send(byteArr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


        // perform seek bar change listener event used for getting the progress value
        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String str0 = String.valueOf(progressChangedValue);
                String str = "blue:" + str0;
                byte[] byteArr = new byte[0];
                try {
                    byteArr = str.getBytes("UTF-8");
                    send(byteArr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void initView() {
        lightOff = findViewById(R.id.light_off);
        lightOn = findViewById(R.id.light_on);
        ImageView setTimeImg = findViewById(R.id.main_set_time);
        ImageView setBluetooth = findViewById(R.id.set_bluetooth);

        if (lightOff != null && lightOn != null && setTimeImg != null) {
            lightOff.setOnClickListener(this);
            lightOn.setOnClickListener(this);
            setTimeImg.setOnClickListener(this);
            setBluetooth.setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_bluetooth:{
                Log.i("bt", "btclick");

                if (flagBT == false) {
                    // Launch the DeviceListActivity to see devices and do scan
                    Intent serverIntent = new Intent(this, BT_DeviceList.class);
                    flagBT = true;
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                }
                else {
                    flagBT = false;
                    mSerialService.stop();
                    mSerialService.start();

                }
            }

            break;
            case R.id.light_off:
                //if(flagBT == true){
                    lightOff.setVisibility(View.INVISIBLE);
                    lightOn.setVisibility(View.VISIBLE);
                    String str = "off";
                    byte[] byteArr = new byte[0];
                    try {
                        byteArr = str.getBytes("UTF-8");
                        send(byteArr);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
               // }


                break;
            case R.id.light_on:
               // if(flagBT == true){
                    lightOff.setVisibility(View.VISIBLE);
                    lightOn.setVisibility(View.INVISIBLE);
                    String str2 = "on";
                    byte[] byteArr2 = new byte[0];
                    try {
                        byteArr2 = str2.getBytes("UTF-8");
                        send(byteArr2);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                //}

                break;
            case R.id.main_set_time: {
                Log.i("set", "settime");
                Intent intent = new Intent(HomeActivity.this, TimingActivity.class);
                startActivity(intent);
            }

            break;

        }

    }





    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG)
            Log.e(LOG_TAG, "++ ON START ++");

        mEnablingBT = false;
    }
    @Override
    public synchronized void onResume() {
        super.onResume();

        if (DEBUG) {
            Log.e(LOG_TAG, "+ ON RESUME +");
        }

        if (!mEnablingBT) { // If we are turning on the BT we cannot check if it's enable
            if ( (mBluetoothAdapter != null)  && (!mBluetoothAdapter.isEnabled()) ) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.alert_dialog_turn_on_bt)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.alert_dialog_warning_title)
                        .setCancelable( false )
                        .setPositiveButton(R.string.alert_dialog_yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mEnablingBT = true;
                                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //finishDialogNoBluetooth();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

            if (mSerialService != null) {
                // Only if the state is STATE_NONE, do we know that we haven't started already
                if (mSerialService.getState() == BluetoothSerialService.STATE_NONE) {
                    // Start the Bluetooth chat services
                    mSerialService.start();
                }
            }

        }
    }
    @Override
    public synchronized void onPause() {
        super.onPause();
        if (DEBUG)
            Log.e(LOG_TAG, "- ON PAUSE -");

        if (homeView != null) {
            mInputManager.hideSoftInputFromWindow(homeView.getWindowToken(), 0);
            //homeView.onPause();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if(DEBUG)
            Log.e(LOG_TAG, "-- ON STOP --");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG)
            Log.e(LOG_TAG, "--- ON DESTROY ---");

        if (mSerialService != null)
            mSerialService.stop();

    }

    public int getConnectionState() {
        return mSerialService.getState();
    }

    // The Handler that gets information back from the BluetoothService
    private final Handler mHandlerBT = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if(DEBUG) Log.i(LOG_TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothSerialService.STATE_CONNECTED:
                            if (mMenuItemConnect != null) {
                                mMenuItemConnect.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
                                mMenuItemConnect.setTitle(R.string.disconnect);
                            }

                            mInputManager.showSoftInput(homeView, InputMethodManager.SHOW_IMPLICIT);

                            //mTitle.setText( R.string.title_connected_to );
                            //mTitle.append(" " + mConnectedDeviceName);
                            break;

                        case BluetoothSerialService.STATE_CONNECTING:
                            //mTitle.setText(R.string.title_connecting);
                            break;

                        case BluetoothSerialService.STATE_LISTEN:
                        case BluetoothSerialService.STATE_NONE:
                            if (mMenuItemConnect != null) {
                                mMenuItemConnect.setIcon(android.R.drawable.ic_menu_search);
                                mMenuItemConnect.setTitle(R.string.connect);
                            }

                            mInputManager.hideSoftInputFromWindow(homeView.getWindowToken(), 0);

                           // mTitle.setText(R.string.title_not_connected);

                            break;
                    }
                    break;
                case MESSAGE_WRITE:
//                    if (mLocalEcho) {
//                        byte[] writeBuf = (byte[]) msg.obj;
//                        homeView.write(writeBuf, msg.arg1);
//                    }

                    break;
/*
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                mEmulatorView.write(readBuf, msg.arg1);

                break;
*/
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_connected_to) + " "
                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    /*
     * byte[] buffer = new byte[1];
     * buffer[0] = (byte)letter;
     * send( buffer );
     * [//mSerialService.write(buffer)]
     * */
    public void send(byte[] out) {

//        if ( out.length == 1 ) {
//
//            if ( out[0] == 0x0D ) {
//                out = handleEndOfLineChars( mOutgoingEoL_0D );
//            }
//            else {
//                if ( out[0] == 0x0A ) {
//                    out = handleEndOfLineChars( mOutgoingEoL_0A );
//                }
//            }
//        }

        if ( out.length > 0 ) {
            mSerialService.write( out );
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(DEBUG) Log.d(LOG_TAG, "onActivityResult " + resultCode);
        switch (requestCode) {

            case REQUEST_CONNECT_DEVICE:

                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    String address = data.getExtras().getString(BT_DeviceList.EXTRA_DEVICE_ADDRESS);
                    Log.i("hhh", address);
                    // Get the BLuetoothDevice object
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    mSerialService.connect(device);
                }
                break;

            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode != Activity.RESULT_OK) {
                    Log.d(LOG_TAG, "BT not enabled");

                   // finishDialogNoBluetooth();
                }
        }
    }

    public void finishDialogNoBluetooth() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_dialog_no_bt)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.app_name)
                .setCancelable( false )
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
