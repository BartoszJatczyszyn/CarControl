package com.example.carcontrol;

import static com.example.carcontrol.Bluetooth.socket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class OnBoardComputer extends AppCompatActivity {
    Button btn_logout, btn_up, btn_down, btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardcomputer);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_up = (Button)findViewById(R.id.btn_up);
        btn_down = (Button)findViewById(R.id.btn_down);
        btn_ok = (Button)findViewById(R.id.btn_ok);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str = "518 3 8 131 255";
                    socket.getOutputStream().write(str.getBytes("US-ASCII"));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str = "518 3 8 131 1";
                    socket.getOutputStream().write(str.getBytes("US-ASCII"));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str = "518 3 1 132 0";
                    socket.getOutputStream().write(str.getBytes("US-ASCII"));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void logout() {
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}
