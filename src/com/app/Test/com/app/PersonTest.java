package com.app;

import org.junit.Test;
import com.app.PersonOps;
import static org.junit.Assert.*;

public class PersonTest {

    String[] person_data;
    PersonOps person = new PersonOps();
    String message;

    public void setUp() {

        Room room_obj = new Room();
        person_data = new String[]{"SAMMY WANJALA", "FELLOW", "Y"};
    }

    /* Test that fellow has been adde successfully to the system*/
    @Test
    public void test_that_a_new_fellow_has_been_added_successfully() {

        message = person.addPerson("SAMMY WANJALA","FELLOW","Y","VALHALLA");

        assertEquals(message, "FELLOW has been added successfully");

    }

    /* Test that fellow has been reallocated successfully*/
    @Test
    public void test_reallocate_person() {
        fail();

    }

    /* Test that a fellows data is typed correctly in the appliaction*/
    @Test
    public void test_that_person_data_is_of_correct_type() {
 fail();
    }
}