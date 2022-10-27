package com.example.carcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class Bluetooth extends AppCompatActivity {

    Button btn_turnOn, btn_turnOff, btn_discoverable, btn_connect, btn_logout;
    TextView pairedList;
    ImageView bluetooth;

    BluetoothAdapter bluetoothAdapter;

    DatabaseHelper databaseHelper;

    private static final int Request_Enable = 0;
    private static final int Request_Discover = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        databaseHelper = new DatabaseHelper(this);

        btn_turnOn = (Button) findViewById(R.id.btn_turnOn);
        btn_turnOff = (Button) findViewById(R.id.btn_turnOff);
        btn_discoverable = (Button) findViewById(R.id.btn_discoverable);
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        pairedList = (TextView) findViewById(R.id.pairedList);
        bluetooth = (ImageView) findViewById(R.id.bluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter.isEnabled()){
            bluetooth.setImageResource(R.drawable.ic_bluetooth_connected);
        }
        else{
            bluetooth.setImageResource(R.drawable.ic_bluetooth_disabled);
        }

        btn_turnOn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()) {
                    Toast.makeText(Bluetooth.this, "Włącz bluetooth", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, Request_Enable);
                    bluetooth.setImageResource(R.drawable.ic_bluetooth_connected);
                }
                else{
                    Toast.makeText(Bluetooth.this, "Już włączone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_discoverable.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isDiscovering()){
                    Toast.makeText(Bluetooth.this, "Włącz widoczność", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, Request_Discover);
                }
            }
        });

        btn_turnOff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                    Toast.makeText(Bluetooth.this, "Wyłącz", Toast.LENGTH_SHORT).show();
                    bluetooth.setImageResource((R.drawable.ic_bluetooth_disabled));
                }
                else{
                    Toast.makeText(Bluetooth.this, "Już wyłączone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    pairedList.setText("Lista wszystkich urządzeń");
                    @SuppressLint("MissingPermission") Set<BluetoothDevice> deviceSet = bluetoothAdapter.getBondedDevices();
                    for(BluetoothDevice device : deviceSet){
                        pairedList.append("\nUrządzenie " + device.getName() + " : " + device);
                    }
                }
                else {
                    Toast.makeText(Bluetooth.this, "Włącz bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }
    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}
