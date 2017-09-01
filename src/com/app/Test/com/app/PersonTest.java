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

    @Test
    public void test_that_a_person_has_been_added_successfully() {

        peopleList = person.addPerson("SAMMY WANJALA", "FELLOW", "Y", "VALHALLA");

        assertEquals(peopleList.get(0).getName(), "SAMMY WANJALA");
    }

    @Test
    public void test_that_fellow_has_been_reallocated_successfully() {

        fail();

    }

    @Test
    public void test_that_fellows_can_be_printed() {

        genericMessage = person.printPeople(peopleList);
        assertEquals(genericMessage, "SAMMY WANJALA");
    }

    @Test
    public void test_that_person_data_is_of_correct_type() {
        fail();
    }
}