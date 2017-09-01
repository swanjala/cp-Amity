package com.app;

import org.junit.Test;
import com.app.PersonOps;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonTest {

    PersonOps person = new PersonOps();
    private String genericMessage;
    private Person personData = new Person();
    private List<Person> peopleList = new ArrayList<Person>();

    public void setUp() {

        personData.setName("SAMMY WANJALA");
        personData.setCategory("FELLOW");
        personData.setRoomAllocation("VALHALLA");

        peopleList.add(personData);

    }

    /* Test that fellow has been adde successfully to the system*/
    @Test
    public void test_that_a_person_has_been_added_successfully() {

        genericMessage = person.addPerson("SAMMY WANJALA","FELLOW","Y","VALHALLA");
        assertEquals(genericMessage, "Person has been added successfully");

    }

    @Test
    public void test_that_all_required_fields_are_given(){

        genericMessage = person.addPerson("SAMMY WANJALA","", "","");
        assertEquals(genericMessage,"Ensure that you enter both name and category");

    }
    /* Test that fellow has been reallocated successfully*/
    @Test
    public void test_reallocate_person() {
        fail();

    }
    /* Test that fellows can be printed out successfully */
    @Test
    public void test_that_fellows_can_be_printed(){

        genericMessage = person.printPeople(peopleList);
        assertEquals(genericMessage,"SAMMY WANJALA");
    }

    /* Test that a fellows data is typed correctly in the appliaction*/
    @Test
    public void test_that_person_data_is_of_correct_type() {
 fail();
    }
}