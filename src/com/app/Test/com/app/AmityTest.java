package com.app.Test.com.app;

import com.app.database.dbModels;
import com.app.fields.Person;
import com.app.fields.Room;
import com.app.ops.PersonOps;
import com.app.ops.RoomOps;
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
    PersonOps personOps = new PersonOps("Add Person ELIUD KIMANI STAFF Y ",personData,roomData);
    RoomOps roomOps = new RoomOps("VALHALLA","OFFICE");
    private List<Person> personList = personOps.addPerson();
    private List<Room> roomList = roomOps.addRoom();
    private boolean saveStateValue;


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