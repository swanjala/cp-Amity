 package com.app;

 import org.junit.Test;
 import com.app.RoomOps;


    import static org.junit.Assert.*;

public class RoomTest {
    private String officeMessage,livingRoomMessage,genericMessage;
    String[] room_data;
    String[] office_data;
    String message;

    RoomOps room = new RoomOps();


    public void setUp() {


    }

    @Test
    public void test_correct_response_when_no_data_is_entered() {

		/* A specific value should be returned whn*/

		officeMessage = room.addRoom("","");
        assertEquals(officeMessage, "There's no room to be saved");

    }

    @Test
    public void test_that_a_working_space_has_been_created_successfully() {

        officeMessage = room.addRoom("VALHALLA","OFFICE");
        assertEquals(officeMessage, "New room has been added");

    }

    @Test

    public void test_that_a_new_living_area_has_been_created_successfully() {

        livingRoomMessage = room.addRoom("SHELL","LIVING");
        assertEquals(livingRoomMessage, "New room has been added");

    }

    @Test
    public void test_that_all_living_rooms_are_occupied() {

        genericMessage = room.addRoom("JADE","OFFICE");
        assertEquals(genericMessage, "All rooms are occupied");
    }

    @Test
    public void test_that_the_room_being_assigned_is_of_correct_type() {

        genericMessage = room.addRoom("ROUND TABLE","MEETING ROOM");
        assertEquals(officeMessage,"This room category does not exist");
    }

    @Test
    public void test_print_room_of_same_type_is_available(){

        genericMessage = room.printRoom("VALHALLA");
        assertEquals(genericMessage,"Room Unoccupied");

    }


}
