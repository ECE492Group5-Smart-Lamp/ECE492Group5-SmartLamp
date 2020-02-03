package com.ece492T5.smartlamp;

import android.app.Activity;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.ece492T5.smartlamp.activities.HomeActivity;
import com.ece492T5.smartlamp.activities.MainActivity;
import com.ece492T5.smartlamp.activities.SelfActivity;
import com.ece492T5.smartlamp.activities.SignInActivity;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IntentTestWithSignIn {

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, true, true);


    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());

        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    @Test
    public void testSignIn(){
        solo.clickOnButton("Sign In");
        solo.waitForActivity(SignInActivity.class);
        solo.assertCurrentActivity("Wrong Activity", SignInActivity.class);


        String email = "testui@test.com";
        solo.enterText((EditText) solo.getView(R.id.signin_email_edit_text), email);
        solo.enterText((EditText) solo.getView(R.id.signin_password_edit_text), "123456");

        solo.clickOnButton("Sign In");
        solo.waitForActivity(HomeActivity.class);

        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);
    }

    @Test
    public void testOpenHomeActivity(){
        solo.clickOnButton("Sign In");
        solo.waitForActivity(SignInActivity.class);
        solo.assertCurrentActivity("Wrong Activity", SignInActivity.class);
        String email = "testui@test.com";
        solo.enterText((EditText) solo.getView(R.id.signin_email_edit_text), email);
        solo.enterText((EditText) solo.getView(R.id.signin_password_edit_text), "123456");
        solo.clickOnButton("Sign In");
        solo.waitForActivity(HomeActivity.class);
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

        solo.clickInList(0);

        Fragment fragment = solo.getCurrentActivity().getFragmentManager().findFragmentById(R.id.frag_frame_add);
        solo.waitForFragmentById(R.id.frag_frame_add);
        solo.waitForText("SmartLamp");
    }

   
}