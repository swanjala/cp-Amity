package Test;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    String[] person_data;

    public void setUp() {
        Person person_obj = new Person();
        Room room_obj = new Room();
        person_data = new String[]{"SAMMY WANJALA", "FELLOW", "Y"};
    }

    /* Test that fellow has been adde successfully to the system*/
    @Test
    public void test_that_a_new_fellow_has_been_added_successfully() {


        assertEquals(person_obj(person_data), "FELLOW has been added successfully");

    }

    /* Test that fellow has been reallocated successfully*/
    @Test
    public void test_reallocate_person() {

        assertEquals(person_obj(person_data), " Reallocation success"); // this should sort out all the prblems

    }

    /* Test that a fellows data is typed correctly in the appliaction*/
    @Test
    public void test_that_person_data_is_of_correct_type() {

        assertTrue(person_data instanceof Array);
    }

}