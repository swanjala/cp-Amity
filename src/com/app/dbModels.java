package com.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database class that executes database commands to save and load Amity state
 * data from a specified database
 */
public class dbModels {

    private Connection connection = null;
    private Statement statement = null;
    private String sqlStatement = null;
    private String message = null;
    private PersonOps personOps = new PersonOps();
    private RoomOps roomOps = new RoomOps();
    private Person person = new Person();
    private Room room = new Room();
    private List<Person> peopleData = new ArrayList<>();
    private List<Room> roomData = new ArrayList<>();

    public void getConnection(String dbUrl) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(dbUrl);

    }

    /* Method that saves Room and Person app data state to database*/

    public String saveState(String dbName) throws SQLException, ClassNotFoundException {

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        peopleData = personOps.peopleList;
        roomData = roomOps.roomList;
        String[] savePeopleSqlStatement = new String[1000];
        String[] saveRoomSqlStatement = new String[1000];
        ResultSet[] saveResultSet = new ResultSet[2];

        /* Generate arrays of SQL commands */

        for (int index = 0; index < peopleData.size(); index++) {
            savePeopleSqlStatement[index] = "INSERT INTO TABLE PERSON(NAME, CATEGORY, WANTS ACCOMODATION)" +
                    "VALUES(" + peopleData.get(index).getName() + "," +
                    "" + peopleData.get(index).getCategory() + "," +
                    "" + peopleData.get(index).getAccomodation() + "," + ")";
        }

        for (int index = 0; index < roomData.size(); index++) {

            saveRoomSqlStatement[index] = "INSERT INTO TABLE ROOM(NAME, CATEGORY)" +
                    "VALUES(" + roomData.get(index).getRoomName() + "," +
                    "" + roomData.get(index).getRoomCategory() + "," + ")";
        }


        if (dbName == null) {
            message = "Add database Name";
            return message;
        } else if (connection == null) {

            getConnection(dbUrl);

        } else {

            for (int index = 0; index < savePeopleSqlStatement.length; index++) {

                saveResultSet[index] = dbData(savePeopleSqlStatement[index], dbUrl);

            }

            for (int index = 0; index < saveRoomSqlStatement.length; index++) {

                saveResultSet[index] = dbData(saveRoomSqlStatement[index], dbUrl);

            }

            message = "State Saved";

        }

        return message;
    }

     /* Method that loads Room and Person app data state from database */

    public String loadState(String dbName) throws SQLException, ClassNotFoundException {

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        if (dbName == null) {
            message = "Add Database name";
            return message;
        } else if (connection == null) {
            getConnection(dbUrl);
        } else {

            String[] sqlStatement = new String[2];
            ResultSet[] resultSet = new ResultSet[2];
            statement = connection.createStatement();
            sqlStatement[0] = "SELECT * FROM PERSON";
            sqlStatement[1] = "SELECT * FROM ROOM";


            for (int index = 0; index < 2; index++) {
                resultSet[index] = dbData(sqlStatement[index], dbUrl);
            }

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

    public ResultSet dbData(String sqlStatement, String dbUrl) throws ClassNotFoundException, SQLException {

        if (connection == null) {
            getConnection(dbUrl);
        }

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sqlStatement);
        statement.close();
        connection.close();

        return results;
    }


}
