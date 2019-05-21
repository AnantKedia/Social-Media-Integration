package com.example.socialmediaintegration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class TwitterMain extends AppCompatActivity {


    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.twitter_main);

        twitterLoginButton=findViewById(R.id.twitter);

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                    //success
                    TwitterSession session= TwitterCore.getInstance().getSessionManager().getActiveSession();
                    TwitterAuthToken authToken=session.getAuthToken();
                    String token=authToken.token;
                    String secret=authToken.secret;

                    login(session);
            }

            @Override
            public void failure(TwitterException exception) {
               //failure
                Toast.makeText(getApplicationContext(),"Oh! Oh!",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void login(TwitterSession session) {

            String username=session.getUserName();
            Intent intent=new Intent(TwitterMain.this,HomePage.class);
            intent.putExtra("username",username);
            startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);

    }
}
