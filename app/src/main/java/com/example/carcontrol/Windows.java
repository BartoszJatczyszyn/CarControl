package com.example.carcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Windows extends AppCompatActivity {

    Button btn_logout, btn_front_left_up, btn_front_left_down, btn_front_right_up,btn_front_right_down, btn_back_left_up, btn_back_left_down, btn_back_right_up, btn_back_right_down ;
    ImageView car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windows);

        btn_logout= (Button)findViewById(R.id.btn_logout);
        btn_front_left_up = (Button)findViewById(R.id.btn_front_left_up);
        btn_front_left_down = (Button)findViewById(R.id.btn_front_left_down);
        btn_front_right_up = (Button)findViewById(R.id.btn_front_right_up);
        btn_front_right_down = (Button)findViewById(R.id.btn_front_right_down);
        btn_back_left_up = (Button)findViewById(R.id.btn_back_left_up);
        btn_back_left_down = (Button)findViewById(R.id.btn_back_left_down);
        btn_back_right_up = (Button)findViewById(R.id.btn_back_right_up);
        btn_back_right_down = (Button)findViewById(R.id.btn_back_right_down);
        car = (ImageView) findViewById(R.id.car);

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
