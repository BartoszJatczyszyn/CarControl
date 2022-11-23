package com.example.carcontrol;


import static com.example.carcontrol.Bluetooth.socket;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class Lights extends AppCompatActivity {


    Button btn_logout, btn_light_long, btn_light_short, btn_light_triangle, btn_light_parking, btn_left_direction, btn_right_direction;
    ImageView lights_front, lights_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_light_long = (Button)findViewById(R.id.btn_light_long);
        btn_light_short = (Button)findViewById(R.id.btn_light_short);
        btn_light_triangle = (Button)findViewById(R.id.btn_light_triangle);
        btn_light_parking = (Button)findViewById(R.id.btn_light_parking);
        btn_left_direction = (Button)findViewById(R.id.btn_left_direction);
        btn_right_direction = (Button)findViewById(R.id.btn_right_direction);
        lights_front = (ImageView)findViewById(R.id.lights_front);
        lights_back= (ImageView)findViewById(R.id.lights_back);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btn_light_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    socket.getOutputStream().write(1);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        btn_light_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    socket.getOutputStream().write(0);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        btn_light_triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_light_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_left_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_right_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}