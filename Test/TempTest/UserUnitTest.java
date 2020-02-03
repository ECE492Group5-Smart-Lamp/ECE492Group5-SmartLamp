package com.ece492T5.smartlamp;

import com.ece492T5.smartlamp.models.Person;

import org.junit.Assert;
import org.junit.Test;

public class UserUnitTest {

    @Test
    public void userClassTest(){
        User user = new User("test@test.com","test");
        Assert.assertTrue(person.getEmail().equals("test@test.com"));
        Assert.assertTrue(person.getName().equals("test"));

        person.setEmail("set@set.com");
        person.setName("set");
        Assert.assertTrue(person.getEmail().equals("set@set.com"));
        Assert.assertTrue(person.getName().equals("set"));

    }
}
