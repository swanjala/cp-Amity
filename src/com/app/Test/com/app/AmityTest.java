package com.app.Test.com.app;

import com.app.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class AmityTest {

    Collection<List<Person>> personData= new ArrayList<>();
    Collection<List<Room>> roomData= new ArrayList<>();
    private dbModels models = new dbModels();
    PersonOps personOps = new PersonOps("ELIUD KIMANI","STAFF","Y","PLATFORM");
    RoomOps roomOps = new RoomOps("VALHALLA","OFFICE");
    private List<Person> personList = personOps.addPerson();
    private List<Room> roomList = roomOps.addRoom();
    private boolean saveStateValue,loadStateValue;


    /**
     * Test that the application can save state
     */

    @Test
    public void test_that_function_can_save_state() throws SQLException, ClassNotFoundException {
        personData.add(personList);
        roomData.add(roomList);
        saveStateValue = models.saveState("testAmity",personData,roomData);
        assertEquals(saveStateValue,true);

    }

    /* Test that application can load state */
    @Test

    public void test_that_app_can_load_state() throws SQLException,ClassNotFoundException{
        HashMap<String,List> payLoad = models.loadState("testAmity");
        personList = payLoad.get("People");
        roomList = payLoad.get("Rooms");
        String output = personList.get(0).getName() + " " + roomList.get(0).getRoomName();
        assertEquals(output,"ELIUD KIMANI VALHALLA");
    }

}