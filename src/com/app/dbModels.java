package com.app;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Database class that executes database commands to save and load Amity state
 * data from a specified database
 */
public class dbModels {

    private Connection connection;
    private Statement statement ;
    private String message = null;
    private PersonOps personOps = new PersonOps();
    private dbHelper dHelper = new dbHelper();
    private RoomOps roomOps = new RoomOps();
    private Person person = new Person();
    private Room room = new Room();

    /**
     *  Invokes the database helper class
     *
     **/

    public void initializeDb(String dbName){
        dHelper.createDB(dbName);
        dHelper.createPeopleTable(dbName);
        dHelper.createRoomTable(dbName);
    }

    /**
     * Method that saves Room and Person app data state to database
     *
     * */

    public String saveState(String dbName, Collection<List<Person>> personData,Collection<List<Room>> roomData)
            throws SQLException, ClassNotFoundException {

        initializeDb(dbName); //Initialize DB
        String savePeopleSqlStatement ="";
        String saveRoomSqlStatement = "";

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(dbUrl);
        Statement statement = connection.createStatement();

        Iterator<List<Person>> personIterator = personData.iterator();
        Iterator<List<Room>> roomIterator = roomData.iterator();


        while (personIterator.hasNext()) {
            List<Person> personLists = personIterator.next();


            for (int index = 0; index < personLists.size(); index++) {

                 savePeopleSqlStatement = "INSERT INTO PEOPLE(NAME, CATEGORY, ACCOMODATION)" +
                        "VALUES(" + "'"+
                        personLists.get(0).getName().trim()+"'" + "," +"'"+
                        personLists.get(0).getCategory().trim() +"'"+ "," + "'"+
                        personLists.get(0).getAccomodationRoom().trim() +"'"+ ")";

                System.out.println(savePeopleSqlStatement);
                statement.execute(savePeopleSqlStatement);

            }

        }

        while (roomIterator.hasNext()) {
            List<Room> roomLists = roomIterator.next();


            for (int index = 0; index < roomLists.size(); index++) {

                saveRoomSqlStatement = "INSERT INTO ROOMS(NAME, CATEGORY)" +
                        "VALUES(" +"'"+
                        roomLists.get(0).getRoomName().trim()+"'" + ","+"'"+
                        roomLists.get(0).getRoomCategory().trim()+"'"+ ")";

                System.out.println(saveRoomSqlStatement);
                statement.execute(saveRoomSqlStatement);
            }
        }

        statement.close();
        connection.close();

        message = "State Saved";

        return message;
    }

     /**
      *  Method that loads Room and Person app data state from database
      *
      *  */

    public String loadState(String dbName) throws SQLException, ClassNotFoundException {

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        if (dbName == null) {
            message = "Add Database name";
            return message;
        } else if (connection == null) {

        } else {

            String[] sqlStatement = new String[2];
            ResultSet[] resultSet = new ResultSet[2];
            statement = connection.createStatement();
            sqlStatement[0] = "SELECT * FROM PERSON";
            sqlStatement[1] = "SELECT * FROM ROOM";


            while (resultSet[0].next()) {
                person.setName(resultSet[0].getString("NAME"));
                person.setCategory(resultSet[0].getString("CATEGORY"));
                personOps.peopleList.add(person);
            }
            while (resultSet[1].next()) {
                room.setRoomName(resultSet[1].getString("NAME"));
                room.setRoomCategory(resultSet[1].getString("CATEGORY"));
                roomOps.roomList.add(room);
            }

            statement.close();
            connection.close();
        }

        return message;
    }
}