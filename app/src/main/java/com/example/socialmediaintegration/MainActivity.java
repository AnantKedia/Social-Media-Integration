package com.example.socialmediaintegration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;



public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    TextView login;
    private static final String EMAIL = "email";


    TextView mLoginButton;

    private TextView userText;
    private TextView statusText;
    private ImageView imageView;


TextView tweet,fbpost;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        fbpost=findViewById(R.id.login_button123);
        fbpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,FBmain.class);
                startActivity(i);
            }
        });

        tweet=findViewById(R.id.twitter123);
        tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,TwitterMain.class);
                startActivity(i);
            }
        });



    }



}
