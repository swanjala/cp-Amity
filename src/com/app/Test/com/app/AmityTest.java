package com.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AmityTest {

    Amity amity = new Amity();
    private List<Person> personList = new ArrayList<>();
    private List<Room> roomList = new ArrayList<>();
    private boolean message;

    /* Test that application can load state */

    @Test
    public void test_that_function_can_save_state(){

//        message = amity.saveState(personList,roomList);
//        assertEquals(message,true);

        fail();
    }

    /* Test that application can load state */
    @Test

    public void test_that_app_can_load_state(){
        message = amity.loadState();
        assertEquals(message,true);
    }



}