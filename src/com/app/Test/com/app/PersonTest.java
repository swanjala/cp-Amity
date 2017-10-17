package com.app.Test.com.app;

import com.app.fields.Person;
import com.app.fields.Room;
import com.app.ops.PersonOps;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private static List<Person> peopleList = new ArrayList<Person>();
    private static Collection<List<Room>> allRoomsData = new ArrayList<>();
    private static Collection <List<Person>> allPeopleData= new ArrayList<>();
    protected static String  category;
    private static PersonOps personOps,reallocate;
    private static String result,output,unallocatedOutput;

    @BeforeClass
    public static void setUp(){

         personOps = new PersonOps("Add Person SAMMY WANJALA FELLOW Y",allPeopleData,allRoomsData);
         reallocate = new PersonOps("SAMMY WANJALA",
                 "HOGWARTS",allPeopleData);

        peopleList = personOps.addPerson();
        allPeopleData.add(peopleList);
        result = reallocate.reallocatePerson();
        output = personOps.printPeople(allPeopleData);
        unallocatedOutput = personOps.printUnallocated(allPeopleData);
    }

    /**
     * Test that a person can be added successfully
     */


    @Test
    public void test_that_a_person_has_been_added_successfully() {

        assertEquals(peopleList.get(0).getName(), "SAMMY WANJALA");
    }

    /**
     * Test that a person can be reallocated successfully
     */

    @Test
    public void test_that_fellow_has_been_reallocated_successfully() {

        assertEquals(result, "Reallocation Success");

    }

    /**
     * Test that people can be printed out
     */

    @Test
    public void test_that_people_can_be_printed() {

        assertEquals(output, "SAMMY WANJALA FELLOW HOGWARTS");
    }

    @Test
    public void test_that_unallocated_people_can_be_printed() {

        assertEquals(unallocatedOutput, "SAMMY WANJALA FELLOW HOGWARTS");

    }

}