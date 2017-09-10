package com.app;

import java.sql.*;

/**
 * Database class that executes database commands to save and load Amity state
 * data from a specified database
 */
public class dbModels {

    private Connection connection = null;
    private Statement statement = null;
    private String sqlStatement = null;
    private String message = null;
    PersonOps personOps = new PersonOps();
    RoomOps roomOps = new RoomOps();

    public void getConnection(String dbUrl) throws ClassNotFoundException,SQLException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(dbUrl);

    }

    /* Method that saves Room and Person app data state to database*/

    public String saveState (String dbName) throws SQLException, ClassNotFoundException {

        String dbUrl = "jdbc:sqlite:" + dbName + ".db";

        if (dbName == null) {
            message = "Add database Name";
            return message;
        } else if (connection ==null) {

            getConnection(dbUrl);

        }else{

                for (int index = 0; index < personOps.peopleList.size() ; index++) {

                    statement = connection.createStatement();
                    sqlStatement = "INSERT INTO TABLE PERSON(NAME, CATEGORY, WANTS ACCOMODATION)" +
                            "VALUES(" + personOps.peopleList.get(index).getName()+"," +
                            ""+ personOps.peopleList.get(index).getCategory()+"," +
                            ""+ personOps.peopleList.get(index).getAccomodation()+","+")";
                    statement.execute(sqlStatement);
                    statement.close();
                    connection.close();

                }
                System.out.print("People State Saved Successfully");


                for (int index = 0; index < roomOps.roomList.size() ; index++) {

                    statement = connection.createStatement();
                    sqlStatement = "INSERT INTO TABLE ROOM(NAME, CATEGORY)" +
                            "VALUES(" + roomOps.roomList.get(index).getRoomName()+"," +
                            ""+ roomOps.roomList.get(index).getRoomCategory()+","+")";

                    statement.execute(sqlStatement);
                    statement.close();
                    connection.close();

                }
                System.out.println("Room state had been Saved Successfully");


            message  = "State Saved";

        }


        return message;
    }

     /* Method that loads Room and Person app data state from database */

    public String loadState(String dbName) throws SQLException,ClassNotFoundException{

        Person person = new Person();
        Room room = new Room ();
        String dbUrl = "jdbc:sqlite:"+ dbName +".db";

        if (dbName== null) {
            message = "Add Database name";
            return message;
        }else if (connection ==null){
            getConnection(dbUrl);
        }else
        {
            statement = connection.createStatement();
            sqlStatement = "SELECT * FROM PERSON";
            ResultSet peopleLoadSet = dbData(sqlStatement,dbUrl);

            while (peopleLoadSet.next()){
                person.setName(peopleLoadSet.getString("NAME"));
                person.setCategory(peopleLoadSet.getString("CATEGORY"));
                personOps.peopleList.add(person);
            }

            statement.close();
            connection.close();

            System.out.println("People State loaded successfully");

            statement = connection.createStatement();
            sqlStatement = "SELECT * FROM ROOM";
            ResultSet roomLoadSet = dbData(sqlStatement,dbUrl);

            while (roomLoadSet.next()){
                room.setRoomName(roomLoadSet.getString("NAME"));
                room.setRoomCategory(roomLoadSet.getString("CATEGORY"));
                roomOps.roomList.add(room);

            }
            statement.close();
            connection.close();
        }

        return message;
    }

    public ResultSet dbData(String sqlStatement, String dbUrl) throws ClassNotFoundException,SQLException{

        if (connection == null){
            getConnection(dbUrl);
        }

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sqlStatement);

        return results;
    }



}
