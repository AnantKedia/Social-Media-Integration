package com.example.socialmediaintegration;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;



public class FBmain extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView tw1,tw2,tw3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.fbmain);

        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");

        callbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Success
                String userId=loginResult.getAccessToken().getUserId();

                GraphRequest graphRequest=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);
                    }
                });


                Bundle parameters=new Bundle();
                parameters.putString("fields","first_name,last_name,email,id");

                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                //Failure
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                //Failure+Error
                Toast.makeText(getApplicationContext(),"Error"+error.fillInStackTrace(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void displayUserInfo(JSONObject object) {

        String first_name,last_name,email;
        first_name="";
        last_name="";
        email="";

        try {
            first_name=object.getString("first_name");
            last_name=object.getString("last_name");
            email=object.getString("email");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        tw1=findViewById(R.id.tv_name);
        tw2=findViewById(R.id.tv_email);


        tw1.setText(first_name+" "+last_name);
        tw2.setText(email);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
