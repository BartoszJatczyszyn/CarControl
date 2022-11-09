package com.example.carcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button btn_logout, btn_bluetooth, btn_locks, btn_windows, btn_lights, btn_others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_bluetooth = (Button)findViewById(R.id.btn_bluetooth);
        btn_locks = (Button)findViewById(R.id.btn_locks);
        btn_windows = (Button)findViewById(R.id.btn_windows);
        btn_lights = (Button)findViewById(R.id.btn_lights);
        btn_others = (Button)findViewById(R.id.btn_others);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth();
            }
        });

        btn_windows.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                windows();
            }
        });

        btn_locks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                locks();
            }
        });

        btn_others.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                others();
            }
        });

        btn_lights.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lights();
            }
        });
    }

    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }

    public void bluetooth() {
        Intent intent=new Intent(this, Bluetooth.class);
        startActivity(intent);
    }

    public void windows() {
        Intent intent=new Intent(this, Windows.class);
        startActivity(intent);
    }

    public void locks() {
        Intent intent=new Intent(this, Locks.class);
        startActivity(intent);
    }

    public void others() {
        Intent intent=new Intent(this, Others.class);
        startActivity(intent);
    }

    public void lights() {
        Intent intent=new Intent(this, Lights.class);
        startActivity(intent);
    }

}