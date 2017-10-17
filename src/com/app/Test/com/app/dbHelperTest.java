package com.app.Test.com.app;

import com.app.Database.dbHelper;

import org.junit.*;

import static org.junit.Assert.*;

public class dbHelperTest {

    private  String testDB = "renaomd";
    dbHelper helper = new dbHelper();
    boolean createDb = helper.createDB(testDB);

    /**
     * Test that the database has been created successfully
     */

    @Test
    public void test_that_database_is_created_succcessfully() {

        assertEquals(createDb, true);
    }

    /**
     * Test that the people table has been created successfully
     */

    @Test
    public void test_that_people_table_is_created_successfully() {
        boolean createPeopleTable = helper.createPeopleTable(testDB);
        System.out.println(createPeopleTable);

        assertEquals(createPeopleTable, true);
    }

    /**
     * Test that the room table has been created successfully
     */

    @Test
    public void test_that_room_table_is_created_successfully() {
        boolean createRoomTable = helper.createRoomTable(testDB);

        assertEquals(createRoomTable, true);
    }


}