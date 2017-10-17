package com.app.Test.com.app;

import com.app.database.dbHelper;

import org.junit.*;

import static org.junit.Assert.*;

public class dbHelperTest {

    private static String testDB = "bdName";
    private static dbHelper helper = new dbHelper();

    static boolean createDb;


    @BeforeClass
    public static void setUp(){
       createDb = helper.createDB(testDB);
    }

    @AfterClass
    public static void tearDown(){
        helper.dropTables(testDB);
    }

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