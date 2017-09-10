package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

    /* Method that saves Room and Person app data state to database*/

    public String saveState(String dbName) {

        if (dbName == null) {
            message = "Add database Name";
            return message;
        } else {
            String dbUrl = "jdbc:sqlite:" + dbName + ".db";
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(dbUrl);

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

            }catch (Exception e){

                return e.getMessage();
            }

            message  = "State Saved";

        }


        return message;
    }

     /* Method that loads Room and Person app data state from database */

    public String loadState(String dbName) {

        String dbUrl = "jdbc:sqlite:"+ dbName +".db";

        try{
            Class.forName("org:sqlite:JDBC");
            connection =DriverManager.getConnection(dbUrl);
            statement = connection.createStatement();
            sqlStatement = "SELECT * FROM PERSON";
            statement.execute(sqlStatement);

            /*
            Logic to receive data from database and convert it to an array list

            */

            statement.close();
            connection.close();

            System.out.println("State loaded successfully");

        }catch (Exception e){
            return  e.getMessage();
        }
        return message;
    }

}
