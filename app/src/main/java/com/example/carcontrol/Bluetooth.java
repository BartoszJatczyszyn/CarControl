package com.example.carcontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


public class Bluetooth extends AppCompatActivity{

    Button btn_turnOn, btn_turnOff, btn_discoverable, btn_search, btn_logout, btn_disconnect;
    TextView txt_name;
    ListView pairedList;
    ImageView bluetooth;

    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;

    private static final int Request_Enable = 0;
    private static final int Request_Discover = 1;
    String address = null;
    String info;
    BluetoothDevice device;
    public static BluetoothSocket socket;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btn_turnOn = (Button) findViewById(R.id.btn_turnOn);
        btn_turnOff = (Button) findViewById(R.id.btn_turnOff);
        btn_discoverable = (Button) findViewById(R.id.btn_discoverable);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_disconnect = (Button) findViewById(R.id.btn_disconnect);
        pairedList = (ListView) findViewById(R.id.pairedList);
        bluetooth = (ImageView) findViewById(R.id.bluetooth);
        txt_name = (TextView) findViewById(R.id.txt_name);

        txt_name.setText(getLocalBluetoothName());

        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        if(bluetoothAdapter.isEnabled()){
            bluetooth.setImageResource(R.drawable.ic_baseline_bluetooth);
        }
        else{
            bluetooth.setImageResource(R.drawable.ic_bluetooth_disabled);
        }

        btn_turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, Request_Enable);
                    bluetooth.setImageResource(R.drawable.ic_baseline_bluetooth);
                    pairedList.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(Bluetooth.this, "Już włączone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_discoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isDiscovering()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, Request_Discover);
                }
            }
        });

        btn_turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                    bluetooth.setImageResource((R.drawable.ic_bluetooth_disabled));
                    pairedList.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(Bluetooth.this, "Już wyłączone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetooth.setImageResource(R.drawable.ic_bluetooth_searching);
                    pairedList.setVisibility(View.VISIBLE);
                    list();
                }
                else {
                    Toast.makeText(Bluetooth.this, "Włącz bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(socket.isConnected()) {
                    Disconnect();
                } else {
                    Toast.makeText(Bluetooth.this, "Nie jesteś połączony", Toast.LENGTH_SHORT).show();
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

    private void list(){
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list= new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice deviceFromList : pairedDevices){
                list.add(deviceFromList.getName() + "\n" + deviceFromList.getAddress());
            }
        } else{
            Toast.makeText(Bluetooth.this, "Nie znaleziono żadnego urządzenia", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        pairedList.setAdapter(adapter);

        pairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {
                info = ((TextView) view).getText().toString();
                address = info.substring(info.length() - 17);
                device = bluetoothAdapter.getRemoteDevice(address);
                int counter =0;
                do {
                    try {
                        socket = device.createRfcommSocketToServiceRecord(myUUID);
                        System.out.println(socket);
                        socket.connect();
                        bluetooth.setImageResource((R.drawable.ic_bluetooth_connected));
                        Toast.makeText(Bluetooth.this, "Połączono z " + info.substring(0, info.length() - 18), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    counter++;
                } while (!socket.isConnected() && counter<3);
            }
        });
    }

    private void Disconnect()
    {
        if (socket!=null) {
            try {
                socket.close();
                bluetooth.setImageResource(R.drawable.ic_bluetooth_searching);
                Toast.makeText(Bluetooth.this, "Rozłączono z " + info.substring(0, info.length() - 18), Toast.LENGTH_SHORT).show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String getLocalBluetoothName(){
        if(bluetoothAdapter==null){
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        String name = bluetoothAdapter.getName();
        if(name==null){
            name = bluetoothAdapter.getAddress();
        }
        return name;
    }

    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}
