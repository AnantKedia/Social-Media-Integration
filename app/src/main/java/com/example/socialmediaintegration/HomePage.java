package com.example.socialmediaintegration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

        TextView uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String username=getIntent().getStringExtra("username");

            uname=findViewById(R.id.usernamet);
            uname.setText(username);
    }
}
