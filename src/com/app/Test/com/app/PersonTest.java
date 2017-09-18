package com.app.Test.com.app;

import com.app.Person;
import com.app.PersonOps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {

    private List<Person> peopleList = new ArrayList<Person>();
    Collection <List<Person>> allPeopleData= new ArrayList<>();
    protected String name,category,requestRoom,roomAllocated;


    PersonOps personOps = new PersonOps("SAMMY WANJALA","FELLOW",
            "Y","NARNIA");

    /**
     * Tests that a person can be added successfully
     */

    @Test
    public void test_that_a_person_has_been_added_successfully() {

        peopleList = personOps.addPerson();
        allPeopleData.add(peopleList);

        assertEquals(peopleList.get(0).getName(), "SAMMY WANJALA");
    }

    /**
     * Tests that a person can be reallocated successfully
     */

    @Test
    public void test_that_fellow_has_been_reallocated_successfully()  {

        peopleList = personOps.addPerson();
        allPeopleData.add(peopleList);

        PersonOps reallocate = new PersonOps("SAMMY WANJALA", "HOGWARTS",allPeopleData);

        String result = reallocate.reallocatePerson();

        assertEquals(result,"Reallocation Success");

    }

    @Test
    public void test_that_fellows_can_be_printed() {

    }

    @Test
    public void test_that_person_data_is_of_correct_type() {
        fail();
    }
}