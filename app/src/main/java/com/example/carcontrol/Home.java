package com.example.carcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button btn_logout, btn_bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseHelper = new DatabaseHelper(this);
        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_bluetooth = (Button)findViewById(R.id.btn_bluetooth);

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
    }

    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }

    public void bluetooth() {
        Intent intent=new Intent(this, Bluetooth.class);
        startActivity(intent);
    }

}