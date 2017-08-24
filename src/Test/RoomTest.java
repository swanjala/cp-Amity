package Test;

import static org.junit.Assert.*;

public class RoomTest {

    String[] room_data;
    String[] office_data;

    public void setUp() {

		/* Data initialization*/

        Room room_obj = new Room();
        room_data = new String[]{"Room 1", "Room 2"};
        office_data = new String[]{"Area 1", "Area 2", "Area 3"};

    }

    @Test
    public void test_correct_response_when_no_data_is_entered() {

		/* A specific value should be returned whn*/
        assertEquals(room_obj("", "DEFAULT"), "There's no room to be saved");

    }

    @Test
    public void test_that_a_working_space_has_been_created_successfully() {


        assertEquals(room_obj(office_data, "OFFICE"), "OFFICE space Added Successfully");

    }

    @Test

    public void test_that_a_new_living_area_has_been_created_successfully() {

        assertEquals(room_obj(room_data, "LIVING"), "LIVING area has been creted successfully");

    }

    @Test
    public void test_that_rooms_are_available_for_allocation() {

        assertEquals(room_obj(), "There are no rooms to assign");

    }

    @Test

    public void test_that_roomns_are_available_for_reallocation() {

        assertEquals(room_obj(), "There are no rooms for reallocaltion");
    }

    @Test
    public void test_that_the_system_can_tell_if_all_rooms_are_occupied() {

        assertEquals(room_obj(), "All rooms are occupied");
    }

    @Test
    public void test_that_the_room_being_assigned_is_of_correct_type() {

        assertEquals(room_obj(), room.type = "LIVING");
    }

    @Test

    public void test_that_correct_room_category_is_added() {

        assertEquals(room_obj(room_data, "OTHER"), "Room type is not defined");
    }



}