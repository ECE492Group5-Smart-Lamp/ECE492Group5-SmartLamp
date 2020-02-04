/**
 * Authors: Shixiong Gao, Xiaoyu Liu, Ye Wang, Xianda Xu
 * Date: Jan. 30, 2020
 */

/**
 * This activity is the app start-up interface. Users can
 * go to SignInActivity or RegisterActivity.
 */

package com.example.smartlamp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button signInBtn = findViewById(R.id.starter_sign_in_button);
        final Button joinNowBtn = findViewById(R.id.stater_join_now_button);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Click on signIn button will go to the SignIn Activity.
             * @param v - view
             */
            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intentSignIn);
            }
        });

        joinNowBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Click on joinNow button will go the Register Activity.
             * @param v - view
             */
            @Override
            public void onClick(View v) {
                Intent intentJoinNow = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentJoinNow);
            }
        });

    }

}
