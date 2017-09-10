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

            String [] sqlStatement = new String[2];
            ResultSet[] resultSet = new ResultSet[2];
            statement = connection.createStatement();
            sqlStatement[0] = "SELECT * FROM PERSON";
            sqlStatement[1] = "SELECT * FROM ROOM";


            for (int index = 0; index <2 ; index++) {
                resultSet[index] = dbData(sqlStatement[index],dbUrl);
            }

            while (resultSet[0].next()){
                person.setName(resultSet[0].getString("NAME"));
                person.setCategory(resultSet[0].getString("CATEGORY"));
                personOps.peopleList.add(person);
            }
            while (resultSet[1].next()){
                room.setRoomName(resultSet[1].getString("NAME"));
                room.setRoomCategory(resultSet[1].getString("CATEGORY"));
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
