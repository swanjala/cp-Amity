 package com.app.Test.com.app;

import com.app.RoomOps;
import com.app.Room;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.List;

import static org.junit.Assert.assertEquals;

 public class RoomTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

     /**
      * Test that a room has been added successfully
       */
    @Test
     public void test_that_a_space_has_been_created_successfully_as_last_element() {

         RoomOps roomOps = new RoomOps("VALHALLA","OFFICE");
         List<Room>roomList = roomOps.addRoom();

         assertEquals(roomList.get(0).getRoomName(),"VALHALLA");

     }

     /**
      * Test that the user is prompted on entering wrong room category
      */

     @Test
    public void test_that_user_is_notified_about_wrong_room_category() {

         RoomOps roomOps = new RoomOps("VALHALLA","STORAGE");
         assertEquals("Enter the correct category",systemOutRule.getLog().trim());

    }


}
