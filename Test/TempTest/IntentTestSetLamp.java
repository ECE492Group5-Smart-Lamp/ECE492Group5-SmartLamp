package com.ece492T5.smartlamp;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ece492T5.smartlamp.activities.AddActivity;
import com.ece492T5.smartlamp.activities.HomeActivity;
import com.ece492T5.smartlamp.activities.MainActivity;
import com.ece492T5.smartlamp.activities.SelfActivity;
import com.ece492T5.smartlamp.activities.SignInActivity;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;



public class IntentTestSetLamp {
    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class,true,true);


    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());

        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

        solo.clickOnButton("Sign In");

        solo.waitForText("SmartLamp",1,2000);
        solo.assertCurrentActivity("Wrong Activity", SignInActivity.class);


        String userName="testui2";
        solo.enterText((EditText) solo.getView(R.id.signin_email_edit_text),userName+"@test.com");
        solo.enterText((EditText) solo.getView(R.id.signin_password_edit_text),"123456");

        solo.clickOnButton("Sign In");


        solo.waitForActivity(HomeActivity.class);

        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

    }

    @Test
    public void start() throws Exception{
        Activity activity= rule.getActivity();

    }


    @Test
    public void setLampCheck(){
        solo.clickOnView(solo.getView(R.id.navigation_self));
        solo.waitForActivity(HomeActivity.class);


        solo.clickInList(0);


        solo.waitForFragmentById(R.id.frag_frame_on,1000);


        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnText("ON");

        solo.clickOnView(solo.getView(Spinner.class, 1));
        solo.scrollToTop();
        solo.clickOnText("OFF");

        
        solo.clickOnView(solo.getView(Spinner.class, 2));
        solo.scrollToTop();
        solo.clickOnText("Brightness");


        solo.waitForText("Choosing the preferred brightness");

    }





}
