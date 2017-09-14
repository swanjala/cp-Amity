package com.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {

    PersonOps person = new PersonOps();
    PersonOps reallocatePerson = new PersonOps("SAMMY WANJALA","Y", "NARNIA");
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

        peopleList = person.addPerson();

        assertEquals(peopleList.get(0).getName(), "SAMMY WANJALA");
    }

    @Test
    public void test_that_fellow_has_been_reallocated_successfully() {

        String reallocationMessage = person.reallocatePersonRoom();
        assertEquals(reallocationMessage,"Reallocation successful");

    }

    @Test
    public void test_that_fellows_can_be_printed() {

    }

    @Test
    public void test_that_person_data_is_of_correct_type() {
        fail();
    }
}