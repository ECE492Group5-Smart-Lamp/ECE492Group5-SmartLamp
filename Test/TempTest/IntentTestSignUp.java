package com.ece492T5.smartlamp;
import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.ece492T5.smartlamp.activities.MainActivity;
import com.ece492T5.smartlamp.activities.RegisterActivity;
import com.ece492T5.smartlamp.activities.SignInActivity;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
public class IntentTestSignUp {

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class,true,true);


    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception{
        Activity activity= rule.getActivity();
    }

    @Test

    public void checkSignUp(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

        solo.clickOnButton("Join Now");

        solo.waitForText("Welcome to SmartLamp",1,2000);

        solo.assertCurrentActivity("Wrong Activity", RegisterActivity.class);
        String userName="testA";
        solo.enterText((EditText) solo.getView(R.id.register_email),userName+"@test.com");
        solo.enterText((EditText) solo.getView(R.id.register_username),userName);
        solo.enterText((EditText) solo.getView(R.id.register_password),"123456");
        solo.enterText((EditText) solo.getView(R.id.register_password_2),"123456");
        solo.clickOnButton("Join Now");

        solo.waitForText("SmartLamp",1,2000);
        solo.assertCurrentActivity("Wrong Activity", SignInActivity.class);

    }


}
