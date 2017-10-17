package com.app.database;

import com.app.fields.Person;
import com.app.ops.PersonOps;
import com.app.fields.Room;
import com.app.ops.RoomOps;

import java.sql.*;
import java.util.*;

/**
 * Class executes database commands to save and load Amity state
 * data from a specified database
 */
public class dbModels {

    private Connection connection;
    private Statement statement;
    private String message = null;
    private PersonOps personOps = new PersonOps();
    private dbHelper dHelper = new dbHelper();
    private RoomOps roomOps = new RoomOps();
    private Person person = new Person();
    private Room room = new Room();

    /**
     * Invokes the database helper class
     **/

    public void initializeDb(String dbName) {
        dHelper.createDB(dbName);
        dHelper.createPeopleTable(dbName);
        dHelper.createRoomTable(dbName);
    }

    /**
     * Method that saves Room and Person app data state to database
     */

    public Boolean saveState(String dbName, Collection<List<Person>> personData, Collection<List<Room>> roomData)
            throws SQLException, ClassNotFoundException {

        initializeDb(dbName); //Initialize DB
        String savePeopleSqlStatement = "";
        String saveRoomSqlStatement = "";

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(dbUrl);
        Statement statement = connection.createStatement();

        Iterator<List<Person>> personIterator = personData.iterator();
        Iterator<List<Room>> roomIterator = roomData.iterator();

        try {
            while (personIterator.hasNext()) {
                List<Person> personLists = personIterator.next();


                for (int index = 0; index < personLists.size(); index++) {

                    savePeopleSqlStatement = "INSERT INTO PEOPLE(NAME, CATEGORY, ACCOMODATION)" +
                            "VALUES(" + "'" +
                            personLists.get(0).getName().trim() + "'" + "," + "'" +
                            personLists.get(0).getCategory().trim() + "'" + "," + "'" +
                            personLists.get(0).getAccomodationRoom().trim() + "'" + ")";

                    System.out.println(savePeopleSqlStatement);
                    statement.execute(savePeopleSqlStatement);

                }

            }
            while (roomIterator.hasNext()) {
                List<Room> roomLists = roomIterator.next();


                for (int index = 0; index < roomLists.size(); index++) {

                    saveRoomSqlStatement = "INSERT INTO ROOMS(NAME, CATEGORY)" +
                            "VALUES(" + "'" +
                            roomLists.get(0).getRoomName().trim() + "'" + "," + "'" +
                            roomLists.get(0).getRoomCategory().trim() + "'" + ")";

                    System.out.println(saveRoomSqlStatement);
                    statement.execute(saveRoomSqlStatement);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        statement.close();
        connection.close();

        return true;
    }

    /**
     * Method that loads Room and Person app data state from database
     */

    public HashMap<String, List> loadState(String dbName) throws SQLException, ClassNotFoundException {
        HashMap<String, List> data = new HashMap<>();
        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        List<Person> personList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl);
            Statement statementPeople;

            statementPeople = connection.createStatement();

            String getPeopleStatement = "SELECT * FROM PEOPLE";
            ResultSet peopleResultSet = statementPeople.executeQuery(getPeopleStatement);

            while (peopleResultSet.next()) {

                person.setName(peopleResultSet.getString("NAME").trim());
                person.setCategory(peopleResultSet.getString("CATEGORY").trim());
                person.setAccomodationRoom(peopleResultSet.getString("ACCOMODATION").trim());
                personList.add(person);
            }

            statementPeople.close();
            connection.close();

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl);
            Statement statementRooms = connection.createStatement();

            String getRoomStatement = "SELECT * FROM ROOMS";
            ResultSet roomResultSet = statementRooms.executeQuery(getRoomStatement);


            while (roomResultSet.next()) {
                room.setRoomName(roomResultSet.getString("NAME").trim());
                room.setRoomCategory(roomResultSet.getString("CATEGORY").trim());

                roomList.add(room);
                System.out.println(roomList.toString());

            }
            statementRooms.close();
            connection.close();


        } catch (Exception e) {
            System.out.println("Error Occured : " + e.getMessage());
        }

        data.put("People", personList);
        data.put("Rooms", roomList);

        return data;
    }
}