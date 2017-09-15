 package com.app;

 import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.ByteArrayOutputStream;
 import java.util.Collection;
 import java.util.List;

import static org.junit.Assert.assertEquals;

 public class RoomTest {
    private String officeMessage,livingRoomMessage,genericMessage;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    String[] room_data;
    String[] office_data;
    String message;

    RoomOps room = new RoomOps("","");

    public void setUp() {


    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Test
    public void test_that_no_room_is_added_when_no_data_is_entered() {

        int intiaListSize = room.roomList.size();
        RoomOps room = new RoomOps("","");

         room.addRoom();
         int newsize = room.roomList.size();

        assertEquals(newsize-intiaListSize, 0);

    }

    @Test
    public void test_that_rooms_should_be_added_before_allocation(){

        PersonOps person = new PersonOps("SAMMY WANJALA","FELLOW","VALHALLA","Y");
        person.addPerson();
        assertEquals("Add Rooms Before Allocating", systemOutRule.getLog().trim());

    }

    @Test
    public void test_that_a_space_has_been_created_successfully_as_last_element() {

        RoomOps ops = new RoomOps("VALHALLA","OFFICE");
        Collection<List<Room>> newList =  ops.roomInfo;
        int index = newList.size();

        assertEquals(newList.iterator().next().get(index).getRoomName(), "VALHALLA");

    }


    @Test
    public void test_that_the_room_being_assigned_is_of_correct_type() {

        RoomOps ops = new RoomOps("VALHALLA","MEETING ROOM");
        assertEquals("Enter Correct Room type ==> OFFICE : LIVING", systemOutRule.getLog().trim());

    }



}
