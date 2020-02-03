package com.ece492T5.smartGesture;

import android.graphics.Color;

import com.cmput301t14.mooditude.models.Mood;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Test for Gesture Class
 */
public class GestureTest {

    private Gesture gest;

    @Before
    public void setUp(){
        gest = new Gesture("user1");
    }

    @Test
    public void testMood(){
        gest test = new Gesture("test1");
        assertSame(testMood.getUser(),"test1");
    }

    @Test
    public void testGetMood(){
        assertSame("test1", mood.getUser());
    }

    @Test
    public void testON(){
        gest.turnON();
        assertSame("ON", mood.getStatus());
    }
    
    @Test
    public void testOFF(){
        gest.turnOff();
        assertSame("OFF", mood.getStatus());
    }
    @Test
    public void testSetColor(){
        gest.setColor(Color.GREEN);
    }

    @Test
    public void testGetColor(){
    	gest.setColor(Color.GREEN);
        assertEquals(Gesture.getColor(), Color.GREEN);
    }

}