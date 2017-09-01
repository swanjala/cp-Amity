package com.app;

import org.junit.*;

import static org.junit.Assert.*;

public class dbHelperTest {

    private static String testDB = "testAmity", createPeopleTable, createRoomTable;

    static dbHelper helper = new dbHelper();
    static String message = helper.createDB(testDB);

    @BeforeClass
    public static void setUp() {
        createPeopleTable = helper.createPeopleTable(testDB);
        createRoomTable = helper.createRoomTable(testDB);
    }

    @Test
    public void test_that_database_is_created_succcessfully() {

        assertEquals(message, "Connection Success!");
    }

    @Test
    public void test_that_people_table_is_created_successfully() {

        assertEquals(createPeopleTable, "People table created successfully");
    }

    @Test
    public void test_that_room_table_is_created_successfully() {

        assertEquals(createRoomTable, "Room table created successfully");
    }


}