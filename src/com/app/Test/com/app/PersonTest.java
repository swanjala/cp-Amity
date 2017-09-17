package com.app.Test.com.app;

import com.app.Person;
import com.app.PersonOps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {

    private List<Person> peopleList = new ArrayList<Person>();
    protected String name,category,requestRoom,roomAllocated;


    @Test
    /**
     * Test that a person can be added successfully
     */
    public void test_that_a_person_has_been_added_successfully() {


        PersonOps personOps = new PersonOps("SAMMY WANJALA","FELLOW",
                "Y","NARNIA");

        peopleList = personOps.addPerson();

        System.out.println(peopleList.get(0).getName().toString());

        assertEquals(peopleList.get(0).getName(), "SAMMY WANJALA");
    }

    @Test
    public void test_that_fellow_has_been_reallocated_successfully() {

       // String reallocationMessage = person.reallocatePersonRoom();
      //  assertEquals(reallocationMessage,"Reallocation successful");

    }

    @Test
    public void test_that_fellows_can_be_printed() {

    }

    @Test
    public void test_that_person_data_is_of_correct_type() {
        fail();
    }
}