package com.cmput301t14.mooditude;

import com.cmput301t14.mooditude.models.FollowRequestMessage;
import com.cmput301t14.mooditude.models.TextMessage;
import com.google.firebase.Timestamp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class MessageUnitTest {

	private Timer wakeup;
	private Timer bedtime;

    @Before
    public void setUp(){
        wakeup = New Timer((Date) 2019-02-01 09:00);
        bedtime = New Timer((Date) 2019-02-01 22:00);
    }

    @Test
    public void testbedtime(){
        assertSame(wakeup.getWakeup(),((Date) 2019-02-01 09:00));
    }

	 @Test
    public void testbedtime(){
        assertSame(bedtime.getBedtime(),((Date) 2019-02-01 22:00));
    }
    
  

}
